package org.deltacv.mackjpeg.test;

import org.deltacv.mackjpeg.*;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TurboJPEGTests {
    static final int TEST_IMAGE_WIDTH = 400;
    static final int TEST_IMAGE_HEIGHT = 400;

    // junit test
    @Test
    public void testBackendSetup() {
        JPEGBackend backend = TurboJPEGBackend.INSTANCE;
        assertNotNull(backend);
        assertTrue(backend.isSupported());
    }

    @Test
    public void testJpegDecompression() {
        try(JPEGDecompressor decompressor = TurboJPEGBackend.INSTANCE.makeDecompressor()) {
            assertNotNull(decompressor);

            // Test decompression with a valid JPEG file
            // load bytes of /test.jpg
            byte[] jpegBytes = null;
            try {
                jpegBytes = getClass().getResourceAsStream("/test.jpeg").readAllBytes();
                decompressor.setJPEG(jpegBytes, jpegBytes.length);
            } catch (IOException | JPEGException | NullPointerException e) {
                fail("Failed to read test JPEG file:", e);
            }

            assertEquals(400, decompressor.getDecodedWidth(), "Decoded width should be 400");
            assertEquals(400, decompressor.getDecodedHeight(), "Decoded height should be 400");

            assertTrue(jpegBytes.length > 0, "JPEG bytes should not be empty");
            assertNotNull(jpegBytes, "JPEG bytes should not be null");

            try {
                byte[] output = decompressor.decompress(PixelFormat.RGB);
                assertNotNull(output, "Decompressed output should not be null");
                assertEquals((TEST_IMAGE_WIDTH * TEST_IMAGE_HEIGHT * 3), output.length, "Decompressed output does not match image size");
            } catch (JPEGException e) {
                fail("Decompression failed:", e);
            }
        } catch (Exception e) {
            fail("Failed to create JPEG decompressor:", e);
        }
    }


    @Test
    public void testJpegCompression() {
        try(JPEGCompressor compressor = TurboJPEGBackend.INSTANCE.makeCompressor()) {
            assertNotNull(compressor);

            // Test decompression with a valid JPEG file
            // load bytes of /test.jpg
            byte[] jpegBytes = null;
            try {
                jpegBytes = getClass().getResourceAsStream("/test.jpeg").readAllBytes();
            } catch (IOException | NullPointerException e) {
                fail("Failed to read test JPEG file:", e);
            }

            byte[] rgbBytes = null;
            try(JPEGDecompressor decompressor = TurboJPEGBackend.INSTANCE.makeDecompressor()) {
                decompressor.setJPEG(jpegBytes, jpegBytes.length);
                rgbBytes = decompressor.decompress(PixelFormat.RGB);

                compressor.setImage(rgbBytes, decompressor.getDecodedWidth(), decompressor.getDecodedHeight(), PixelFormat.RGB);
            } catch (JPEGException e) {
                fail("Decompression failed:", e);
            }

            compressor.setSubSampling(Sampling.SAMP_440);

            assertTrue(jpegBytes.length > 0, "JPEG bytes should not be empty");
            assertNotNull(jpegBytes, "JPEG bytes should not be null");

            assertNotNull(rgbBytes, "RGB bytes should not be null");
            assertEquals((TEST_IMAGE_WIDTH * TEST_IMAGE_HEIGHT * 3), rgbBytes.length, "RGB bytes do not match image size");

            try {
                compressor.setQuality(50);
                byte[] compressed = compressor.compress();
                assertNotNull(compressed, "Compressed output should not be null");
                assertTrue(compressed.length > 0, "Compressed output should not be empty");
            } catch (JPEGException e) {
                fail("Compression failed:", e);
            }
        } catch (Exception e) {
            fail("Failed to create JPEG compressor:", e);
        }
    }

}
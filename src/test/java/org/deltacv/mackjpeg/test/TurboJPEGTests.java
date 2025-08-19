package org.deltacv.mackjpeg.test;

import org.deltacv.mackjpeg.JPEGBackend;
import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.JPEGDecompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TurboJPEGTests {
    // junit test
    @Test
    public void testBackendSetup() {
        JPEGBackend backend = TurboJPEGBackend.INSTANCE;
        Assertions.assertNotNull(backend);
        Assertions.assertTrue(backend.isSupported());
    }

    @Test
    public void testJpegCompression() {
        try(JPEGCompressor compressor = TurboJPEGBackend.INSTANCE.makeCompressor()) {
            Assertions.assertNotNull(compressor);
        } catch (Exception e) {
            Assertions.fail("Failed to create JPEG compressor:", e);
        }
    }

    @Test
    public void testJpegDecompression() {
        try(JPEGDecompressor decompressor = TurboJPEGBackend.INSTANCE.makeDecompressor()) {
            Assertions.assertNotNull(decompressor);

            // Test decompression with a valid JPEG file
            // load bytes of /test.jpg
            byte[] jpegBytes = null;
            try {
                jpegBytes = getClass().getResourceAsStream("/test.jpeg").readAllBytes();
                System.out.println("Loaded JPEG bytes: " + jpegBytes.length + " bytes");

                decompressor.setJPEG(jpegBytes, jpegBytes.length);
            } catch (IOException | JPEGException | NullPointerException e) {
                Assertions.fail("Failed to read test JPEG file:", e);
            }

            Assertions.assertEquals(400, decompressor.getDecodedWidth(), "Decoded width should be 400");
            Assertions.assertEquals(400, decompressor.getDecodedHeight(), "Decoded height should be 400");

            Assertions.assertTrue(jpegBytes.length > 0, "JPEG bytes should not be empty");
            Assertions.assertNotNull(jpegBytes, "JPEG bytes should not be null");

            try {
                byte[] output = decompressor.decompress(PixelFormat.RGB);
                Assertions.assertNotNull(output, "Decompressed output should not be null");
                Assertions.assertTrue(output.length > 0, "Decompressed output should not be empty");
            } catch (JPEGException e) {
                Assertions.fail("Decompression failed:", e);
            }
        } catch (Exception e) {
            Assertions.fail("Failed to create JPEG decompressor:", e);
        }
    }

}
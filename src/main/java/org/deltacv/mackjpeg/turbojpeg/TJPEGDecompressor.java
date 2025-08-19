package org.deltacv.mackjpeg.turbojpeg;

import org.deltacv.mackjpeg.JPEGDecompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.libjpegturbo.turbojpeg.TJ;
import org.libjpegturbo.turbojpeg.TJDecompressor;

public class TJPEGDecompressor implements JPEGDecompressor {
    private TJDecompressor tj;

    public TJPEGDecompressor() {
        try {
            tj = new TJDecompressor();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create TJDecompressor", e);
        }
    }

    @Override
    public void setJPEG(byte[] jpegData, int jpegSize) throws JPEGException {
        try {
            tj.setSourceImage(jpegData, jpegSize);
        } catch (Exception e) {
            throw new JPEGException("Failed to set JPEG Image", e);
        }
    }

    @Override
    public void decompress(byte[] out, int width, int height, PixelFormat pixelFormat) throws JPEGException {
        try {
            tj.decompress8(out, width, 0, height, TJPixelFormatMapper.mapToTJPixelFormat(pixelFormat));
        } catch (Exception e) {
            throw new JPEGException("Failed to decompress JPEG Image", e);
        }
    }

    @Override
    public byte[] decompress(PixelFormat pixelFormat)  throws JPEGException {
        try {
            return tj.decompress8(0, TJPixelFormatMapper.mapToTJPixelFormat(pixelFormat));
        } catch (Exception e) {
            throw new JPEGException("Failed to decompress JPEG Image", e);
        }
    }

    @Override
    public int getDecodedWidth() {
        return tj.getWidth();
    }

    @Override
    public int getDecodedHeight() {
        return tj.getHeight();
    }

    @Override
    public void close() {
        if (tj != null) {
            tj.close();
            tj = null;
        }
    }
}

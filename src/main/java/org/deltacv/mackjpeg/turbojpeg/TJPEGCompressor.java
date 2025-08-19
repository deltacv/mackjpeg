package org.deltacv.mackjpeg.turbojpeg;

import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.libjpegturbo.turbojpeg.TJ;
import org.libjpegturbo.turbojpeg.TJCompressor;

public class TJPEGCompressor implements JPEGCompressor {

    private TJCompressor tj;

    @Override
    public void setImage(byte[] image, int width, int height, PixelFormat pixelFormat) throws JPEGException {
        try {
            tj = new TJCompressor(image, width, 0, height, TJPixelFormatMapper.mapToTJPixelFormat(pixelFormat));
        } catch (Exception e) {
            throw new JPEGException("Failed to create TJPEGCompressor", e);
        }
    }

    @Override
    public void setQuality(int quality) throws JPEGException {
        assertNonNull();

        try {
            tj.setJPEGQuality(quality);
        } catch (Exception e) {
            throw new JPEGException("Failed to set quality", e);
        }
    }

    @Override
    public void compress(byte[] out) throws JPEGException {
        assertNonNull();

        try {
            tj.compress(out, TJ.FLAG_FASTDCT);
        } catch (Exception e) {
            throw new JPEGException("Failed to compress image", e);
        }
    }

    @Override
    public byte[] compress() throws JPEGException {
        assertNonNull();

        try {
            return tj.compress(TJ.FLAG_FASTDCT);
        } catch (Exception e) {
            throw new JPEGException("Failed to compress image", e);
        }
    }

    private void assertNonNull() throws JPEGException {
        if (tj == null) {
            throw new JPEGException("TJPEGCompressor is not initialized, call setImage() first.");
        }
    }
}

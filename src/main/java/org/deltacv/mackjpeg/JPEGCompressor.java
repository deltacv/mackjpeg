package org.deltacv.mackjpeg;

import org.deltacv.mackjpeg.exception.JPEGException;

public interface JPEGCompressor extends AutoCloseable {
    void setImage(byte[] image, int width, int height, PixelFormat pixelFormat) throws JPEGException;
    void setQuality(int quality) throws JPEGException;

    void compress(byte[] out) throws JPEGException;
    byte[] compress() throws JPEGException;
}

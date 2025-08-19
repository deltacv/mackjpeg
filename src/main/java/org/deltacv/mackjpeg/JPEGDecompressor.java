package org.deltacv.mackjpeg;

import org.deltacv.mackjpeg.exception.JPEGException;

public interface JPEGDecompressor extends AutoCloseable {
    void setJPEG(byte[] jpegData, int jpegSize) throws JPEGException;

    void decompress(byte[] out, int width, int height, PixelFormat pixelFormat) throws JPEGException;
    byte[] decompress(PixelFormat pixelFormat) throws JPEGException;

    int getDecodedWidth();
    int getDecodedHeight();
}

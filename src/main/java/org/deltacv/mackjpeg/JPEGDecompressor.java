package org.deltacv.mackjpeg;

import org.deltacv.mackjpeg.exception.JPEGException;

public interface JPEGDecompressor {
    void setJPEG(byte[] jpegData, int jpegSize) throws JPEGException;

    void decompress(byte[] out, int width, int height, PixelFormat pixelFormat) throws JPEGException;
    byte[] decompress(int width, int height, PixelFormat pixelFormat) throws JPEGException;
}

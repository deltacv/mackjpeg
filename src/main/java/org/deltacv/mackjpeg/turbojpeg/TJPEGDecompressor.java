/*
 * Copyright (c) 2025 Sebastian Erives
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package org.deltacv.mackjpeg.turbojpeg;

import org.deltacv.mackjpeg.JPEGDecompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.libjpegturbo.turbojpeg.TJ;
import org.libjpegturbo.turbojpeg.TJDecompressor;
import org.libjpegturbo.turbojpeg.TJException;

/**
 * Implementation of JPEGDecompressor using the TurboJPEG library.
 * This class provides methods to set JPEG data, decompress it, and retrieve
 * the decoded image dimensions.
 */
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
            try {
                tj.close();
            } catch (TJException ignored) {}

            tj = null;
        }
    }
}

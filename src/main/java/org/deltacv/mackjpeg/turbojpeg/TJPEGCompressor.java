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

import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.libjpegturbo.turbojpeg.TJ;
import org.libjpegturbo.turbojpeg.TJCompressor;
import org.libjpegturbo.turbojpeg.TJException;

/**
 * Implementation of JPEGCompressor using libjpeg-turbo's TJCompressor.
 */
public class TJPEGCompressor implements JPEGCompressor {

    private TJCompressor tj;

    @Override
    public void setImage(byte[] image, int width, int height, PixelFormat pixelFormat) throws JPEGException {
        try {
            tj = new TJCompressor(image, 0, 0, width, 0, height, TJPixelFormatMapper.mapToTJPixelFormat(pixelFormat));
        } catch (Exception e) {
            throw new JPEGException("Failed to create TJPEGCompressor", e);
        }
    }

    @Override
    public void setQuality(int quality) throws JPEGException {
        assertNonNull();
        tj.set(TJ.PARAM_QUALITY, quality);
    }

    @Override
    public int getCompressedSize() throws JPEGException {
        assertNonNull();
        return tj.getCompressedSize();
    }

    @Override
    public void compress(byte[] out) throws JPEGException {
        assertNonNull();

        try {
            tj.compress(out);
        } catch (Exception e) {
            throw new JPEGException("Failed to compress image", e);
        }
    }

    @Override
    public byte[] compress() throws JPEGException {
        assertNonNull();

        try {
            return tj.compress();
        } catch (Exception e) {
            throw new JPEGException("Failed to compress image", e);
        }
    }

    private void assertNonNull() throws JPEGException {
        if (tj == null) {
            throw new JPEGException("TJPEGCompressor is not initialized, call setImage() first.");
        }
    }

    @Override
    public void close() throws TJException {
        if(tj != null) {
            tj.close();
            tj = null;
        }
    }
}

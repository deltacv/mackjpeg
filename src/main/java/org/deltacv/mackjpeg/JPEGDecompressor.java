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

package org.deltacv.mackjpeg;

import org.deltacv.mackjpeg.exception.JPEGException;

/**
 * Interface for JPEG decompression.
 */
public interface JPEGDecompressor extends AutoCloseable {
    /* * Sets the JPEG data to be decompressed.
     *
     * @param jpegData The JPEG data as a byte array.
     * @param jpegSize The size of the JPEG data in bytes.
     * @throws JPEGException If an error occurs while setting the JPEG data.
     */
    void setJPEG(byte[] jpegData, int jpegSize) throws JPEGException;

    /**
     * Decompresses the JPEG data into the specified output buffer.
     *
     * @param out The output buffer to store the decompressed image.
     * @param width The width of the decompressed image.
     * @param height The height of the decompressed image.
     * @param pixelFormat The pixel format of the decompressed image.
     * @throws JPEGException If an error occurs during decompression.
     */
    void decompress(byte[] out, PixelFormat pixelFormat) throws JPEGException;

    /**
     * Decompresses the JPEG data and returns it as a byte array.
     *
     * @param pixelFormat The pixel format of the decompressed image.
     * @return The decompressed image as a byte array.
     * @throws JPEGException If an error occurs during decompression.
     */
    byte[] decompress(PixelFormat pixelFormat) throws JPEGException;

    /**
     * Get the resulting image width after decompression.
     * Can be used to determine the dimensions of the output image before decompression.
     */
    int getDecodedWidth();

    /**
     * Get the resulting image height after decompression.
     * Can be used to determine the dimensions of the output image before decompression.
     */
    int getDecodedHeight();
}

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
 * Interface for JPEG compression.
 * This interface defines methods to set the image data, quality, and to compress the image.
 */
public interface JPEGCompressor extends AutoCloseable {

    /**
     * Sets the image data to be compressed.
     *
     * @param image The image data as a byte array.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param pixelFormat The pixel format of the image (e.g., RGB, BGR, GRAY).
     * @throws JPEGException If an error occurs while setting the image data.
     */
    void setImage(byte[] image, int width, int height, PixelFormat pixelFormat) throws JPEGException;

    /**
     * Sets the quality of the JPEG compression.
     *
     * @param quality The quality level for compression (0-100).
     * @throws JPEGException If an error occurs while setting the quality.
     */
    void setQuality(int quality) throws JPEGException;

    /**
     * Compresses the image and writes the output to the specified byte array.
     *
     * @param out The byte array to write the compressed JPEG data to.
     * @throws JPEGException If an error occurs during compression.
     */
    void compress(byte[] out) throws JPEGException;

    /**
     * Compresses the image and returns the compressed JPEG data as a byte array.
     *
     * @return The compressed JPEG data as a byte array.
     * @throws JPEGException If an error occurs during compression.
     */
    byte[] compress() throws JPEGException;
}

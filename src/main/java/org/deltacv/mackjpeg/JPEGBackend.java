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

/**
 * Interface for JPEG backend implementations.
 * Provides methods to create compressors and decompressors,
 * check support, and get the name of the backend.
 */
public interface JPEGBackend {
    /**
     * Creates a new JPEG compressor instance.
     */
    JPEGCompressor makeCompressor();

    /**
     * Creates a new JPEG decompressor instance.
     */
    JPEGDecompressor makeDecompressor();

    /**
     * Checks if the backend is supported on the current platform.
     *
     * @return true if supported, false otherwise.
     */
    boolean isSupported();

    /**
     * Gets the name of the JPEG backend.
     *
     * @return the name of the backend.
     */
    String getName();
}
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

import org.deltacv.mackjpeg.JPEGBackend;
import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.JPEGDecompressor;
import org.libjpegturbo.turbojpeg.TJLoader;

/**
 * Implementation of JPEGBackend using the TurboJPEG library.
 * Automatically loads the native TurboJPEG library when this class is loaded.
 * This class provides methods to create JPEG compressors and decompressors,
 * and checks if the TurboJPEG backend is supported.
 */
public final class TurboJPEGBackend implements JPEGBackend {
    public static final TurboJPEGBackend INSTANCE = new TurboJPEGBackend();

    static {
        try {
            TJLoader.load();
        } catch (Exception ignored) { }
    }

    private TurboJPEGBackend() {
        // Private constructor to prevent instantiation
    }

    @Override
    public JPEGCompressor makeCompressor() {
        return new TJPEGCompressor();
    }

    @Override
    public JPEGDecompressor makeDecompressor() {
        return new TJPEGDecompressor();
    }

    @Override
    public boolean isSupported() {
        return TJLoader.isLoaded();
    }

    @Override
    public String getName() {
        return "TurboJPEG";
    }
}

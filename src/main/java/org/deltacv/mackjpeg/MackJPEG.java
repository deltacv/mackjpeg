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

import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;
import org.libjpegturbo.turbojpeg.TJLoader;

/**
 * Main class for the MackJPEG library.
 * Provides methods to get the supported JPEG backend.
 * Currently, it supports TurboJPEG as the only backend.
 */
@SuppressWarnings("unused")
public final class MackJPEG {
    private MackJPEG() {
        // Prevent instantiation
    }

    /**
     * Checks if the TurboJPEG backend is supported and returns it.
     * If TurboJPEG is not supported, it returns null.
     * @return JPEGBackend instance if supported, otherwise null.
     */
    public static JPEGBackend getSupportedBackend() {
        if(TurboJPEGBackend.INSTANCE.isSupported()) {
            return TurboJPEGBackend.INSTANCE;
        } else {
            return null;
        }
    }
}

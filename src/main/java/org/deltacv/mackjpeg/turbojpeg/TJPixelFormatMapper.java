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

import org.deltacv.mackjpeg.PixelFormat;
import org.libjpegturbo.turbojpeg.TJ;

/**
 * Utility class to map PixelFormat enums to TurboJPEG pixel format constants.
 */
final class TJPixelFormatMapper {
    private TJPixelFormatMapper() {
        // Prevent instantiation
    }

    public static int mapToTJPixelFormat(PixelFormat pixelFormat) {
        switch (pixelFormat) {
            case GRAY:
                return TJ.PF_GRAY;
            case RGB:
                return TJ.PF_RGB;
            case BGR:
                return TJ.PF_BGR;
        }

        throw new IllegalArgumentException("Unsupported PixelFormat: " + pixelFormat);
    }
}

package org.deltacv.mackjpeg.turbojpeg;

import org.deltacv.mackjpeg.PixelFormat;
import org.libjpegturbo.turbojpeg.TJ;

public final class TJPixelFormatMapper {
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

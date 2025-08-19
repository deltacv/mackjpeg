package org.deltacv.mackjpeg;

import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;
import org.libjpegturbo.turbojpeg.TJLoader;

@SuppressWarnings("unused")
public final class MackJPEG {
    private MackJPEG() {
        // Prevent instantiation
    }

    public JPEGBackend getSupportedBackend() {
        if(TurboJPEGBackend.INSTANCE.isSupported()) {
            return TurboJPEGBackend.INSTANCE;
        } else {
            return null;
        }
    }
}

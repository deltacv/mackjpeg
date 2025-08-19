package org.deltacv.mackjpeg.turbojpeg;

import org.deltacv.mackjpeg.JPEGBackend;
import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.JPEGDecompressor;
import org.libjpegturbo.turbojpeg.TJLoader;

public final class TurboJPEGBackend implements JPEGBackend {
    public static final TurboJPEGBackend INSTANCE = new TurboJPEGBackend();

    static {
        TJLoader.load();
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

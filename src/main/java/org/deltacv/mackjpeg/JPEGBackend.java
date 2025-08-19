package org.deltacv.mackjpeg;

public interface JPEGBackend {
    JPEGCompressor makeCompressor();
    JPEGDecompressor makeDecompressor();

    boolean isSupported();
    String getName();
}
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

package org.libjpegturbo.turbojpeg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

/**
 * Loader for the TurboJPEG native library.
 * Library users shouln't need to call this directly,
 */
public final class TJLoader {
    private static boolean isLoaded = false;

    public static boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Loads the TurboJPEG native library.
     * If the library is not supported on the current OS/Arch,
     * it will throw a RuntimeException.
     * <p>
     * This method should be called before using any TurboJPEG functionality.
     * It automatically detects the OS and architecture to load the correct library.
     */
    public static void load() {
        // get os and arch
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String arch = System.getProperty("os.arch").toLowerCase(Locale.getDefault());

        String libPath = getLibPath(arch, os);

        if (libPath == null) {
            isLoaded = false;
            throw new UnsupportedOperationException("Unsupported OS/Arch: " + os + " " + arch);
        }

        loadFromResource(libPath);
        isLoaded = true;
    }

    private static String getLibPath(String arch, String os) {
        boolean isArm = arch.contains("arm") || arch.contains("aarch64");

        String libPath = "/tj/";

        if(os.contains("win")) {
            libPath += "turbojpeg.dll";
        } else if (os.contains("mac")) {
            libPath += isArm ? "libturbojpeg-arm64.dylib" : "libturbojpeg-amd64.dylib";
        } else if (os.contains("nix") || os.contains("nux")) {
            libPath += isArm ? "libturbojpeg-arm64.so" : "libturbojpeg-amd64.so";
        } else {
            // Unsupported OS
            libPath = null;
        }
        return libPath;
    }

    private static void loadFromResource(String resource) {
        try (InputStream res = TJLoader.class.getResourceAsStream(resource)) {
            if (res == null) {
                throw new FileNotFoundException("Native lib not found: " + resource);
            }
            // Create temp file
            File tempFile = File.createTempFile("libturbojpeg", getFileExtension(resource));
            tempFile.deleteOnExit();

            // Copy content
            Files.copy(res, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Load library
            System.load(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileExtension(String path) {
        if (path.endsWith(".dll")) return ".dll";
        if (path.endsWith(".so")) return ".so";
        if (path.endsWith(".dylib")) return ".dylib";
        return "";
    }
}
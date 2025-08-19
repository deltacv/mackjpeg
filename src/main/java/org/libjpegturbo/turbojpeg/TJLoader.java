package org.libjpegturbo.turbojpeg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

public class TJLoader {
    private static boolean isLoaded = false;

    public static boolean isLoaded() {
        return isLoaded;
    }

    public static void load() {
        // get os and arch
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String arch = System.getProperty("os.arch").toLowerCase(Locale.getDefault());

        String libPath = null;

        if (os.contains("win")) {
            if (arch.contains("64")) {
                libPath = "/tj/windows_64/turbojpeg.dll";
            } else {
                libPath = "/tj/windows_32/turbojpeg.dll";
            }
        } else if (os.contains("linux")) {
            if (arch.contains("64")) {
                libPath = "/tj/linux_64/libturbojpeg.so";
            } else {
                libPath = "/tj/linux_32/libturbojpeg.so";
            }
        } else if (os.contains("mac") || os.contains("darwin")) {
            if (arch.contains("64")) {
                libPath = "/tj/osx_64/libturbojpeg.dylib";
            } else if (arch.contains("ppc")) {
                libPath = "/tj/osx_ppc/libturbojpeg.dylib";
            } else {
                libPath = "/tj/osx_32/libturbojpeg.dylib";
            }
        }

        if (libPath == null) {
            isLoaded = false;
            throw new RuntimeException("Unsupported OS/Arch: " + os + " " + arch);
        }

        loadFromResource(libPath);
        isLoaded = true;
    }

    private static void loadFromResource(String resource) {
        try (InputStream res = TJLoader.class.getResourceAsStream(resource)) {
            if (res == null) {
                throw new RuntimeException("Native lib not found: " + resource);
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

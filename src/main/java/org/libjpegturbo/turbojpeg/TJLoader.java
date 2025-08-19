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

        String libPath = getLibPath(arch, os);

        if (libPath == null) {
            isLoaded = false;
            throw new RuntimeException("Unsupported OS/Arch: " + os + " " + arch);
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
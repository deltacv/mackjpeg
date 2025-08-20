# mackjpeg

`mackjpeg` is a lightweight Java library for **fast JPEG compression and decompression** that provides a simple interface swappable to any supported backend at any time, mainly powered by [libjpeg-turbo](https://libjpeg-turbo.org/).  

---

## Features

- High-performance compression/decompression using TurboJPEG
- Pluggable backends (TurboJPEG and more in the future)
- Simple object-oriented API (`JPEGCompressor`, `JPEGDecompressor`)

---

## Getting Started

### Installation

Add the dependency to your project (coming soon to Maven Central):

```xml
<dependency>
  <groupId>org.deltacv</groupId>
  <artifactId>mackjpeg</artifactId>
  <version>1.0.0</version>
</dependency>
````

Or if using **Gradle**:

```gradle
implementation "org.deltacv:mackjpeg:1.0.0"
```

---

## Usage Examples

### 1. Checking backend availability

```java
import org.deltacv.mackjpeg.JPEGBackend;
import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;

public class Example {
    public static void main(String[] args) {
        JPEGBackend backend = TurboJPEGBackend.INSTANCE;

        if (backend.isSupported()) {
            System.out.println("TurboJPEG backend is available!");
        } else {
            System.err.println("TurboJPEG not supported on this platform.");
        }
    }
}
```

---

### 2. Creating a JPEG compressor

```java
import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;

public class CompressionExample {
    public static void main(String[] args) {
        try (JPEGCompressor compressor = TurboJPEGBackend.INSTANCE.makeCompressor()) {
            System.out.println("JPEG compressor created successfully!");
            // Here you could pass raw pixel data and compress to JPEG
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

### 3. Decompressing a JPEG image

```java
import org.deltacv.mackjpeg.JPEGDecompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.deltacv.mackjpeg.turbojpeg.TurboJPEGBackend;

import java.io.IOException;

public class DecompressionExample {
    public static void main(String[] args) {
        try (JPEGDecompressor decompressor = TurboJPEGBackend.INSTANCE.makeDecompressor()) {
            
            // Load JPEG file from resources
            byte[] jpegBytes = DecompressionExample.class
                    .getResourceAsStream("/test.jpeg")
                    .readAllBytes();

            // Set input JPEG data
            decompressor.setJPEG(jpegBytes, jpegBytes.length);

            System.out.println("Image size: " +
                decompressor.getDecodedWidth() + "x" + decompressor.getDecodedHeight());

            // Decompress into RGB pixel format
            byte[] pixels = decompressor.decompress(PixelFormat.RGB);

            System.out.println("Decompressed pixel buffer length: " + pixels.length);

        } catch (IOException | JPEGException e) {
            e.printStackTrace();
        }
    }
}
```

---

## Development

Clone the repository:

```bash
git clone https://github.com/deltacv/mackjpeg.git
cd mackjpeg
```

Build and run tests:

```bash
./gradlew clean build
```

## License

This project is licensed under the **MIT License**.
See [LICENSE](LICENSE) for details.

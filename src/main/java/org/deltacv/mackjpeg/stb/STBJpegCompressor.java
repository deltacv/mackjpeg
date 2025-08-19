package org.deltacv.mackjpeg.stb;

import org.deltacv.mackjpeg.JPEGCompressor;
import org.deltacv.mackjpeg.PixelFormat;
import org.deltacv.mackjpeg.exception.JPEGException;
import org.lwjgl.stb.STBIWriteCallback;
import org.lwjgl.stb.STBIWriteCallbackI;
import org.lwjgl.stb.STBImageWrite;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

public class STBJpegCompressor implements JPEGCompressor {

    ByteBuffer imageBuffer;

    int width;
    int height;
    PixelFormat pixelFormat;

    int jpegQuality = 50;

    @Override
    public void setImage(byte[] image, int width, int height, PixelFormat pixelFormat) throws JPEGException {
        if(imageBuffer != null) {
            MemoryUtil.memFree(imageBuffer);
        }

        try {
            imageBuffer = MemoryUtil.memAlloc(image.length);
            imageBuffer.put(image);
            imageBuffer.flip();
        } catch(Throwable e) {
            throw new JPEGException("Failed to allocate memory for image buffer", e);
        }

        this.width = width;
        this.height = height;
        this.pixelFormat = pixelFormat;
    }

    @Override
    public void setQuality(int quality) throws JPEGException {
        jpegQuality = quality;
    }

    @Override
    public void compress(byte[] out) throws JPEGException {
        if (imageBuffer == null) {
            throw new JPEGException("Image buffer is not set");
        }

        STBImageWrite.stbi_write_jpg_to_func((STBIWriteCallbackI) (context, dataPointer, size) -> {
            ByteBuffer data = STBIWriteCallback.getData(dataPointer, size);
            if (data.remaining() > out.length) {
                throw new IllegalArgumentException("Output buffer is too small");
            }

        }, 0, width, height, pixelFormat.getChannels(), imageBuffer, jpegQuality);
    }

    @Override
    public byte[] compress() throws JPEGException {
        return new byte[0];
    }
}

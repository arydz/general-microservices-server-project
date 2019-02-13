package com.learning.arydz.server.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Convenience class for loading files to memory. It is prepared only for smaller files. Less then 1MB.
 *
 * <p><code>FileLoader</code> is meant for loading streams of characters to memory.
 *
 */
public class FileLoader {

    private final Logger logger = LoggerFactory.getLogger(FileLoader.class);
    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    public byte[] load() {
        File file = getFileFromResources();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return getBytesFromDataFile(file, fileInputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return throwErrorMessage();
    }

    private File getFileFromResources() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            logger.error("No resource found!");
            throw new IllegalStateException("No resource found!");
        }
        return new File(resource.getFile());
    }

    private byte[] getBytesFromDataFile(File file, FileInputStream fileInputStream) throws IOException {
        byte[] data = new byte[(int) file.length()];
        int count;
        while ((count = fileInputStream.read(data)) > 0) {
            logger.info("Loaded bytes {}", count);
        }
        return data;
    }

    private byte[] throwErrorMessage() {
        String wrongResult = "Some problem occurs. No data read!";
        logger.error(wrongResult);
        throw new IllegalStateException(wrongResult);
    }
}

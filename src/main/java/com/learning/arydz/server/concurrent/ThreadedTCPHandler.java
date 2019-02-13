package com.learning.arydz.server.concurrent;

import com.learning.arydz.server.input.FileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadedTCPHandler implements Runnable {

    private static final String FILE_NAME_TO_BROADCAST = "data/2010_Population_By_Town.csv";
    private final Logger logger = LoggerFactory.getLogger(ThreadedTCPHandler.class);

    private final Socket socket;

    public ThreadedTCPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        logger.info("Spawning new connection {}",  Thread.currentThread().getName());
        FileLoader fileLoader = new FileLoader(FILE_NAME_TO_BROADCAST);
        try (OutputStream outputStream = socket.getOutputStream()) {
            byte[] data = fileLoader.load();
            sendFileToClient(outputStream, data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendFileToClient(OutputStream outputStream, byte[] data) throws IOException {
        outputStream.write(data, 0, data.length);
        outputStream.flush();
        socket.shutdownOutput();
        socket.close();
    }
}

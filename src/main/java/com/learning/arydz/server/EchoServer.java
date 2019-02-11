package com.learning.arydz.server;

import com.learning.arydz.server.concurrent.ThreadedEchoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    private static Logger logger = LoggerFactory.getLogger(ThreadedEchoHandler.class);

    private EchoServer() {

    }

    public static void start(Thread mainThread) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            listenClientConnection(mainThread, serverSocket);
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static void listenClientConnection(Thread mainThread, ServerSocket serverSocket) throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        while (mainThread.isAlive()) {
            Socket incoming = serverSocket.accept();
            executor.submit(new ThreadedEchoHandler(incoming));
        }
    }

}

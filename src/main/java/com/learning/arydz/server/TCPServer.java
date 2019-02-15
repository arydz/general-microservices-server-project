package com.learning.arydz.server;

import com.learning.arydz.server.concurrent.ThreadedTCPHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    private static final int SOCKET_PORT = 8189;

    private static final Logger logger = LoggerFactory.getLogger(ThreadedTCPHandler.class);

    private TCPServer() {

    }

    public static void start(Thread mainThread) {
        ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
        try (ServerSocket serverSocket = ssf.createServerSocket(SOCKET_PORT)) {
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
            executor.submit(new ThreadedTCPHandler(incoming));
        }
    }

}

package com.learning.arydz;

import com.learning.arydz.server.TCPServer;

public class Application {

    public static void main(String... args) {
        TCPServer.start(Thread.currentThread());
    }
}

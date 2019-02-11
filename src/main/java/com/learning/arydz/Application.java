package com.learning.arydz;

import com.learning.arydz.server.EchoServer;

public class Application {

    public static void main(String... args) {
        EchoServer.start(Thread.currentThread());
    }
}

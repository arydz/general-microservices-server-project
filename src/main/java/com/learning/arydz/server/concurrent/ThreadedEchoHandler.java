package com.learning.arydz.server.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ThreadedEchoHandler.class);

    private Socket incoming;

    public ThreadedEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try (InputStream inStream = incoming.getInputStream(); OutputStream outStream = incoming.getOutputStream()) {
            logger.info(Thread.currentThread().getName());
            Scanner in = new Scanner(inStream, StandardCharsets.UTF_8.name());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8.name()), true);
            out.println("Hello! Write BYE, to exit.");

            showEcho(in, out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void showEcho(Scanner in, PrintWriter out) {
        boolean done = false;
        while (!done && in.hasNextLine()) {
            String line = in.nextLine();
            out.println("Echo: " + line);
            if (line.trim().equals("BYE")) {
                done = true;
            }
        }
    }

}

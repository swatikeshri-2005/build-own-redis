package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisServer {

    private final int port;

    public RedisServer(int port) {
        this.port = port;
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Redis Server Started...");
            System.out.println("Listening on port " + port);

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Client Connected");

                ConnectionManager.handle(socket);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
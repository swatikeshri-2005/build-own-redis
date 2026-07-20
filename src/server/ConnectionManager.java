package server;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionManager {

    private static final ExecutorService pool =
            Executors.newCachedThreadPool();

    public static void handle(Socket socket) {

        pool.execute(new ClientHandler(socket));

    }

}
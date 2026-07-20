package server;

import commands.Command;
import commands.CommandRegistry;
import protocol.RESPParser;
import protocol.RESPWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            OutputStream out =
                    socket.getOutputStream();

            while (true) {

                List<String> command =
                        RESPParser.parse(reader);

                if (command == null)
                    break;

                if (command.isEmpty())
                    continue;

                System.out.println("Received : " + command);

                String cmd = command.get(0);

                Command redisCommand =
                        CommandRegistry.get(cmd);

                if (redisCommand == null) {

                    RESPWriter.writeError(
                            out,
                            "Unknown Command");

                    continue;
                }

                redisCommand.execute(out, command);

            }

            socket.close();

        } catch (Exception e) {

            System.out.println("Client disconnected.");

        }

    }

}
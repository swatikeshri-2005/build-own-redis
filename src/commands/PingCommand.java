package commands;

import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class PingCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        RESPWriter.writeSimpleString(out, "PONG");

    }
}
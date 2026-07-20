package commands;

import database.ExpiryManager;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class TTLCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for TTL");

            return;
        }

        RESPWriter.writeInteger(
                out,
                (int) ExpiryManager.ttl(args.get(1)));
    }
}
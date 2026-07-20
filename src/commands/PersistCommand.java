package commands;

import database.ExpiryManager;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class PersistCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for PERSIST");

            return;
        }

        ExpiryManager.remove(args.get(1));

        RESPWriter.writeInteger(out, 1);
    }
}
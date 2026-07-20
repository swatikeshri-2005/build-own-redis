package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class ExistsCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for EXISTS");

            return;
        }

        int exists = RedisDatabase.exists(args.get(1)) ? 1 : 0;

        RESPWriter.writeInteger(out, exists);
    }
}
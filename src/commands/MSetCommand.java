package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class MSetCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if ((args.size() - 1) % 2 != 0) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for MSET");

            return;
        }

        for (int i = 1; i < args.size(); i += 2) {

            RedisDatabase.set(
                    args.get(i),
                    args.get(i + 1));

        }

        RESPWriter.writeSimpleString(out, "OK");
    }
}
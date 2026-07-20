package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class GetCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for GET");

            return;
        }

        RESPWriter.writeBulkString(
                out,
                RedisDatabase.get(args.get(1)));

    }
}
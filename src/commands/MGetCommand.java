package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MGetCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for MGET");

            return;
        }

        List<String> values = new ArrayList<>();

        for (int i = 1; i < args.size(); i++) {

            values.add(
                    RedisDatabase.get(args.get(i)));

        }

        RESPWriter.writeBulkArray(out, values);
    }
}
package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class IncrCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 2) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for INCR");

            return;
        }

        try {

            int value = RedisDatabase.increment(args.get(1));

            RESPWriter.writeInteger(out, value);

        } catch (NumberFormatException e) {

            RESPWriter.writeError(out,
                    "value is not an integer");

        }

    }
}
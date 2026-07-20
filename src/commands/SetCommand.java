package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class SetCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 3) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for SET");

            return;
        }

        RedisDatabase.set(
                args.get(1),
                args.get(2));

        RESPWriter.writeSimpleString(out, "OK");

    }
}
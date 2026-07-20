package commands;

import database.ExpiryManager;
import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class ExpireCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        if (args.size() < 3) {

            RESPWriter.writeError(out,
                    "wrong number of arguments for EXPIRE");

            return;
        }

        String key = args.get(1);
        int seconds = Integer.parseInt(args.get(2));

        if (!RedisDatabase.exists(key)) {

            RESPWriter.writeInteger(out, 0);
            return;
        }

        ExpiryManager.expire(key, seconds);

        RESPWriter.writeInteger(out, 1);
    }
}
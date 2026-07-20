package commands;

import database.RedisDatabase;
import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public class KeysCommand implements Command {

    @Override
    public void execute(OutputStream out, List<String> args)
            throws Exception {

        Set<String> keys = RedisDatabase.keys();

        RESPWriter.writeArray(out, keys);
    }
}
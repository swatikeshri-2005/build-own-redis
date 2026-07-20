package commands;

import protocol.RESPWriter;

import java.io.OutputStream;
import java.util.List;

public class EchoCommand implements Command{

    @Override
      public void execute(OutputStream out, List<String> args)
      throws Exception{
        if(args.size() < 2){
                 RESPWriter.writeError(out,
                    "wrong number of arguments for ECHO");

            return;
        }
           RESPWriter.writeBulkString(out, args.get(1));
      }
}
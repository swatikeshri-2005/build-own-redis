package commands;

import java.io.OutputStream;
import java.util.List;

public interface Command {

     void execute(OutputStream out, List<String> args)
            throws Exception;
}

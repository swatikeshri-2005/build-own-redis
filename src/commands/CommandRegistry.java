package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry{
 private static final Map<String, Command> commands =
            new HashMap<>();

         private CommandRegistry() {
    }
    
      public static void register(String name, Command command) {
        commands.put(name.toUpperCase(), command);
    }

      public static Command get(String name) {
        return commands.get(name.toUpperCase());
    }

      public static void initialize() {

        register("PING", new PingCommand());
        register("ECHO", new EchoCommand());

        register("SET", new SetCommand());
        register("GET", new GetCommand());

        register("DEL", new Delcommand());
        register("EXISTS", new ExistsCommand());
        register("KEYS", new KeysCommand());

        register("EXPIRE", new ExpireCommand());
        register("TTL", new TTLCommand());
        register("PERSIST", new PersistCommand());

        register("INCR", new IncrCommand());
        register("DECR", new DecrCommand());

        register("MSET", new MSetCommand());
        register("MGET", new MGetCommand());
    }
}
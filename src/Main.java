import commands.CommandRegistry;
import server.RedisServer;

public class Main {

    public static void main(String[] args) {

        CommandRegistry.initialize();

        RedisServer server =
                new RedisServer(6379);

        server.start();

    }

}
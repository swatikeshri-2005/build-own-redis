package protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RESPParser {

    public static List<String> parse(BufferedReader reader)
            throws IOException {

        String first = reader.readLine();

        if (first == null)
            return null;

        if (!first.startsWith("*"))
            throw new IOException("Invalid RESP Array");

        int count = Integer.parseInt(first.substring(1));

        List<String> command = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String bulkHeader = reader.readLine();

            if (bulkHeader == null || !bulkHeader.startsWith("$")) {
                throw new IOException("Invalid Bulk String");
            }

            String value = reader.readLine();

            command.add(value);
        }

        return command;
    }
}
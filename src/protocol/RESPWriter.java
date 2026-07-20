package protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public class RESPWriter {

    // +OK, +PONG
    public static void writeSimpleString(OutputStream out, String message)
            throws IOException {

        out.write(("+" + message + "\r\n").getBytes());
        out.flush();
    }

    // -ERR message
    public static void writeError(OutputStream out, String message)
            throws IOException {

        out.write(("-ERR " + message + "\r\n").getBytes());
        out.flush();
    }

    // $5\r\nHello\r\n
    public static void writeBulkString(OutputStream out, String value)
            throws IOException {

        if (value == null) {
            out.write("$-1\r\n".getBytes());
        } else {
            out.write(("$" + value.length() + "\r\n").getBytes());
            out.write((value + "\r\n").getBytes());
        }

        out.flush();
    }

    // :1
    public static void writeInteger(OutputStream out, int value)
            throws IOException {

        out.write((":" + value + "\r\n").getBytes());
        out.flush();
    }

    // KEYS response
    public static void writeArray(OutputStream out, Set<String> values)
            throws IOException {

        out.write(("*" + values.size() + "\r\n").getBytes());

        for (String value : values) {

            out.write(("$" + value.length() + "\r\n").getBytes());
            out.write((value + "\r\n").getBytes());
        }

        out.flush();
    }

    // MGET response
    public static void writeBulkArray(OutputStream out, List<String> values)
            throws IOException {

        out.write(("*" + values.size() + "\r\n").getBytes());

        for (String value : values) {

            if (value == null) {

                out.write("$-1\r\n".getBytes());

            } else {

                out.write(("$" + value.length() + "\r\n").getBytes());
                out.write((value + "\r\n").getBytes());
            }
        }

        out.flush();
    }
}
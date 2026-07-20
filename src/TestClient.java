import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 6379);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream();
                Scanner scanner = new Scanner(System.in)
        ) {

            System.out.println("Connected to Redis");
            System.out.println("Type exit to quit");

            while (true) {

                System.out.print("redis> ");

                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit"))
                    break;

                String resp = encode(input);

                out.write(resp.getBytes());
                out.flush();

                printResponse(reader);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private static String encode(String input) {

        String[] parts = input.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();

        sb.append("*").append(parts.length).append("\r\n");

        for (String p : parts) {

            sb.append("$")
              .append(p.length())
              .append("\r\n");

            sb.append(p)
              .append("\r\n");
        }

        return sb.toString();
    }

    private static void printResponse(BufferedReader reader)
            throws IOException {

        String first = reader.readLine();

        if (first == null)
            return;

        switch (first.charAt(0)) {

            case '+':
            case '-':
            case ':':
                System.out.println(first);
                break;

            case '$':

                int len = Integer.parseInt(first.substring(1));

                if (len == -1) {

                    System.out.println("(nil)");

                } else {

                    System.out.println(reader.readLine());

                }

                break;
            case '*':
               int count = Integer.parseInt(first.substring(1));

    for (int i = 0; i < count; i++) {

        String header = reader.readLine();

        if (header.equals("$-1")) {

            System.out.println("(nil)");

        } else {

            System.out.println(reader.readLine());

        }

    }
           
break;

            default:

                System.out.println(first);

        }

    }
}
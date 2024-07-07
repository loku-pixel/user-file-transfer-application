import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String prompt = reader.readLine();
                System.out.println("Server says: " + prompt);

                System.out.print("Enter an expression (e.g., 2 + 3), or type 'exit' to close: ");
                String input = userInput.readLine();
                writer.println(input);
                writer.flush(); // Ensure immediate sending

                if (input.equalsIgnoreCase("exit")) {
                    break; // Exit the loop and close the connection
                }

                String result = reader.readLine();
                System.out.println("Server says: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

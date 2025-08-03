import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);

        // Set up reader and writer
        BufferedReader serverMessageReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Read welcome message from server
        String welcomeMessage = serverMessageReader.readLine();
        System.out.println("Server: " + welcomeMessage);

        // Prompt user
        System.out.print("Write to server: ");

        // Read input from user
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String messageToSend = input.readLine();

        // Send message to server
        writer.write(messageToSend);
        writer.newLine();
        writer.flush();

        // Read second server response
        String serverReply = serverMessageReader.readLine();
        System.out.println("Server: " + serverReply);

        // Close everything
        input.close();
        writer.close();
        serverMessageReader.close();
        socket.close();
    }
}

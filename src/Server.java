import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("Waiting for a connection");
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();
        System.out.println("A client has connected");

        // Use a single BufferedWriter for all server output
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Send welcome message
        output.write("Welcome to the server!");
        output.newLine();
        output.flush();

        // Receive message from the client
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = input.readLine();
        System.out.println("Client says: " + message);

        // Send a second response back
        String reply = "Server received: " + message;
        output.write(reply);
        output.newLine();
        output.flush();

        // Clean up
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}

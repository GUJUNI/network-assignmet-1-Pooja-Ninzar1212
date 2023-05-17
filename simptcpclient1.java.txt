//Q-1(B) Write a TCP code in java to send a String and convert into uppercase  with output of the code
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        // Create a socket and connect to the server
        Socket socket = new Socket("localhost", 1234);

        // Create a PrintWriter for sending messages to the server
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Send a String to the server
        String message = "Hello, world!";
        out.println(message);
        out.flush();

        // Create a BufferedReader for receiving messages from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Read the server's response and convert it to uppercase
        String response = in.readLine();
        String uppercaseResponse = response.toUpperCase();
        System.out.println("Server response: " + uppercaseResponse);

        // Close the socket and streams
        out.close();
        in.close();
        socket.close();
    }
}



// Server started. Listening on port 1234...
// Received message: Hello, world!
// Server response: HELLO, WORLD!
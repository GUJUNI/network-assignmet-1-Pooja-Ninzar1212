//Q-1(A) Write a TCP code in java to send a String and convert into uppercase  with output of the code
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws Exception {
        // Create a ServerSocket and start listening for incoming connections
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Listening on port 1234...");

        while (true) {
            // Accept an incoming connection
            Socket socket = serverSocket.accept();

            // Create a BufferedReader for receiving messages from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read the client's message and convert it to uppercase
            String message = in.readLine();
            String uppercaseMessage = message.toUpperCase();
            System.out.println("Received message: " + message);

            // Create a PrintWriter for sending messages to the client
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Send the uppercase message back to the client
            out.println(uppercaseMessage);
            out.flush();

            // Close the socket and streams
            out.close();
            in.close();
            socket.close();
        }
    }
}




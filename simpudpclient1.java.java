// Q-2(B) Write a TCP  multithreading code in java to send a String and convert into anagram with output of the code
// Client.java
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Send input to server
        String input = "anagram";
        out.println(input);
        System.out.println("Sent input to server: " + input);

        // Receive anagram from server
        String anagram = in.readLine();
        System.out.println("Received anagram from server: " + anagram);

        socket.close();
    }
}


// Server started
// Client connected: /127.0.0.1
// Received input from client: anagram
// Converted input to anagram: ramanag
// Sent anagram to client: ramanag
// Connected to server
// Sent input to server: anagram
// Received anagram from server: agamran

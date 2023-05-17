// Q-2(A) Write a TCP  multithreading code in java to send a String and convert into anagram with output of the code
// Server.java
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());
            new Thread(new Worker(socket)).start();
        }
    }
}

class Worker implements Runnable {
    private Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read input from client
            String input = in.readLine();
            System.out.println("Received input from client: " + input);

            // Convert input to anagram
            String anagram = convertToAnagram(input);
            System.out.println("Converted input to anagram: " + anagram);

            // Send anagram to client
            out.println(anagram);
            System.out.println("Sent anagram to client: " + anagram);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToAnagram(String input) {
        char[] chars = input.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }
}

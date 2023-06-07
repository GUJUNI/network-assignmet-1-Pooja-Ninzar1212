//4(A)Write a java code  UDP code in java to find lcm of two number with output of the code
import java.net.*;

public class UDPLCMCalculatorClient {
    private static final int PORT = 8888;
    private static final String HOST = "localhost";
    private static DatagramSocket socket;

    public static void main(String[] args) {
        try {
            socket = new DatagramSocket();
            System.out.println("UDP Client LCM Calculator is running...");

            // Send first number to the server
            int num1 = 12;
            byte[] num1Bytes = String.valueOf(num1).getBytes();
            DatagramPacket num1Packet = new DatagramPacket(num1Bytes, num1Bytes.length, InetAddress.getByName(HOST), PORT);
            socket.send(num1Packet);
            System.out.println("Sent Number 1: " + num1);

            // Send second number to the server
            int num2 = 18;
            byte[] num2Bytes = String.valueOf(num2).getBytes();
            DatagramPacket num2Packet = new DatagramPacket(num2Bytes, num2Bytes.length, InetAddress.getByName(HOST), PORT);
            socket.send(num2Packet);
            System.out.println("Sent Number 2: " + num2);

            // Receive the LCM from the server
            byte[] lcmBytes = new byte[1024];
            DatagramPacket lcmPacket = new DatagramPacket(lcmBytes, lcmBytes.length);
            socket.receive(lcmPacket);
            int lcm = Integer.parseInt(new String(lcmPacket.getData()).trim());
            System.out.println("Received LCM: " + lcm);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/* output:
 * UDP Server LCM Calculator is running...
   Received Number 1: 12
   Received Number 2: 18
   Sent LCM: 36 
 */
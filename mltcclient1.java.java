// 3(A) Write a java code  UDP  multithreading code in java to check Prime number  with output of the code
import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 1234;

            while (true) {
                System.out.print("Enter a number (0 to exit): ");
                String number = scanner.nextLine();

                if (number.equals("0")) {
                    break;
                }

                byte[] sendData = number.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
                datagramSocket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);

                String result = new String(receivePacket.getData());
                System.out.println(result.trim());
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (datagramSocket != null) {



// Enter a number (0 to exit): 7
// 7 is a prime number
// Enter a number (0 to exit): 21
// 21 is not a prime number
// Enter a number (0 to exit): 31
// 31 is a prime number
// Enter a number (0 to exit): 0

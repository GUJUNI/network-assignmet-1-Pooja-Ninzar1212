//4(B).Write a java code  UDP code in java to find lcm of two number with output of the code.

import java.net.*;

public class UDPLCMCalculatorServer {
    private static final int PORT = 8888;
    private static DatagramSocket socket;

    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(PORT);
            System.out.println("UDP Server LCM Calculator is running...");

            while (true) {
                // Receive first number from the client
                byte[] num1Bytes = new byte[1024];
                DatagramPacket num1Packet = new DatagramPacket(num1Bytes, num1Bytes.length);
                socket.receive(num1Packet);
                int num1 = Integer.parseInt(new String(num1Packet.getData()).trim());
                System.out.println("Received Number 1: " + num1);

                // Receive second number from the client
                byte[] num2Bytes = new byte[1024];
                DatagramPacket num2Packet = new DatagramPacket(num2Bytes, num2Bytes.length);
                socket.receive(num2Packet);
                int num2 = Integer.parseInt(new String(num2Packet.getData()).trim());
                System.out.println("Received Number 2: " + num2);

                // Calculate the LCM
                int lcm = calculateLCM(num1, num2);

                // Send the LCM back to the client
                byte[] lcmBytes = String.valueOf(lcm).getBytes();
                DatagramPacket lcmPacket = new DatagramPacket(lcmBytes, lcmBytes.length, num1Packet.getAddress(), num1Packet.getPort());
                socket.send(lcmPacket);
                System.out.println("Sent LCM: " + lcm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private static int calculateLCM(int num1, int num2) {
        int gcd = calculateGCD(num1)



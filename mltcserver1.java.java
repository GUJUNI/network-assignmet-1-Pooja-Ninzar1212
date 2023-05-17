// 3(A) Write a java code  UDP  multithreading code in java to check Prime number  with output of the code
import java.net.*;
import java.util.*;

public class UDPServer {
    public static void main(String args[]) {
        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket(1234);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                String number = new String(receivePacket.getData());
                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                Thread t = new PrimeThread(number, clientIP, clientPort, datagramSocket);
                t.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}

class PrimeThread extends Thread {
    private String number;
    private InetAddress clientIP;
    private int clientPort;
    private DatagramSocket datagramSocket;

    public PrimeThread(String number, InetAddress clientIP, int clientPort, DatagramSocket datagramSocket) {
        this.number = number.trim();
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.datagramSocket = datagramSocket;
    }

    public void run() {
        int num = Integer.parseInt(number);
        boolean isPrime = true;

        if (num < 2) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        String result;
        if (isPrime) {
            result = num + " is a prime number";
        } else {
            result = num + " is not a prime number";
        }

        byte[] sendData = result.getBytes();

        try {
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
            datagramSocket.send(sendPacket);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

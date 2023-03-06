package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket("localHost", 1234);
//
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\joshu\\Documents\\Level1Test.txt");
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//
//        InputStream is = socket.getInputStream();
//        byte[] contents = new byte[10000];
//
//        int bytesRead;
//        while((bytesRead = is.read(contents)) != -1) {
//            bos.write(contents, 0, bytesRead);
//        }
//
//        bos.flush();
//        fos.flush();
//        System.out.println("File saved successfully!");
//
//        bos.close();
//        fos.close();
//        is.close();
//        socket.close();
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        byte[] sendData = "send file".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
        clientSocket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String fileContents = new String(receivePacket.getData());

        System.out.println("Received file contents:");
        System.out.println(fileContents);

        clientSocket.close();
    }
}
package app.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(1234);
//        serverSocket.setReuseAddress(true);
//        System.out.println("Server listening on port 1234...");
        DatagramSocket serverSocket = new DatagramSocket(1234);
        System.out.println("Server listening on port 1234...");

        while (true) {
//            Socket clientSocket = serverSocket.accept();
//            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
//            Thread t = new Thread(new ClientHandler(clientSocket));
//            t.start();

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            System.out.println("Received packet from client: " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());

            String fileContents = readFileContents("C:\\Users\\joshu\\Documents\\TestTCP1.txt");
            byte[] sendData = fileContents.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            System.out.println("File sent to client: " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());
        }
    }

    private static String readFileContents(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }

        br.close();
        return sb.toString();
    }

//    static class ClientHandler implements Runnable {
//        private final Socket clientSocket;
//
//        public ClientHandler(Socket socket) {
//            this.clientSocket = socket;
//        }
//
//        @Override
//        public void run() {
//            try {
//
//                File file = new File("C:\\Users\\joshu\\Documents\\Level1.txt");
//                FileInputStream fis = new FileInputStream(file);
//                BufferedInputStream bis = new BufferedInputStream(fis);
//
//                OutputStream os = clientSocket.getOutputStream();
//                byte[] contents;
//                long fileLength = file.length();
//                long current = 0;
//
//                while(current!=fileLength) {
//                    int size = 10000;
//                    if(fileLength - current >= size)
//                        current += size;
//                    else {
//                        size = (int)(fileLength - current);
//                        current = fileLength;
//                    }
//                    contents = new byte[size];
//                    bis.read(contents, 0, size);
//                    os.write(contents);
//                    System.out.println("Sending file ... " + (current*100)/fileLength + "% complete!");
//                }
//        os.flush();
//        bis.close();
//        fis.close();
//        clientSocket.close();
//        System.out.println("File sent to client: " + clientSocket.getInetAddress().getHostAddress());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
}
package app.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2011);
        System.out.println("Server listening on port 2011...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
//                Socket socket = serverSocket.accept();
//                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                File file = new File("C:\\Users\\joshu\\Documents\\Level1.txt");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                OutputStream os = clientSocket.getOutputStream();
                byte[] contents;
                long fileLength = file.length();
                long current = 0;

                while(current!=fileLength) {
                    int size = 10000;
                    if(fileLength - current >= size)
                        current += size;
                    else {
                        size = (int)(fileLength - current);
                        current = fileLength;
                    }
                    contents = new byte[size];
                    bis.read(contents, 0, size);
                    os.write(contents);
                    System.out.println("Sending file ... " + (current*100)/fileLength + "% complete!");
                }

//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

//        String inputLine, outputLine;
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println("Received message from client: " + inputLine);
//            outputLine = "Server received message: " + inputLine;
//            out.println(outputLine);
//            if (inputLine.equals("Bye."))
//                break;
//        }

//        out.close();
//        in.close();
        os.flush();
        bis.close();
        fis.close();
        clientSocket.close();
//        serverSocket.close();
        System.out.println("File sent to client: " + clientSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
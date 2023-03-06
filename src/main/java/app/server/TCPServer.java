package app.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2011);
        System.out.println("Server listening on port 2011...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received message from client: " + inputLine);
            outputLine = "Server received message: " + inputLine;
            out.println(outputLine);
            if (inputLine.equals("Bye."))
                break;
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
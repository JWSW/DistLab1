package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localHost", 2011);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput, serverResponse;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            serverResponse = in.readLine();
            System.out.println("Server response: " + serverResponse);
            if (userInput.equals("Bye."))
                break;
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
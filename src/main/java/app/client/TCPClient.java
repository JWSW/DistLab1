package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localHost", 2011);

        FileOutputStream fos = new FileOutputStream("C:\\Users\\joshu\\Documents\\Level1Test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//
//        String userInput, serverResponse;
//        while ((userInput = stdIn.readLine()) != null) {
//            out.println(userInput);
//            serverResponse = in.readLine();
//            System.out.println("Server response: " + serverResponse);
//            if (userInput.equals("Bye."))
//                break;
//        }

        InputStream is = socket.getInputStream();
        byte[] contents = new byte[10000];

        int bytesRead;
        while((bytesRead = is.read(contents)) != -1) {
            bos.write(contents, 0, bytesRead);
        }

//        out.close();
//        in.close();
//        stdIn.close();
        bos.flush();
        fos.flush();
        System.out.println("File saved successfully!");

        bos.close();
        fos.close();
        is.close();
        socket.close();
    }
}
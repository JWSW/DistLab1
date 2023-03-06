package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localHost", 2011);
//        Socket socket2 = new Socket("localHost2", 2011);

        FileOutputStream fos = new FileOutputStream("C:\\Users\\joshu\\Documents\\Level1Test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
//        FileOutputStream fos2 = new FileOutputStream("C:\\Users\\joshu\\Documents\\Level1Test2.txt");
//        BufferedOutputStream bos2 = new BufferedOutputStream(fos2);

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
//        InputStream is2 = socket2.getInputStream();
//        byte[] contents2 = new byte[10000];

        int bytesRead;
        while((bytesRead = is.read(contents)) != -1) {
            bos.write(contents, 0, bytesRead);
        }
//        int bytesRead2;
//        while((bytesRead2 = is.read(contents2)) != -1) {
//            bos2.write(contents2, 0, bytesRead2);
//        }

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
//
//        bos2.flush();
//        fos2.flush();
//        System.out.println("File 2 saved successfully!");
//
//        bos2.close();
//        fos2.close();
//        is2.close();
//        socket2.close();
    }
}
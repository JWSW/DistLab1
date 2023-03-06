package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localHost", 2011);

        FileOutputStream fos = new FileOutputStream("C:\\Users\\joshu\\Documents\\Level1Test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        InputStream is = socket.getInputStream();
        byte[] contents = new byte[10000];

        int bytesRead;
        while((bytesRead = is.read(contents)) != -1) {
            bos.write(contents, 0, bytesRead);
        }

        bos.flush();
        fos.flush();
        System.out.println("File saved successfully!");

        bos.close();
        fos.close();
        is.close();
        socket.close();
    }
}
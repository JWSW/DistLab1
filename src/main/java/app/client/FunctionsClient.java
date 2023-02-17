package app.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FunctionsClient {
    private static Socket socket;
    private static String hostName;
    private static int portNumber;

    public static void connectToServer()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Geef hostname: ");
        String hostname = sc.nextLine();

        System.out.println("Geef port: ");
        String port = sc.nextLine();
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    hostName = hostname;
                    portNumber = Integer.parseInt(port);
                    socket = new Socket(hostname,Integer.parseInt(port));
                }
                catch (IOException ex)
                {
                    Logger.getLogger(FunctionsClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    private static void reconnectToServer()
    {
        try
        {
            socket = new Socket(hostName, portNumber);
        }
        catch (IOException ex)
        {
            Logger.getLogger(FunctionsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void receiveFile(String outputFilePath)
    {
        InputStream is;
        BufferedInputStream bis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        try
        {
            File output = new File(outputFilePath);
            is = socket.getInputStream();
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(output);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int data;
            while(true)
            {
                data = bis.read(buffer);
                if(data != -1)
                {
                    bos.write(buffer, 0, 1024);
                }
                else
                {
                    bis.close();
                    bos.close();
                    break;
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(FunctionsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        reconnectToServer();
    }
}

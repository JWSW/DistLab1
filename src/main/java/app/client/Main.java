//package org.example;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Geef hostname: ");
        String hostname = sc.nextLine();

        System.out.println("Geef port: ");
        String port = sc.nextLine();

        try
        {

            Runtime.getRuntime().exec(new String[] {"cmd", "/K", "ssh -p "+port+" root@"+hostname});
                    Runtime.getRuntime().exec(new String[] {"cmd", "/K", "ssh -p "+port+" root@"+hostname});
            System.out.println("Het werkt.");
        }
        catch (Exception e)
        {
            System.out.println("Iets fout gegaan");
            e.printStackTrace();
        }

        System.out.println("gwn test");
    }
}
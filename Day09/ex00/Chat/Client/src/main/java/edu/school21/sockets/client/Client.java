package edu.school21.sockets.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void runClient() throws IOException, InterruptedException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
        BufferedWriter writer =
                new BufferedWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream()));

        //Step 1
        String message = reader.readLine();
        System.out.println(message);

        Scanner sc = new Scanner(System.in);
        //Step 2
        String mess1 = sc.nextLine();
        writer.write(mess1);
        writer.newLine();
        writer.flush();

        //Step 3
        message = reader.readLine();
        System.out.println(message);

        //Step 4
        String mess2 = sc.nextLine();
        writer.write(mess2);
        writer.newLine();
        writer.flush();

        //Step 5
        message = reader.readLine();
        System.out.println(message);

        //Step 6
        String mess3 = sc.nextLine();
        writer.write(mess3);
        writer.newLine();
        writer.flush();

        //Step 7
        message = reader.readLine();
        System.out.println(message);

        reader.close();
        writer.close();
        clientSocket.close();
    }
}

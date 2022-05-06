package edu.school21.sockets.client;

import java.io.*;
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

        message = reader.readLine();
        if (message == null) {
            closeEverything(clientSocket, reader, writer);
        }

        if ("signup".equals(mess1.toLowerCase())) {
            //Step 3
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

            String signIn = sc.nextLine();
            writer.write(signIn);
            writer.newLine();
            writer.flush();
        }
        //------------------------

        //message = reader.readLine();
        System.out.println(message);

        String username = sc.nextLine();
        writer.write(username);
        writer.newLine();
        writer.flush();

        message = reader.readLine();
        if (message == null) {
            closeEverything(clientSocket, reader, writer);
        }
        System.out.println(message);

        String password = sc.nextLine();
        writer.write(password);
        writer.newLine();
        writer.flush();
        //------------------------------2

        message = reader.readLine();
        System.out.println(message);
        message = reader.readLine();
        System.out.println(message);
        //------------------------------
        listenForMessage(reader, writer);
        sendMessage(username, reader, writer);

//        reader.close();
//        writer.close();
//        clientSocket.close();
    }

    public void listenForMessage(BufferedReader reader, BufferedWriter writer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msg;

                while (clientSocket.isConnected()) {
                    try {
                        msg = reader.readLine();
                        if (msg == null) {
                            closeEverything(clientSocket,reader,writer);
                            break;
                        }
                        System.out.println(msg);
                    } catch (IOException e) {
                        closeEverything(clientSocket, reader, writer);
                    }
                }
            }
        }).start();
    }

    public void sendMessage(String username, BufferedReader reader, BufferedWriter writer) {
        try {
//            writer.write(username);
//            writer.newLine();
//            writer.flush();
            Scanner scanner = new Scanner(System.in);
            while (clientSocket.isConnected()) {
                String messageToSend = scanner.nextLine();
//                if("exit".equals(messageToSend.toLowerCase())) {
//                    closeEverything(clientSocket, reader ,writer);
//                }
                writer.write(username + ": " + messageToSend);
                writer.newLine();
                writer.flush();
                if("exit".equals(messageToSend.toLowerCase())) {
                    closeEverything(clientSocket, reader ,writer);
                }
            }
        } catch (IOException e) {
            closeEverything(clientSocket, reader, writer);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            System.out.println("You have left the chat.");
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}


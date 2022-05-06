package edu.school21.sockets.server;

import edu.school21.sockets.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientUsername;
    private UserService userService;

    public ClientHandler(Socket socket, UserService userService) {
        try {
            this.socket = socket;
            this.userService = userService;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String response = null;

            writer.write("Enter username:");
            writer.newLine();
            writer.flush();

            String tryUsername = reader.readLine();
            if (userService.validateUser(tryUsername)) {
                this.clientUsername = tryUsername;
                System.out.println("valid user name");
            } else {
                closeEverything(socket, reader, writer);
                return;
            }

            writer.write("Enter password:");
            writer.newLine();
            writer.flush();

            String tryPassword = reader.readLine();
            if (!userService.validatePassword(tryUsername, tryPassword)) {
                System.out.println("password not valid");
                closeEverything(socket, reader, writer);
                return;
            }
            //------------------------------2
            writer.write("SERVER: " + clientUsername + " voshel v chat!");
            writer.newLine();
            writer.write("Start messaging");
            writer.newLine();
            writer.flush();
            clientHandlers.add(this);
            //------------------------------

        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = reader.readLine();
                if (messageFromClient == null) {
                    closeEverything(socket,reader,writer);
                    break;
                }
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        if (messageToSend.split(": ")[1].equals("exit")) {
            closeEverything(socket, reader, writer);
        }
        if (clientHandlers.size() > 0) {
            userService.addMessageToDb(messageToSend);
        }
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.writer.write(messageToSend);
                    clientHandler.writer.newLine();
                    clientHandler.writer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
            }
        }
    }

    public void removeClientHandler() {
        System.out.println("SERVER: " + clientUsername + " pokinula chat!");
        System.out.println(clientHandlers.size() + " clients left");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        clientHandlers.remove(this);
        try {
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
        }
    }
}

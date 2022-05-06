package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.services.UserService;
import edu.school21.sockets.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {

    private ServerSocket serverSocket;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Server() {
    }

    public void setServerSocket(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    //    @Autowired
//    public Server(ServerSocket serverSocket) {
//        this.serverSocket = serverSocket;
//    }

    public void runServer() throws IOException {
        User user = null;
        int count = 0;

        System.out.println("Server started");
        while(true) {
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client accepted " + ++count);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));
            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream()));

            //Step 1
            writer.write("Hello from Server!");
            writer.newLine();
            writer.flush();

            String response = null;
            //Step 2
            response = reader.readLine();
            if ("signup".equals(response.toLowerCase())) {
                //Step 3
                writer.write("Enter username:");
                writer.newLine();
                writer.flush();
                //Step 4
                String username = reader.readLine();
                //Step 5
                writer.write("Enter password:");
                writer.newLine();
                writer.flush();
                //Step 6
                String password = reader.readLine();
                //Step 7
                user = new User(username, passwordEncoder.encode(password));
                userService.signUp(user);

                writer.write("Signup of " + username + " successful!");
                writer.newLine();
                writer.flush();

                response = reader.readLine();
                if (!"signin".equals(response.toLowerCase())) {
                    reader.close();
                    writer.close();
                    clientSocket.close();
                    System.out.println("Client closed by server");
                    continue;
                }
                ClientHandler clientHandler = new ClientHandler(clientSocket, userService);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } else if ("signin".equals(response.toLowerCase())) {
                ClientHandler clientHandler = new ClientHandler(clientSocket, userService);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } else {
                reader.close();
                writer.close();
                clientSocket.close();
                System.out.println("Client closed by server");
            }
        }
    }
}

package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            return;
        }

        String[] array = args[0].split("=");
        int port = Integer.parseInt(array[1]);
        System.out.println("port=" + port);


        Socket clientSocket = new Socket("10.21.34.124", port);

        Client client = new Client(clientSocket);
        client.runClient();
    }
}

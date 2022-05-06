package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            return;
        }

        String[] array = args[0].split("=");
        int port = Integer.parseInt(array[1]);
        System.out.println("port=" + port);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                SocketsApplicationConfig.class
        );

        Server server = context.getBean(Server.class);

        server.setServerSocket(port);
        server.runServer();
    }
}

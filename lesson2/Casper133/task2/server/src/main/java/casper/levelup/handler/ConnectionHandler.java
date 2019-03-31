package casper.levelup.handler;

import casper.levelup.Server;
import casper.levelup.user.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ConnectionHandler implements Runnable {
    private Server server;
    private ServerSocket serverSocket;

    public ConnectionHandler(Server server, ServerSocket serverSocket) {
        this.server = server;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                User user = new User(clientSocket);

                List<User> allUsers = server.getAllUsers();
                allUsers.add(user);
                server.setAllUsers(allUsers);

                MessageHandler messageHandler = new MessageHandler(user, server);
                Thread handleMessageThread = new Thread(messageHandler);
                handleMessageThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

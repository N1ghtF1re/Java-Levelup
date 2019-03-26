package main.casper.levelup;

import main.casper.levelup.connector.SocketClientConnector;
import main.casper.levelup.handler.ConnectionHandler;
import main.casper.levelup.user.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private List<User> allUsers = Collections.synchronizedList(new LinkedList<User>());

    Server() {
        SocketClientConnector clientConnector = new SocketClientConnector();
        clientConnector.initConnection();

        ConnectionHandler connectionHandler = new ConnectionHandler(this, clientConnector.getServerSocket());
        Thread acceptConnectionsThread = new Thread(connectionHandler);
        acceptConnectionsThread.start();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}

package casper.levelup;

import casper.levelup.connector.SocketClientConnector;
import casper.levelup.handler.ConnectionHandler;
import casper.levelup.user.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private List<User> allUsers = Collections.synchronizedList(new LinkedList<User>());
    private SocketClientConnector clientConnector;

    Server() {
        clientConnector = new SocketClientConnector(this);
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

    public SocketClientConnector getClientConnector() {
        return clientConnector;
    }
}

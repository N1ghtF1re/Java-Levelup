package main.casper.levelup.connector;

import main.casper.levelup.user.User;

import java.io.*;
import java.net.ServerSocket;

public class SocketClientConnector implements ClientConnector {
    private ServerSocket serverSocket;

    @Override
    public void initConnection() {
        final int port = 5623;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(User user, String message) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(user.getSocket().getOutputStream()));
            outWriter.write(message);
            outWriter.newLine();
            outWriter.flush();
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }

            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}

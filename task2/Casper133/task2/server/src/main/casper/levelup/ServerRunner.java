package main.casper.levelup;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {
    public static void main(String[] args) {
        /*
        Server server = new Server();
        Thread acceptConnectionsThread = new Thread(server);
        acceptConnectionsThread.start();
        */


        int port = 5623;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String inputMessage = inReader.readLine();
            while (!inputMessage.equalsIgnoreCase("/exit")) {
                System.out.println(inputMessage);
                outWriter.write("Hello, Client! Thank you for message: " + inputMessage);
                outWriter.newLine();
                outWriter.flush();
                inputMessage = inReader.readLine();
            }

            serverSocket.close();
            clientSocket.close();
            inReader.close();
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

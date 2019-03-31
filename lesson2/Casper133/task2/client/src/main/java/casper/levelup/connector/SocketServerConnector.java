package casper.levelup.connector;

import java.io.*;
import java.net.Socket;

public class SocketServerConnector implements ServerConnector {
    private Socket socket;
    private BufferedWriter outWriter;
    private BufferedReader inReader;

    @Override
    public void connect() {
        final int port = 5623;

        try {
            socket = new Socket("localhost", port);
            outWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        try {
            outWriter.write(message);
            outWriter.newLine();
            outWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readMessage() {
        try {
            return inReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disconnect() {
        try {
            socket.close();
            outWriter.close();
            inReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

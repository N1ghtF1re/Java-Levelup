package main.casper.levelup;

import main.casper.levelup.abstraction.AbstractClient;
import main.casper.levelup.connector.ServerConnector;
import main.casper.levelup.connector.SocketServerConnector;
import main.casper.levelup.reader.ConsoleMessageReader;
import main.casper.levelup.reader.MessageReader;
import main.casper.levelup.reader.ServerReader;

public class Client extends AbstractClient {
    private ServerConnector serverConnector = new SocketServerConnector();
    private MessageReader messageReader = new ConsoleMessageReader();
    private boolean clientStopped = false;

    Client() {
        connectToServer();

        ServerReader serverReader = new ServerReader(this);
        Thread serverReaderThread = new Thread(serverReader);
        serverReaderThread.start();

        while (!clientStopped) {
            sendMessage();
        }
    }

    @Override
    public void connectToServer() {
        serverConnector.connect();
    }

    @Override
    public void sendMessage() {
        String message = messageReader.readMessage();

        if (message != null) {
            serverConnector.sendMessage(message);

            if (message.equalsIgnoreCase("/exit")) {
                closeAll();
            }
        }
    }

    @Override
    public void showMessage() {
        String message = serverConnector.readMessage();

        if (message != null) {
            System.out.println(message);
        }
    }

    private void closeAll() {
        serverConnector.disconnect();
        messageReader.stopReading();
        clientStopped = true;
        System.exit(0);
    }

    public boolean isClientStopped() {
        return clientStopped;
    }

    public void setClientStopped(boolean clientStopped) {
        this.clientStopped = clientStopped;
    }
}

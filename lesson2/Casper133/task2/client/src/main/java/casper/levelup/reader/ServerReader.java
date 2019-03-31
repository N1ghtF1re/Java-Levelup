package casper.levelup.reader;

import casper.levelup.Client;

public class ServerReader implements Runnable {
    private Client client;

    public ServerReader(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!client.isClientStopped()) {
            client.showMessage();
        }
    }
}

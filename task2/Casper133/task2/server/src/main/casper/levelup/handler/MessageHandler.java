package main.casper.levelup.handler;

import main.casper.levelup.Server;
import main.casper.levelup.command.MessageInvoker;
import main.casper.levelup.user.User;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageHandler implements Runnable {
    private User user;
    private Server server;

    MessageHandler(User user, Server server) {
        this.user = user;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader inReader = user.getInReader();
            while (!user.isUserExit()) {
                String message = inReader.readLine();
                if (message != null) {
                    MessageInvoker messageInvoker = new MessageInvoker(user, server, message);
                    messageInvoker.handle();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

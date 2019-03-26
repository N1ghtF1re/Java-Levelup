package main.casper.levelup.handler;

import main.casper.levelup.Server;
import main.casper.levelup.command.MessageInvoker;
import main.casper.levelup.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageHandler implements Runnable {
    private User user;
    private Server server;

    MessageHandler(User user, Server server) {
        this.user = user;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader inReader = new BufferedReader(new InputStreamReader(user.getSocket().getInputStream()))) {
            String message = inReader.readLine();

            while (true) {
                if (message != null) {
                    MessageInvoker messageInvoker = new MessageInvoker(user, server, message);
                    messageInvoker.handle();
                }

                message = inReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package main.casper.levelup.command;

import main.casper.levelup.connector.SocketClientConnector;
import main.casper.levelup.user.User;

public class RegCommand implements Command {
    private User user;
    private String message;
    private SocketClientConnector clientConnector;

    RegCommand(User user, String message, SocketClientConnector clientConnector) {
        this.user = user;
        this.message = message;
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        if (!user.isLoggedIn()) {
            String username = message.replaceAll("/reg ", "");
            user.setUsername(username);
            user.setLoggedIn(true);
            clientConnector.sendMessage(user, "Регистрация прошла успешно!\n");
            System.out.println(username + " just registered");
            clientConnector.createChatSession(user);
        } else {
            clientConnector.sendMessage(user, "Вы уже зарегистрированы!\n");
        }
    }
}

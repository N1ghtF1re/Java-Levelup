package casper.levelup.command;

import casper.levelup.connector.SocketClientConnector;
import casper.levelup.user.User;

import java.util.List;

public class CommonMessage implements Command {
    private User user;
    private String message;
    private SocketClientConnector clientConnector;

    CommonMessage(User user, String message, SocketClientConnector clientConnector) {
        this.user = user;
        this.message = message;
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        if (user.isLoggedIn()) {
            if (!user.isInChatSession()) {
                List<String> messages = user.getMessages();
                messages.add(message);
                user.setMessages(messages);
            } else {
                user.getChatSession().sendMessageInChatSession(user, message);
            }
        } else {
            clientConnector.sendMessage(user,
                    "Вы не зарегистрированы! Для доступа к чату введите команду \"/reg USERNAME\"\n");
        }
    }
}

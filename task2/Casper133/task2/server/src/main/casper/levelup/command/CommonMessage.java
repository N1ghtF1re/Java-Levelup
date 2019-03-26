package main.casper.levelup.command;

import main.casper.levelup.user.User;

import java.util.List;

public class CommonMessage implements Command {
    private User user;
    private String message;

    CommonMessage(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute() {
        if (user.isLoggedIn()) {
            if (!user.isInChatSession()) {
                List<String> messages = user.getMessages();
                messages.add(message);
                user.setMessages(messages);
            }
            // TODO: else передавать сообщения напрямую в чат-сессию
        }
        // TODO: else отправить клиенту сообщение об обязятельной регистрации
    }
}

package casper.levelup.session;

import casper.levelup.connector.SocketClientConnector;
import casper.levelup.user.User;

public class ChatSession {
    private User firstUser;
    private User secondUser;
    private SocketClientConnector clientConnector;

    public ChatSession(User firstUser, User secondUser, SocketClientConnector clientConnector) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.clientConnector = clientConnector;
        System.out.println("Created chat session with " + firstUser.getUsername() + " and " + secondUser.getUsername());
    }

    public void sendMessageInChatSession(User user, String message) {
        message = "[" + user.getUsername() + "]: " + message;
        clientConnector.sendMessage(getInterlocutor(user), message);
        System.out.println(message);
    }

    public void disconnect(User user) {
        firstUser.setInChatSession(false);
        secondUser.setInChatSession(false);

        clientConnector.sendMessage(getInterlocutor(user),
                user.getUsername() + " отключился. Вы помещены в режим ожидания собеседника\n");
        System.out.println(user.getUsername() + " отключился");
    }

    private User getInterlocutor(User user) {
        if (user.equals(firstUser)) {
            return secondUser;
        } else {
            return firstUser;
        }
    }
}

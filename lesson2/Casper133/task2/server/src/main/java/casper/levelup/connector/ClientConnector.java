package casper.levelup.connector;

import casper.levelup.user.User;

public interface ClientConnector {
    void initConnection();
    void sendMessage(User user, String message);
    void disconnect();
}

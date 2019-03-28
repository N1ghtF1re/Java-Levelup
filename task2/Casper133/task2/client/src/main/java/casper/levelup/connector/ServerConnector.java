package casper.levelup.connector;

public interface ServerConnector {
    void connect();
    void sendMessage(String message);
    String readMessage();
    void disconnect();
}

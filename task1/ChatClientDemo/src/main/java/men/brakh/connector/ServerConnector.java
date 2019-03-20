package men.brakh.connector;

import men.brakh.Message;

/**
 * Интерфейс связи с сервером
 */
public interface ServerConnector {
    boolean connect();
    void sendMessage(Message message);
    void disconnect();
}

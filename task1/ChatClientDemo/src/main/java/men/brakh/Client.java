package men.brakh;

import men.brakh.connector.ServerConnector;
import men.brakh.connector.impl.FileServerConnector;
import men.brakh.messageReader.MessageReader;
import men.brakh.messageReader.impl.ConsoleMessageReader;
import men.brakh.parser.Parser;
import men.brakh.parser.iml.CsvParser;
import men.brakh.view.MessageMapper;
import men.brakh.view.impl.ConsoleMessageMapper;

public class Client {
    private User user = null;
    private ServerConnector serverConnector;
    private MessageReader messageReader;
    private MessageMapper messageMapper;
    private Parser parser;


    /**
     * Возвращает, зарегистрирован ли пользователь
     */
    public boolean isRegistered() {
        return user != null;
    }

    public void register(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Client() {
        parser = new CsvParser();

        messageMapper = new ConsoleMessageMapper();

        serverConnector = new FileServerConnector(this, "in.txt", messageMapper, parser);
        serverConnector.connect();

        messageReader = new ConsoleMessageReader(this, messageMapper);
        messageReader.startRead();
    }

}

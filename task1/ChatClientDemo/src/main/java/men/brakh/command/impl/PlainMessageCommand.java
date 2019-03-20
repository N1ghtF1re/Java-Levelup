package men.brakh.command.impl;

import men.brakh.Client;
import men.brakh.Message;
import men.brakh.User;
import men.brakh.command.Command;
import men.brakh.view.MessageMapper;

public class PlainMessageCommand implements Command {
    private MessageMapper messageMapper;
    private Client client;
    private Message message;

    public PlainMessageCommand(Client client, MessageMapper messageMapper, Message message) {
        this.messageMapper = messageMapper;
        this.message = message;
        this.client = client;
    }

    @Override
    public void execute() {
        if(client.isRegistered()) {
            messageMapper.show(message);
        } else {
            messageMapper.show(new Message("Зарегайся!", new User("Server")));
        }
    }
}

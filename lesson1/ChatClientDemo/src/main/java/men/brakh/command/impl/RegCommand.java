package men.brakh.command.impl;

import men.brakh.Client;
import men.brakh.Message;
import men.brakh.User;
import men.brakh.command.Command;
import men.brakh.view.MessageMapper;

public class RegCommand implements Command {
    private Client client;
    private Message message;
    private MessageMapper messageMapper;

    public RegCommand(Client client, Message message, MessageMapper messageMapper) {
        this.client = client;
        this.messageMapper = messageMapper;
        this.message = message;
    }

    @Override
    public void execute() {
       if(!client.isRegistered()) {
           client.register(new User(message.getText()));
           messageMapper.show(new Message("Привет, " + message.getText(), new User("SERVER")));
       } else {
           throw new RuntimeException();
       }
    }
}

package men.brakh.command;

import men.brakh.Client;
import men.brakh.Message;
import men.brakh.command.impl.ExitCommand;
import men.brakh.command.impl.PlainMessageCommand;
import men.brakh.command.impl.RegCommand;
import men.brakh.view.MessageMapper;

public class Invoker {
    private Command regCommand;
    private ExitCommand exitCommand;
    private PlainMessageCommand plainMessageCommand;
    private Message message;

    public Invoker(Client client, Message message, MessageMapper messageMapper) {
        regCommand = new RegCommand(client, message, messageMapper);
        plainMessageCommand = new PlainMessageCommand(client, messageMapper, message);
        exitCommand = new ExitCommand(messageMapper);

        this.message = message;
    }

    public void handle() {
        String[] words = message.getText().split(" ", 2);
        if(words.length == 0) return;
        switch (words[0]) {
            case "/reg":
                message.setText(message.getText().replaceAll("/reg ", ""));
                regCommand.execute();
                break;
            case "/exit":
                message.setText(message.getText().replaceAll("/exit ", ""));
                exitCommand.execute();
                break;
            default:
                plainMessageCommand.execute();
                break;
        }
    }
}

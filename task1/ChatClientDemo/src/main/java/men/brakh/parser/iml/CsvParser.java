package men.brakh.parser.iml;

import men.brakh.Message;
import men.brakh.MessageStatus;
import men.brakh.User;
import men.brakh.parser.Parser;

public class CsvParser implements Parser {
    @Override
    public Message parse(String message) {
        String[] rows = message.split(":", 2);
        String name = rows[0];
        String text = rows[1];

        User user = new User(name);
        return new Message(text, user, MessageStatus.OK);
    }

    @Override
    public String stringify(Message message) {
        return String.format("%s:%s", message.getUser().getName(),
                message.getText());
    }
}

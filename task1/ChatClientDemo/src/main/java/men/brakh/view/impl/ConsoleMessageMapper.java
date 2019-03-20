package men.brakh.view.impl;

import men.brakh.Message;
import men.brakh.view.MessageMapper;

public class ConsoleMessageMapper implements MessageMapper {
    @Override
    public void show(Message message) {
        System.out.println(message);
    }
}

package men.brakh.command.impl;

import men.brakh.command.Command;
import men.brakh.view.MessageMapper;

public class ExitCommand implements Command {
    private MessageMapper messageMapper;

    public ExitCommand(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public void execute() {
        // Это неочень красиво, но красивее сами перепишете))0
        System.exit(0);
    }
}

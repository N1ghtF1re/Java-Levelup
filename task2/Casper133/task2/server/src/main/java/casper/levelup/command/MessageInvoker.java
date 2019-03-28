package casper.levelup.command;

import casper.levelup.Server;
import casper.levelup.user.User;

public class MessageInvoker {
    private RegCommand regCommand;
    private ExitCommand exitCommand;
    private CommonMessage commonMessage;
    private String message;

    public MessageInvoker(User user, Server server, String message) {
        regCommand = new RegCommand(user, message, server.getClientConnector());
        exitCommand = new ExitCommand(user, server);
        commonMessage = new CommonMessage(user, message, server.getClientConnector());

        this.message = message;
    }

    public void handle() {
        String[] words = message.split(" ", 2);
        if(words.length == 0) return;
        switch (words[0]) {
            case "/reg":
                regCommand.execute();
                break;
            case "/exit":
                exitCommand.execute();
                break;
            default:
                commonMessage.execute();
                break;
        }
    }
}

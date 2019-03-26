package main.casper.levelup.command;

import main.casper.levelup.user.User;

public class RegCommand implements Command {
    private User user;
    private String message;

    RegCommand(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute() {
        if (!user.isLoggedIn()) {
            String username = message.replaceAll("/reg ", "");
            user.setUsername(username);
            user.setLoggedIn(true);
        }
    }
}

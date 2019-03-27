package main.casper.levelup.command;

import main.casper.levelup.Server;
import main.casper.levelup.user.User;

import java.util.List;

public class ExitCommand implements Command {
    private User user;
    private Server server;

    ExitCommand(User user, Server server) {
        this.user = user;
        this.server = server;
    }

    @Override
    public void execute() {
        List<User> allUsers = server.getAllUsers();
        allUsers.remove(user);
        server.setAllUsers(allUsers);
        user.setUserExit(true);
        user.getChatSession().disconnect(user);
        user.closeAll();
    }
}

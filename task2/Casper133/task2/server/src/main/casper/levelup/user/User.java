package main.casper.levelup.user;

import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class User {
    private Socket socket;
    private String username;
    private List<String> messages = Collections.synchronizedList(new LinkedList<String>());
    private boolean loggedIn = false;
    private boolean inChatSession = false;

    public User(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isInChatSession() {
        return inChatSession;
    }

    public void setInChatSession(boolean inChatSession) {
        this.inChatSession = inChatSession;
    }
}

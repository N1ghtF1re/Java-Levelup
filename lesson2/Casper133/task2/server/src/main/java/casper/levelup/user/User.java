package casper.levelup.user;

import casper.levelup.session.ChatSession;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class User {
    private Socket socket;
    private BufferedReader inReader;
    private BufferedWriter outWriter;
    private ChatSession chatSession;

    private String username;
    private List<String> messages = Collections.synchronizedList(new LinkedList<String>());
    private boolean loggedIn = false;
    private boolean inChatSession = false;
    private boolean userExit = false;

    public User(Socket socket) {
        this.socket = socket;

        try {
            this.inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAll() {
        try {
            socket.close();
            inReader.close();
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getInReader() {
        return inReader;
    }

    public BufferedWriter getOutWriter() {
        return outWriter;
    }

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
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

    public boolean isUserExit() {
        return userExit;
    }

    public void setUserExit(boolean userExit) {
        this.userExit = userExit;
    }
}

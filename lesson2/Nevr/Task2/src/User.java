import java.io.*;
import java.net.Socket;

public class User extends Thread {
    public User interlocutor;
    public boolean hasInterlocutor;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public User(Socket socket) throws IOException {
        this.socket = socket;
        hasInterlocutor = false;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        sendError("Вы подключились к серверу");
        start();
    }

    public void getInterlocutor(User interlocutor){
        interlocutor.hasInterlocutor = true;
        this.interlocutor = interlocutor;
        hasInterlocutor = true;
        interlocutor.interlocutor = this;
        sendError("Вы подключены к собеседнику");
        send("Вы подключены к собеседнику");
    }

    public void disconect(){
        if (hasInterlocutor) {
            interlocutor.hasInterlocutor = false;
            send("Вы отключены от собеседника");
            Server.newChat(interlocutor);
        }
        closeThisUser();
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                if (word.equals("/exit"))
                    disconect();
                else if (hasInterlocutor)
                    send(word);
                else
                    sendError("Собеседник отсутствует");
            }
        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            interlocutor.out.write(msg + "\n");
            interlocutor.out.flush();
        } catch (IOException ignored) {
            interlocutor.disconect();
        }
    }

    private void sendError(String message){
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ignored) {
            disconect();
        }
    }

    private void closeThisUser(){
        try
        {
            socket.close();
            in.close();
            out.close();
            Server.usersList.remove(this);
        }
        catch (IOException e){}
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static   final int PORT = 8080;
    public static   LinkedList<User> usersList = new LinkedList<User>();

    public static void servGo() throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try{
            while (true){
                Socket socket = server.accept();
                try{
                    connecting(new User(socket));
                } catch (Exception e){
                    socket.close();
                }
            }
        }
        finally {
            server.close();
        }
    }

    public static void connecting(User user){
        for (User usr : usersList)
        {
            if (!usr.hasInterlocutor) {
                usr.getInterlocutor(user);
                break;
            }
            usersList.add(user);
        }
    }

    public static void newChat(User user)
    {
        usersList.remove(user);
        connecting(user);
    }
}

import com.sun.javaws.exceptions.ExitException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String name;
    public  String ipAddr = "localhost";
    public  int port = 8080;
    public  Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputUser; // поток чтения с консоли

    public void GetName() {
        String name;
        String firstWord;
        Scanner console = new Scanner(System.in);
        do {
            System.out.println("Зарегестрируйтесь!");
            name = console.nextLine();
            firstWord = GetFirstWord(name);
            name = name.replaceAll("/reg", "").trim();
        } while (!firstWord.equals("/reg") || name.isEmpty());
        this.name = name;
    }

    private String GetFirstWord(String string) {
        string = string.trim();
        if (!string.isEmpty())
            return (string).split(" ")[0];
        else
            return "";
    }

    public Client() {
        GetName();
        System.out.println("Подключение к серверу...");
        try {
            this.socket = new Socket(ipAddr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Thread readThread = new Thread(new ReadMsg());
            readThread.start();
            Thread writeThread = new Thread(new WriteMsg());
            writeThread.start();
        } catch (Exception e) {
            System.out.println("Ошибка подключения к серверу");
            close();
        }
    }

    private class ReadMsg implements Runnable {
        @Override
            public void run() {
            String str;

            try {
                while (true) {
                    str = in.readLine(); // ждем сообщения с сервера
                    if (str.equals("stop")) {
                        break; // выходим из цикла если пришло "stop"
                    }
                    System.out.println(str); // пишем сообщение с сервера на консоль
                }
            } catch (IOException e) {
            }
            finally {
                close();
            }
        }
    }

    private class WriteMsg implements Runnable {
        @Override
        public void run() {
            String firstWord;
            while (true) {
                String userWord;
                try {
                    userWord = inputUser.readLine(); // сообщения с консоли
                    firstWord = GetFirstWord(userWord);
                    if (firstWord.equals("/exit")) {
                        out.write("/exit" + "\n");
                        out.flush();
                        close();
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        out.write(name + ": " + userWord + "\n"); // отправляем на сервер
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    close();
                }
            }
        }
    }

    protected void close(){
        try {
            if (socket != null && !socket.isClosed()) {
                System.out.println("Отключение от сервера...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }
}

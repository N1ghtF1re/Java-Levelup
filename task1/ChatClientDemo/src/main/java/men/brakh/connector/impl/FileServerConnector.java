package men.brakh.connector.impl;

import men.brakh.Client;
import men.brakh.Message;
import men.brakh.connector.ServerConnector;
import men.brakh.parser.Parser;
import men.brakh.view.MessageMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Связь с сервером, построенном на файлах
 */
public class FileServerConnector implements ServerConnector {
    private String filename;
    private Client client;
    private boolean active;
    private MessageMapper messageMapper;
    private Parser parser;

    public FileServerConnector(Client client, String filename, MessageMapper messageMapper, Parser parser) {
        this.filename = filename;
        this.client = client;
        this.active = true;
        this.messageMapper = messageMapper;
        this.parser = parser;

        File file = new File("in.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean connect() {
        handleMessages();
        return true;
    }

    private void handleMessages() {
        // Это тоже можно покрасивее сделать)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (client.isRegistered()) {
                                List<String> lines = Files.readAllLines(
                                        Paths.get(filename), StandardCharsets.UTF_8);
                                for (String line : lines) {
                                    Message msg = parser.parse(line);

                                    messageMapper.show(msg);
                                }

                                PrintWriter pw = new PrintWriter(filename); // Очистка файла
                                pw.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },0, 1000
        );
    }

    @Override
    public void sendMessage(Message message) {
        // Для файлов пока нет отправки на сервер
    }

    @Override
    public void disconnect() {
        active = false;
    }
}

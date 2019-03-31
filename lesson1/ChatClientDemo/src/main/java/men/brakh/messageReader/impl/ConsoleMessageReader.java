package men.brakh.messageReader.impl;

import men.brakh.Client;
import men.brakh.Message;
import men.brakh.command.Invoker;
import men.brakh.messageReader.MessageReader;
import men.brakh.view.MessageMapper;

import java.util.Scanner;

public class ConsoleMessageReader implements MessageReader {
    private Client client;
    private MessageMapper messageMapper;

    public ConsoleMessageReader(Client client, MessageMapper messageMapper) {
        this.client = client;
        this.messageMapper = messageMapper;
    }

    @Override
    public void startRead() {
        Thread thread = new Thread(
                () -> {
                    Scanner scanner = new Scanner(System.in);

                    while (true) {
                        String line = scanner.nextLine();

                        Message message = new Message(line, client.getUser());
                        Invoker invoker = new Invoker(client, message, messageMapper);
                        invoker.handle();
                    }
                }
        );
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

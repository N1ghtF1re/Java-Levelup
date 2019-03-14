package casper.levelup;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleThread implements Runnable {
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String message = bufferedReader.readLine();

            while (!message.equalsIgnoreCase("/exit")) {
                System.out.println("Пока что я не могу принять сообщение. Для выхода введи /exit\n");
                message = bufferedReader.readLine();
            }

            System.out.println("Удачного дня!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

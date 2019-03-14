package casper.levelup;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleThread implements Runnable {
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String username;
            boolean isUserReg = false;

            Pattern loginPattern = Pattern.compile("/login .+", Pattern.CASE_INSENSITIVE);
            Pattern spacePattern = Pattern.compile("\\s");

            String message = bufferedReader.readLine().trim();
            while (!message.equalsIgnoreCase("/exit")) {
                if (!isUserReg) {
                    if (loginPattern.matcher(message).find()) {
                        username = spacePattern.split(message, 2)[1];
                        isUserReg = true;
                        System.out.println("Привет, " + username + "!\n");
                    } else {
                        System.out.println("Без логина чат недоступен.\nВведите команду \"/login USERNAME\".\n");
                    }
                }

                message = bufferedReader.readLine().trim();
            }

            System.out.println("Удачного дня!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

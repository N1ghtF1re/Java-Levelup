package casper.levelup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ConsoleThread implements Runnable {
    @Override
    public void run() {
        User user = new User();
        boolean isUserLoggedIn = false;
        FileReading fileReading = null;

        String message = ConsoleReading.readFromConsole();
        while (!message.equalsIgnoreCase("/exit")) {
            if (isUserLoggedIn) {
                Date dateNow = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = dateFormat.format(dateNow);

                String fullMessage = "[" + time + "] " + user.getUsername() + ": " + message + "\n";
                System.out.println(fullMessage);
            } else {
                Pattern loginPattern = Pattern.compile("/login .+", Pattern.CASE_INSENSITIVE);
                Pattern spacePattern = Pattern.compile("\\s");

                if (loginPattern.matcher(message).find()) {
                    user.setUsername(spacePattern.split(message, 2)[1]);
                    isUserLoggedIn = true;

                    fileReading = new FileReading();
                    fileReading.startReadingFile();

                    System.out.println("Привет, " + user.getUsername() + "!\n");
                } else {
                    System.out.println("Без логина чат недоступен.\nВведите команду \"/login USERNAME\".\n");
                }
            }

            message = ConsoleReading.readFromConsole();
        }

        ConsoleReading.closeReader();

        if (fileReading != null) {
            fileReading.stopTimer();
            System.out.println("Удачного дня, " + user.getUsername() + "!");
        }
    }
}

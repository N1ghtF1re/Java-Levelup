package casper.levelup;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ConsoleThread implements Runnable {
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            User user = new User();
            boolean isUserLoggedIn = false;
            Thread fileThread = null;

            String message = bufferedReader.readLine().trim();
            while (!message.equalsIgnoreCase("/exit")) {
                if (isUserLoggedIn) {
                    File messagesFile = new File("files/Messages.txt");

                    if (!messagesFile.exists()) {
                        messagesFile.getParentFile().mkdirs();
                        messagesFile.createNewFile();
                    }

                    FileWriter fileWriter = new FileWriter(messagesFile, true);
                    Date dateNow = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String time = dateFormat.format(dateNow);
                    String fileMessage = "[" + time + "] " + user.getUsername() + ": " + message + "\n";
                    fileWriter.write(fileMessage);
                    fileWriter.flush();
                    fileWriter.close();
                } else {
                    Pattern loginPattern = Pattern.compile("/login .+", Pattern.CASE_INSENSITIVE);
                    Pattern spacePattern = Pattern.compile("\\s");

                    if (loginPattern.matcher(message).find()) {
                        user.setUsername(spacePattern.split(message, 2)[1]);
                        isUserLoggedIn = true;

                        fileThread = new Thread(new FileThread());
                        fileThread.start();

                        System.out.println("Привет, " + user.getUsername() + "!\n");
                    } else {
                        System.out.println("Без логина чат недоступен.\nВведите команду \"/login USERNAME\".\n");
                    }
                }

                message = bufferedReader.readLine().trim();
            }

            if (fileThread != null) {
                fileThread.interrupt();
                System.out.println("Удачного дня, " + user.getUsername() + "!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

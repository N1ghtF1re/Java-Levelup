package casper.levelup;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ConsoleThread implements Runnable {
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String username = null;
            boolean isUserReg = false;
            Thread fileThread = null;

            String message = bufferedReader.readLine().trim();
            while (!message.equalsIgnoreCase("/exit")) {
                if (isUserReg) {
                    FileWriter fileWriter = new FileWriter("files/Messages.txt", true);
                    Date dateNow = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String time = dateFormat.format(dateNow);
                    String fileMessage = "[" + time + "] " + username + ": " + message + "\n";
                    fileWriter.write(fileMessage);
                    fileWriter.flush();
                    fileWriter.close();
                } else {
                    Pattern loginPattern = Pattern.compile("/login .+", Pattern.CASE_INSENSITIVE);
                    Pattern spacePattern = Pattern.compile("\\s");

                    if (loginPattern.matcher(message).find()) {
                        username = spacePattern.split(message, 2)[1];
                        isUserReg = true;

                        fileThread = new Thread(new FileThread());
                        fileThread.start();

                        System.out.println("Привет, " + username + "!\n");
                    } else {
                        System.out.println("Без логина чат недоступен.\nВведите команду \"/login USERNAME\".\n");
                    }
                }

                message = bufferedReader.readLine().trim();
            }

            if (fileThread != null) {
                fileThread.interrupt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

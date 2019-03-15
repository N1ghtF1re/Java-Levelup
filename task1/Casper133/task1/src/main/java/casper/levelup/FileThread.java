package casper.levelup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileThread implements Runnable {
    @Override
    public void run() {
        try {
            String fileBeforePause = "";
            while (true) {
                Thread.sleep(1000);
                File messagesFile = new File("files/Messages.txt");

                if (!messagesFile.exists()) {
                    messagesFile.getParentFile().mkdirs();
                    messagesFile.createNewFile();
                }

                String fileAfterPause = new String(Files.readAllBytes(Paths.get("files/Messages.txt")));

                if (!fileAfterPause.equals(fileBeforePause)) {
                    fileBeforePause = escapeSpecialRegexChars(fileBeforePause);

                    Pattern filePattern = Pattern.compile(fileBeforePause);
                    Matcher matcher = filePattern.matcher(fileAfterPause);

                    String newMessages = matcher.replaceFirst("");
                    System.out.println(newMessages);
                }

                fileBeforePause = fileAfterPause;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Удачного дня!");
        }
    }

    private String escapeSpecialRegexChars(String str) {
        Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
        return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
    }
}

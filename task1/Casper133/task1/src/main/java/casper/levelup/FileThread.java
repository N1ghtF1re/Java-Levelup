package casper.levelup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileThread implements Runnable {
    @Override
    public void run() {
        // try (BufferedReader fileReader = new BufferedReader(new FileReader("files/Messages.txt"))) {
        try {
            String fileBeforePause = "";
            while (true) {
                Thread.sleep(1000);
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String escapeSpecialRegexChars(String str) {
        Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
        return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
    }
}

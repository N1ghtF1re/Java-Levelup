package casper.levelup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileThread implements Runnable {
    @Override
    public void run() {
        // try (BufferedReader fileReader = new BufferedReader(new FileReader("files/Messages.txt"))) {
        try {
            String fileBeforePause = "";
            while (true) {
                try {
                    Thread.sleep(1000);
                    String fileAfterPause = new String(Files.readAllBytes(Paths.get("files/Messages.txt")));

                    if (fileAfterPause.equals(fileBeforePause)) {
                        System.out.println("Files equal");
                    } else {
                        System.out.println("Files not equal");
                    }

                    fileBeforePause = fileAfterPause;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

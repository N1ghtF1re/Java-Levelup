package casper.levelup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

class FileReading {
    private Timer timer;
    private File file = new File("files/Messages.txt");
    private LinkedList<String> messagesBeforePause = new LinkedList<>();

    void startReadingFile() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    LinkedList<String> messagesAfterPause = new LinkedList<>();
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }

                    BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);

                    String line;
                    while ((line = reader.readLine()) != null) {
                        messagesAfterPause.add(line);
                    }

                    if (!messagesAfterPause.equals(messagesBeforePause)) {
                        for (int i = messagesBeforePause.size(); i < messagesAfterPause.size(); i++) {
                            System.out.println(messagesAfterPause.get(i));
                        }
                    }

                    messagesBeforePause = messagesAfterPause;
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}

package main.casper.levelup.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMessageReader implements MessageReader {
    private BufferedReader bufferedReader;

    @Override
    public String readMessage() {
        try {
            if (bufferedReader == null) {
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            }

            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void stopReading() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package casper.levelup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ConsoleReading {
    private static BufferedReader bufferedReader;

    static String readFromConsole() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    static void closeReader() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

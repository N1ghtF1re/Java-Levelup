import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileClient {
    public void start() throws Exception {

        Thread ClientFile = new Thread(new Runnable()
        {
            public void run() {
                String st;
                boolean notKilled = true;
                File file = new File("test.txt");
               // BufferedReader reader = null;

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    while (notKilled) {
                       // st = reader.readLine();
                        while ((st = reader.readLine()) != null) {
                            if (st.equals("--exit")) {
                                notKilled = false;
                            } else System.out.println("[From File] : " + st);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ClientFile.start();
    }
}

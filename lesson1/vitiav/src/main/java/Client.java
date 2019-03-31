import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {
    static   String name="";
    static String file;
    public Client(String filename) {
        this.file = filename;

    }

    @Override
    public void run() {
        System.out.println("starting client!");
        Scanner scanner1 = new Scanner(System.in);
        ReentrantLock lock = new ReentrantLock();
        String inputString = "";

        do {
            inputString = scanner1.nextLine();


            if(inputString.charAt(0)=='/'){

                if ((inputString.length() > 5) && (inputString.substring(0, 5).equals("/reg "))) {
                    name = inputString.substring(5);
                    System.out.println("Привет, " + name);
                }

            }
            else if ((name.length() > 0) && (inputString.length() > 0)&&(inputString.charAt(0)!='/')) {

                try {
                    String appendc='[' + name + ']' + inputString+'\n';
                    lock.lock();
                    Files.write(Paths.get(file),appendc.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }

            }

        } while (!inputString.equals("/exit"));
        if (name.length() > 0) {
            System.out.println("Пока, " + name + "! Приходи еще!");
        }
        System.exit(0);
    }
}

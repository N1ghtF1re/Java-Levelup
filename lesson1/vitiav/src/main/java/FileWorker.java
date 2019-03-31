import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FileWorker implements Runnable {
    private final String filename;
    ReentrantLock lock = new ReentrantLock();
    private int actualCount = 0;
    private int printedCount = 0;

    public FileWorker(String file) {

        this.filename = file;

    }

    public void run() {
        System.out.println("starting fileworker!");
        while (true) {
            try {
                // System.out.println(Thread.currentThread());
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {


                // System.out.println("adding");
                lock.lock();
                List<String> lines = Files.readAllLines(Paths.get(this.filename));
                this.actualCount = lines.size();
                for (; this.printedCount < this.actualCount; this.printedCount++) {
                    System.out.println(lines.get(this.printedCount));
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                //lock.unlock();
            }
            lock.unlock();
        }

    }

    public void clear() {
        this.printedCount = 0;
        this.actualCount = 0;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }
}

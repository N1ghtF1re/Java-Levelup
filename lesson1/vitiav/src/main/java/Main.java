import java.io.File;
import java.io.IOException;

public class Main {

    static String file="1.txt";
    public static void main(String[] args) {

      if(args.length>0){
            file = args[0]; }       File file1 = new File(file);
        try {
            if(file1.createNewFile()){
                System.out.println(file+" File Created");
            }else System.out.println("File "+file+" already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t1 = new  Thread(new FileWorker(file));
      Thread t2 = new Thread(new Client(file));
     t2.start();
     t1.start();
    }
}

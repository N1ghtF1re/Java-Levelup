import java.io.*;
import java.util.Scanner;

public class ConsoleClient {
   // private FileClient fileClient = new FileClient();
    public static void main(String[] args) throws Exception{
        FileClient fileClient = new FileClient();

        Thread ClientRead = new Thread(new Runnable()
        {
            public void run()
            {
                boolean notKilled = true;
                Scanner scan = new Scanner(System.in);

                String username = "";
                System.out.println("Enter your name :");
                username = scan.nextLine();

                String st ;

                while (notKilled) {
                  //  while ((st = scan.nextLine()) != null) {
                    st = scan.nextLine();
                        if (st.equals("--exit")){
                            notKilled = false;
                           // break;
                        } else
                            System.out.println("[" + username + "] : " + st);
                    }
               // }
            }
        });
        ClientRead.start();
        fileClient.start();
    }

}

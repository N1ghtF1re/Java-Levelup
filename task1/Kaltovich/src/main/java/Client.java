import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception
    {
        //Создание потока для файла
        Thread myThready = new Thread(new Runnable()
        {
            public void run()
            {
                boolean notKilled = true;

                File file = new File("D:\\!Disk_D\\codes program\\test.txt");
                String st;
                try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                    while (notKilled) {
                        while ((st = br.readLine()) != null) {
                            if (st.equals("--exit")){
                                notKilled = false;
                                break;
                            } else  if (st != "") System.out.println("[From File] : " + st);
                        }
                    }
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Создание потока для консоли
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
                   while ((st = scan.nextLine()) != null) {
                       if (st.equals("--exit")){
                           notKilled = false;
                           break;
                       } else
                           System.out.println("[" + username + "] : " + st);
                   }
                }
            }
        });

        myThready.start();	//Запуск потока
        ClientRead.start();	//Запуск потока

//чтобы программа завершилась, нужно, чтобы и в файле и в консоли было прописано "--exit"
    }
}

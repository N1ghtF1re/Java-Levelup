import java.io.*;
public class Server
{
    public static void main(String[] args)throws Exception
    {
        boolean end = false;
        File file = new File("D:\\!Disk_D\\codes program\\test.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        while (end == false) {
            String st;

            while ((st = br.readLine()) != null) {
                if (st == "--exit") {
                    end = true;
                }
                else System.out.println("[From File] : " + st);

            }
        }
    }
}
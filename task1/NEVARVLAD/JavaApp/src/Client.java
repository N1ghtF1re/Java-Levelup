import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    private boolean TimerGoing = false;
    private String Name;
    private String FileName = "file.txt";
    Timer reading;

    //Получение имени пользователя
    public void GetName() {
        String name;
        String firstWord;
        Scanner console = new Scanner(System.in);
        do {
            name = console.nextLine();
            firstWord = GetFirstWord(name);
            name = name.replaceAll("/reg", "").trim();
        } while (!firstWord.equals("/reg") || name.isEmpty());
        Name = name;
        //console.close();
    }
    //Непосредственный запуск чата
    public void StartChar() {
        CreateFile();
        StreamResume();
        Scanner console = new Scanner(System.in);
        String message = "", firstWord;
        while (true) {
           // if (console.hasNextLine()) {
                message = console.nextLine();

            if (!message.isEmpty()) {
                firstWord = GetFirstWord(message);
                if (firstWord.equals("/exit")) {
                    StreamPause();
                    return;
                } else if (firstWord.equals("/pause"))
                    StreamPause();
                else if (firstWord.equals("/resume"))
                    StreamResume();
                message = message.trim();
                if (!message.isEmpty())
                    System.out.println(String.format("[%s]: %s", Name, message));
            }
        }
    }
    //Получения первого слова для проверка на команду
    private String GetFirstWord(String string) {
        String res;
        string = string.trim();
        try
        {
            res = (string + " ").split(" ")[0];
        }
        catch (Exception e)
        {
            res = "";
        }
        return res;
    }
    //Процедурка чтения из файла в основном потоке (на всякий пожарный)
    private void ReadFile() {
        try {
            FileInputStream fstream = new FileInputStream(FileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
    //Создание файла, откуда читаем (мало ли)
    private void CreateFile() {
        File filePath = new File("");
        filePath.mkdir();
        FileName = filePath + FileName;
        File file = new File(FileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Остановка таймера
    public void StreamPause(){
        if (TimerGoing) {
            this.reading.cancel();
            TimerGoing = false;
        }
    }
    //Возобновление таймера
    public void StreamResume() {
        if (!TimerGoing) {
            this.reading = new Timer();
            this.reading.schedule(new ReadingTask(FileName), 0, 1000);
            TimerGoing = true;
        }
    }

}


//Работа с файлом
class ReadingTask extends TimerTask {

    String FileName;
    Random random = new Random();

    String[] Names =  {"Наташа", "Влад", "Маша", "Саша", "Кирилл", "Даша", "Карина", "Настя", "Гена"};
    String[] Messages = {"Вижла топ", "С# класс", "Java бесит", "Хочу на винду", "InlliJ IDEA отстой", "Жизнь гавно", "Дедлайн скоро", "Не хочу на военку", "Ищу разработчика", "Помогите, плиз"};

    ReadingTask(String fileName){
        FileName = fileName;
    }

    public void run() {
        //чтение сообщений
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName)))){
            String strLine;
            while ((strLine = br.readLine()) != null){
                System.out.println(strLine);
            }

        }catch (IOException e){
            System.out.println("Ошибка");
        }
        //отправка сообщений
        try(
            FileWriter writer = new FileWriter(FileName)){
            writer.write(String.format("[%s]: %s", Names[random.nextInt(Names.length)], Messages[random.nextInt(Messages.length)])+ System.getProperty("line.separator"));
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}



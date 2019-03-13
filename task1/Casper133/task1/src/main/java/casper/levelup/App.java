package casper.levelup;

public class App {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в чат!\n");
        System.out.println("Для начала войдите под каким-либо никнеймом (/reg Nickname).");
        System.out.println("В любой момент можно выйти (/exit).\n");

        Thread consoleServiceThread = new Thread(new ConsoleService());
        consoleServiceThread.start();

        Thread fileServiceThread = new Thread(new FileService());
        fileServiceThread.start();
    }
}

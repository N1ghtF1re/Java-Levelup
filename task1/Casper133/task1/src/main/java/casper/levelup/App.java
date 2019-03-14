package casper.levelup;

public class App {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в чат!\n");
        System.out.println("Для начала войдите под каким-либо никнеймом (/reg Nickname).");
        System.out.println("В любой момент можно выйти (/exit).\n");

        Thread consoleThread = new Thread(new ConsoleThread());
        consoleThread.start();

        Thread fileThread = new Thread(new FileThread());
        fileThread.start();
    }
}

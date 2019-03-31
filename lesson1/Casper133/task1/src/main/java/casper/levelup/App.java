package casper.levelup;

public class App {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать!\n");
        System.out.println("Для начала войдите в чат (/login USERNAME).");
        System.out.println("В любой момент можно выйти (/exit).\n");

        Thread consoleThread = new Thread(new ConsoleThread());
        consoleThread.start();
    }
}

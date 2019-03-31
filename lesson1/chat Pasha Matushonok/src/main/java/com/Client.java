package com;

import java.util.Scanner;

public class Client implements Runnable {
    @Override
    public void run(){
        String startmessage;
        boolean start, exit;
        do {
            Scanner scanner = new Scanner(System.in);
            startmessage = scanner.nextLine();
            start = startmessage.startsWith("/reg") && (startmessage.length() > 5) && (startmessage.charAt(4) == ' ');
            exit = startmessage.equals("/exit");
        }while (!start && !exit);
        if (!exit && start){
            String username = startmessage.substring(5, startmessage.length());
            FileListner fileListner = new FileListner();
            Thread filelistner = new Thread(fileListner);
            filelistner.start();
            startchat(username);
            fileListner.continuechatting = false;
        }
    }

    protected void startchat(String username){
        Scanner scanner = new Scanner(System.in);
        System.out.println(username+" has joined the chat");
        while (true) {
            String clientmessage = scanner.nextLine();
            if (clientmessage.equals("/exit")) {
                System.out.println(username+" has left chat");
                return;
            }
            else
                System.out.format("[%s] %s\n", username, clientmessage);
        }
    }
}

package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class FileListner implements Runnable {
    public boolean continuechatting;

    FileListner(){
        continuechatting = true;
    }

    @Override
    public void run(){
        String filename = "./src/main/resources/chat.txt";
        int prevmessageamount = 0;
        int messageamount;
        try {
            while (continuechatting) {
                LinkedList<String> messages = readfile(filename);
                messageamount = messages.size();
                if (messageamount > prevmessageamount){
                    for (int i = prevmessageamount; i < messageamount; i++){
                        System.out.println(messages.get(i));
                    }
                }
                prevmessageamount = messageamount;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    private LinkedList<String> readfile(String filename) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            LinkedList<String> messages = new LinkedList<>();
            while ((line = reader.readLine()) != null){
                messages.add(line);
            }
            return messages;
        }
    }
}

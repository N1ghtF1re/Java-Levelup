package com;

public class Main {
    public static void main(String[] args) {
        Thread client = new Thread(new Client());
        client.start();
    }
}

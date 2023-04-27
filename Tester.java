package com.example;


public class Tester {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        server.start();
        client.start(); 
    }
}

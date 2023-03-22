package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

class Server extends Thread{
    @Override
    public void run(){

        final ServerSocket server;
        final Socket in;

        try{
            server = new ServerSocket(62);
            in = server.accept();

        }catch(Exception e){
            System.out.println(e);
            return;
        }
        int go = 1;
        Scanner inReader = null;
        PrintStream outWriter = null;
        try {
            inReader = new Scanner(in.getInputStream());
            outWriter = new PrintStream(in.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            go = 0;
        }
        outWriter.println("Hello, I'm Larry! You're Friendly Fake AI. What can I do for you today?");
        String input = inReader.nextLine();
        try{
            if (go==1){
                while(go==1){
                outWriter.println(input);
                input = inReader.nextLine();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



        try{
            if(inReader!=null){
                inReader.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            if(outWriter!=null){
                outWriter.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            in.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try{
            server.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
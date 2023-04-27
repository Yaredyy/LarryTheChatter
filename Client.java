package com.example;

import java.io.PrintStream;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

class Client extends Thread{
    @Override
    public void run(){
        final Socket out;

        try{
            out = new Socket("localhost", 62);
        }catch(Exception e){
            System.out.println(e);
            return;
        }
        int go=1;
        Scanner inReader = null;
        PrintStream outWriter = null;
        Scanner sc = null;
        try {
            inReader = new Scanner(out.getInputStream());
            outWriter = new PrintStream(out.getOutputStream());
            sc = new Scanner(System.in);
            
        } catch (IOException e) {
            e.printStackTrace();
            go = 0;
        }

        try{
            if (go==1){
                String input;
                System.out.println(inReader.nextLine());
                while(go==1){
                    System.out.print("Input: ");
                    input = sc.nextLine();
                    try{
                        if(Integer.valueOf(input)==0){
                            throw new IOException();
                        }
                    }
                    catch(NumberFormatException e){
                    }
                    outWriter.println(input);
                    System.out.println("Loading");

                    if(inReader.nextLine().equals("Start of Response")){
                        String res = inReader.nextLine();
                        while(!res.equals("End of Response")){
                            System.out.println("Output: "+ res);
                            res = inReader.nextLine();
                        }
                    }

                    
                }
            }
        }
        catch(IOException e){
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
            out.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    // public void printAll(Scanner in){
    //     while(in.hasNextLine()){
    //         System.out.println(in.nextLine());
    //     }
    // }
}
package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Stack;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;





class chatGPT extends Thread{
    Stack<String> inputs;
    Stack<String> outputs;
    java.net.http.HttpRequest.Builder per;

    @Override
    public void run(){
        inputs = new Stack<String>();
        outputs = new Stack<String>();
        boolean flag = false;
        per = HttpRequest.newBuilder().uri(URI.create("https://openai80.p.rapidapi.com/chat/completions")).header("content-type", "application/json").header("X-RapidAPI-Key", "9896bc5682mshe84b62bff5d4f8ep12f1b4jsn7f40e8f57db6").header("X-RapidAPI-Host", "openai80.p.rapidapi.com");
        String stake = "";
        while(flag==false){
            if(inputs.size()!=0){
                stake = request((String) inputs.pop());
                outputs.push(stake);
                stake = "";
            }
        }
    }
    
    public String request(String text){
        HttpRequest request = per.method("POST", HttpRequest.BodyPublishers.ofString("{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \""+text+"\"}]}")).build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(response!=null){
            JsonObject res=null;
            try {
                res = (JsonObject) Jsoner.deserialize(response.body());
            } catch (JsonException e) {
                e.printStackTrace();
            }
            if(res!=null){
                String bark = "";
                if((JsonArray) res.get("choices")!=null){
                    JsonArray ja = ((JsonArray) res.get("choices"));
                    for(int i = 0; i<ja.size();i++){
                        if(!bark.equals("")){
                            bark+="\n";
                        }
                        bark += ((JsonObject)((JsonObject)ja.get(i)).get("message")).get("content").toString();
                    }

                }
                return bark;
                }
                else{
                    return"Error";
                }
            }
        return "!!Error!!";
    }



}

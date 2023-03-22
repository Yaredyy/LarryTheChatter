package com.example;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;




class chatGPT {
    String url;
    JsonObject data;
    HttpURLConnection con;
    public chatGPT(){
        try{
            url = "https://api.openai.com/v1/completions";
            con = (HttpURLConnection) new URL(url).openConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer YOUR-API-KEY");

        data = new JsonObject();
        data.put("model", "text-davinci-003");
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);
    }
    public String request(String text){
        data.put("prompt", text);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        //return the Json Object made using jsoner library and the output string
        JsonObject out = (JsonObject) Jsoner.deserialize(output);
        return out.getString("choices").split("text")[1].split("}")[0].split(":")[1].replace("\"", "");
    }
}

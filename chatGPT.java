import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;




class chatGPT {
    String url;
    JSONObject data;
    HttpURLConnection con;
    public chatGPT(){
        url = "https://api.openai.com/v1/completions";
        con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer YOUR-API-KEY");

        data = new JsonObject();
        data.put("model", "text-davinci-003");
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);
    }
    public JsonObject request(String text){
        data.put("prompt", text);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        return (new JsonObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }
}

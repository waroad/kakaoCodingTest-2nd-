import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
    static Connection instance = null;
    static final String connType = "Content-Type";
    static final String connJson = "application/json";
    static final String connAuth = "Authorization";

    public static Connection getInstance(){
        if(instance == null) instance = new Connection();
        return instance;
    }

    public String start(int problemId){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        String response = null;
        try{
            URL url = new URL(Global.BASE_URL + Global.POST_START + "?problem=" + problemId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Auth-Token", Global.X_AUTH_TOKEN);
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = br.readLine()) != null) sb.append(line);
                responseJson = new JSONObject(sb.toString());
                response = responseJson.getString("auth_key");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public JSONObject trucks(){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_URL + Global.GET_TRUCKS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(responseCode == 400) System.out.println("400 : Cant");
            else if(responseCode == 401) System.out.println("401: X_AUTH_TOKEN Header Error");
            else if(responseCode == 500) System.out.println("500: Server Error Please Contact");
            else{
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine()) != null) sb.append(line);
                responseJson = new JSONObject(sb.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject locations(){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_URL + Global.GET_LOCATIONS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.instance.getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(responseCode == 400) System.out.println("400 : Cant");
            else if(responseCode == 401) System.out.println("401: X_AUTH_TOKEN Header Error");
            else if(responseCode == 500) System.out.println("500: Server Error Please Contact");
            else{
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine()) != null) sb.append(line);
                responseJson = new JSONObject(sb.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject simulate(JSONArray commandArray){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_URL + Global.PUT_SIMULATE);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty(connType, connJson);
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(conn.getOutputStream())));
            JSONObject commands = new JSONObject();
            commands.put("commands", commandArray);
            System.out.println(commands);
            bw.write(commands.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();
            if(responseCode == 400) System.out.println("400 : Cant");
            else if(responseCode == 401) System.out.println("401: X_AUTH_TOKEN Header Error");
            else if(responseCode == 500) System.out.println("500: Server Error Please Contact");
            else{
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = br.readLine()) != null) sb.append(line);
                responseJson = new JSONObject(sb.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }


    public JSONObject score(){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_URL + Global.GET_SCORE);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.instance.getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(responseCode == 400) System.out.println("400 : Cant");
            else if(responseCode == 401) System.out.println("401: X_AUTH_TOKEN Header Error");
            else if(responseCode == 500) System.out.println("500: Server Error Please Contact");
            else{
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine()) != null) sb.append(line);
                responseJson = new JSONObject(sb.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

}

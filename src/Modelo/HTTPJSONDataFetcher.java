package Modelo;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPJSONDataFetcher {
    private URL url;
    private HttpURLConnection connection;
    private JSONObject object;

    public HTTPJSONDataFetcher(String url) {
        setUrl(url);
        createGETConnection();
    }

    public void setUrl(String url) {
        try {
            this.url = new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setHeader(String headerKey, String headerData){
        connection.setRequestProperty(headerKey,headerData);
    }


    public void setRequestProperty(String reqKey, String reqData){
        connection.setRequestProperty(reqKey,reqData);
    }

    private void createGETConnection() {
        try {
            this.connection = (HttpURLConnection) this.url.openConnection();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setURL(String url) {
        try {
            this.url = new URL(url);
            createGETConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private boolean checkIfConnectionIsSetAndCorrect() throws IOException {
        return connection != null && connection.getResponseCode() == 200;
    }

    private void closeConnection(){
        connection.disconnect();
    }

    public  JSONObject  getData() throws Exception {

        //if (checkIfConnectionIsSetAndCorrect()) {
        this.connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea;
            StringBuilder resultado = new StringBuilder();
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }

            JSONParser parser = new JSONParser();
            object = (JSONObject) parser.parse(resultado.toString());
            rd.close();
            closeConnection();
            return object;

       // } else
          //  throw new Exception("Connection error code: "  + connection.getResponseCode());
    }


}

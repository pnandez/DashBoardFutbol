package Modelo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class HTTPJSONGET {
    public static JSONObject getDataFromURL(String url, String headerKey, String headerBody) throws Exception {

        // create a client
        HttpClient client = HttpClient.newHttpClient();

// create a request
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url))
                .header(headerKey,headerBody)
                .build();

// use the client to send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONObject object = new JSONObject();
        object = (JSONObject) parser.parse(response.body());

        return object;

    }
}

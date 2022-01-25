package Modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Standings {
    Map<String, Long> standingsMap;

    public Standings(JSONArray array){
        standingsMap = new LinkedHashMap<>();
        for (Object o: array) {
            String teamName = (String) ((JSONObject)((JSONObject) o).get("team")).get("name");
            standingsMap.put(teamName, (Long) ((JSONObject)o).get("points"));
        }
    }

    public Map<String, Long> getStandingsMap() {
        return standingsMap;
    }
}

package Modelo;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private String name;
    private long id;
    private String shortName;
    private List<Player> squad;
    private Map<RESULT, Long> teamStatsMap;
    private long goalsScored;
    private long goalsReceived;

    public Team(JSONObject object){
        this.name = (String) object.get("name");
        this.id = (long) object.get("id");
        this.shortName = (String) object.get("tla");
        squad = new ArrayList<>();
        teamStatsMap = new HashMap<>();
    }

    public void setAllStats(JSONObject object){
        teamStatsMap.put(RESULT.WON, (Long) object.get("won"));
        teamStatsMap.put(RESULT.DRAW, (Long) object.get("draw"));
        teamStatsMap.put(RESULT.LOST, (Long) object.get("lost"));
        goalsReceived = (long) object.get("goalsAgainst");
        goalsScored = (long) object.get("goalsFor");
    }



    public Map<RESULT, Long> getTeamStatsMap() {
        return teamStatsMap;
    }

    public long getGoalsScored() {
        return goalsScored;
    }

    public long getGoalsReceived() {
        return goalsReceived;
    }

    public void addPlayerToSquad(Player p){
        squad.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public void setSquad(List<Player> squad) {
        this.squad = squad;
    }

    public Player getPlayerByID(long ID){
        for(Player p : squad){
            if (p.getId() == ID){
                return p;
            }
        }
        return null;
    }
}

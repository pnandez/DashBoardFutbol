package Modelo;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private long id;
    private String shortName;
    private List<Player> squad;

    public Team(JSONObject object){
        this.name = (String) object.get("name");
        this.id = (long) object.get("id");
        this.shortName = (String) object.get("tla");
        squad = new ArrayList<>();
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

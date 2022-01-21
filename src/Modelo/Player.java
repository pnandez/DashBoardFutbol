package Modelo;

import org.json.simple.JSONObject;

public class Player {
    private long id;
    private String name;
    private POSITIONS position;
    private String nationality;

    public Player(JSONObject object){
        this.id = (long) object.get("id");
        this.name = (String) object.get("name");
        this.position = POSITIONS.valueOf((String) object.get("position"));
        this.nationality = (String) object.get("nationality");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public POSITIONS getPos() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }
}

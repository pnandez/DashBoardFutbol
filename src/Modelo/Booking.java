package Modelo;

import org.json.simple.JSONObject;

public class Booking{
    private Player player;
    private Long minute;
    private Team team;
    private CARDS card;


    public Booking(JSONObject object){
        LaLigaModel model = LaLigaModel.getInstance();
        team = model.getTeamById((Long) ((JSONObject)object.get("team")).get("id"));
        player = team.getPlayerByID((Long) ((JSONObject)object.get("scorer")).get("id"));
        minute = (Long) object.get("minute");
        card = CARDS.valueOf((String) object.get("card"));
    }
}

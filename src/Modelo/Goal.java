package Modelo;

import org.json.simple.JSONObject;

public class Goal {
    private Player scorer;
    private Long minute;
    private Team team;
    private Player assister;

    public Goal(JSONObject object){
        LaLigaModel model = LaLigaModel.getInstance();
        team = model.getTeamById((Long) ((JSONObject)object.get("team")).get("id"));
        scorer = team.getPlayerByID((Long) ((JSONObject)object.get("scorer")).get("id"));
        minute = (Long) object.get("minute");
        if(object.get("assist") == null) {
            assister = team.getPlayerByID((Long) ((JSONObject) object.get("assist")).get("id"));
        }else{
            assister = null;
        }
    }

    public Boolean assistIsNull(){
        return assister == null;
    }

    public Player getScorer() {
        return scorer;
    }

    public Long getMinute() {
        return minute;
    }

    public Team getTeam() {
        return team;
    }

    public Player getAssister() {
        return assister;
    }
}

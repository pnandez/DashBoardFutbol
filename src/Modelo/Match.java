package Modelo;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Date;

public class Match {
    private long homeTeamID;
    private long awayTeamID;
    private long homeTeamScore;
    private long awayTeamScore;
    private Date matchDate;
    private long matchDay;

    public Match(JSONObject object) throws Exception {
        this.homeTeamID = (long) ((JSONObject) object.get("homeTeam")).get("id");
        this.awayTeamID = (long) ((JSONObject) object.get("awayTeam")).get("id");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.matchDate = dateFormat.parse((String) object.get("utcDate"));
        parseScore((JSONObject)object.get("score"));
    }

    private void parseScore(JSONObject score) {
        JSONObject fulltimeObj = (JSONObject) score.get("fullTime");
        this.homeTeamScore = (long) fulltimeObj.get("homeTeam");
        this.awayTeamScore = (long) fulltimeObj.get("awayTeam");
    }

    public long getHomeTeamID() {
        return homeTeamID;
    }

    public long getAwayTeamID() {
        return awayTeamID;
    }

    public long getHomeTeamScore() {
        return homeTeamScore;
    }

    public long getAwayTeamScore() {
        return awayTeamScore;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public long getMatchDay() {
        return matchDay;
    }

    public Boolean checkIfTeamPlayed(Team team){
        if(team.getId() == awayTeamID || team.getId() == homeTeamID){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Match{" +
                "homeTeamID=" + homeTeamID +
                ", awayTeamID=" + awayTeamID +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", matchDate=" + matchDate +
                ", matchDay=" + matchDay +
                '}';
    }
}

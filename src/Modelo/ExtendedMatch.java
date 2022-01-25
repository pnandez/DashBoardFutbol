package Modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtendedMatch implements IMatch{

    private long ID;
    private long homeTeamID;
    private long awayTeamID;
    private String homeTeamName;
    private String awayTeamName;
    private long homeTeamScore;
    private long awayTeamScore;
    private Date matchDate;
    private long matchDay;
    private List<String> refereesName = null;
    private String stadiumName = null;
    private long head2headNumberOfGoals;
    private long head2headMatches;
    private long head2headDraws;
    private long head2headWonHomeTeam;
    private long head2headWonAwayTeam;
    private Boolean extendedDataIsSet = false;

    public ExtendedMatch(JSONObject object) throws Exception {
        this.ID = (long)  object.get("id");
        this.homeTeamID = (long) ((JSONObject) object.get("homeTeam")).get("id");
        this.homeTeamName = (String) ((JSONObject) object.get("homeTeam")).get("name");
        this.awayTeamID = (long) ((JSONObject) object.get("awayTeam")).get("id");
        this.awayTeamName = (String) ((JSONObject) object.get("awayTeam")).get("name");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.matchDate = dateFormat.parse((String) object.get("utcDate"));
        this.matchDay = (long) object.get("matchday");
        parseScore((JSONObject) object.get("score"));
    }

    public void setExtendedData(){

        JSONObject object = new JSONObject();
        try {
            object = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/matches/" + this.getID(), "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        refereesName = new ArrayList<>();
        JSONArray refArray = (JSONArray) ((JSONObject)object.get("match")).get("referees");
        for(Object obj : refArray){
            refereesName.add((String) ((JSONObject)obj).get("name"));
        }
        stadiumName = (String) ((JSONObject)object.get("match")).get("venue");
        head2headMatches = (long) ((JSONObject)object.get("head2head")).get("numberOfMatches");
        head2headNumberOfGoals = (long) ((JSONObject)object.get("head2head")).get("totalGoals");
        head2headDraws = (long) ((JSONObject)((JSONObject)object.get("head2head")).get("homeTeam")).get("draws");
        head2headWonAwayTeam = (long) ((JSONObject)((JSONObject)object.get("head2head")).get("homeTeam")).get("losses");
        head2headWonHomeTeam = (long) ((JSONObject)((JSONObject)object.get("head2head")).get("homeTeam")).get("wins");
        extendedDataIsSet = true;
    }

    public Boolean checkIfExtendedInfoIsSet(){
        return extendedDataIsSet;
    }

    private void parseScore(JSONObject score) {
        JSONObject fulltimeObj = (JSONObject) score.get("fullTime");
        this.homeTeamScore = (long) fulltimeObj.get("homeTeam");
        this.awayTeamScore = (long) fulltimeObj.get("awayTeam");
    }

    @Override
    public long getID() {
        return this.ID;
    }

    @Override
    public long getHomeTeamID() {
        return homeTeamID;
    }

    @Override
    public long getAwayTeamID() {
        return awayTeamID;
    }

    @Override
    public long getHomeTeamScore() {
        return homeTeamScore;
    }

    @Override
    public long getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public Date getMatchDate() {
        return matchDate;
    }

    @Override
    public long getMatchDay() {
        return matchDay;
    }

    @Override
    public Boolean checkIfTeamPlayed(Team team){
        if(team.getId() == awayTeamID || team.getId() == homeTeamID){
            return true;
        }
        return false;
    }

    @Override
    public String getHomeTeamName() {
        return homeTeamName;
    }

    @Override
    public String getAwayTeamName() {
        return awayTeamName;
    }

    public List<String> getRefereesName() {
        return refereesName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public long getHead2headNumberOfGoals() {
        return head2headNumberOfGoals;
    }

    public long getHead2headMatches() {
        return head2headMatches;
    }

    public long getHead2headDraws() {
        return head2headDraws;
    }

    public long getHead2headWonHomeTeam() {
        return head2headWonHomeTeam;
    }

    public long getHead2headWonAwayTeam() {
        return head2headWonAwayTeam;
    }

    @Override
    public String toString() {
        return "ExtendedMatch{" +
                "ID=" + ID +
                ", homeTeamID=" + homeTeamID +
                ", awayTeamID=" + awayTeamID +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", matchDate=" + matchDate +
                ", matchDay=" + matchDay +
                ", refereesName=" + refereesName +
                ", stadiumName='" + stadiumName + '\'' +
                ", head2headNumberOfGoals=" + head2headNumberOfGoals +
                ", head2headMatches=" + head2headMatches +
                ", head2headDraws=" + head2headDraws +
                ", head2headWonHomeTeam=" + head2headWonHomeTeam +
                ", head2headWonAwayTeam=" + head2headWonAwayTeam +
                '}';
    }
}

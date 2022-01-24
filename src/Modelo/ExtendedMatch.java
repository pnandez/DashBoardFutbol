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
    private List<Goal> goalList = null;
    private List<Booking> bookingList = null;
    private long homeTeamID;
    private long awayTeamID;
    private String homeTeamName;
    private String awayTeamName;
    private long homeTeamScore;
    private long awayTeamScore;
    private Date matchDate;
    private long matchDay;

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
            object = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/matches/" + this, "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        parseScore((JSONObject)object.get("score"));
        this.matchDay = (long)  object.get("matchday");
        goalList = new ArrayList<>();
        bookingList = new ArrayList<>();
        JSONArray goalsArray = (JSONArray) object.get("goals");
        for (Object obj: goalsArray) {
            goalList.add(new Goal((JSONObject) obj));
        }
        JSONArray bookingsArray = (JSONArray) object.get("bookings");
        for (Object obj: bookingsArray) {
            bookingList.add(new Booking((JSONObject) obj));
        }

    }

    public Boolean checkIfExtendedInfoIsSet(){
        if(goalList == null || bookingList == null){
            return false;
        }
        return  true;
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

    @Override
    public List<Goal> getGoalList() {
        return goalList;
    }

    @Override
    public List<Booking> getBookingList() {
        return bookingList;
    }


    @Override
    public String toString() {
        return "ExtendedMatch{" +
                "goalList=" + goalList +
                ", bookingList=" + bookingList +
                ", homeTeamID=" + homeTeamID +
                ", awayTeamID=" + awayTeamID +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", matchDate=" + matchDate +
                ", matchDay=" + matchDay +
                '}';
    }


}

package Modelo;

import org.json.simple.JSONObject;


import java.util.Date;
import java.util.List;

//TODO Implementar proxy con match
public class MatchProxy implements IMatch{
    private ExtendedMatch extendedMatch = null;


    public MatchProxy(JSONObject object) throws Exception {
        extendedMatch = new ExtendedMatch(object);
    }

    @Override
    public long getID() {
        return extendedMatch.getID();
    }

    @Override
    public long getHomeTeamID() {
        return extendedMatch.getHomeTeamID();
    }

    @Override
    public long getAwayTeamID() {
        return extendedMatch.getAwayTeamID();
    }

    @Override
    public long getHomeTeamScore() {
        return extendedMatch.getHomeTeamScore();
    }

    @Override
    public long getAwayTeamScore() {
        return extendedMatch.getAwayTeamScore();
    }

    @Override
    public Date getMatchDate() {
        return extendedMatch.getMatchDate();
    }

    @Override
    public long getMatchDay() {
        return extendedMatch.getMatchDay();
    }

    @Override
    public Boolean checkIfTeamPlayed(Team team){
        if(team.getId() == extendedMatch.getAwayTeamID() || team.getId() == extendedMatch.getHomeTeamID()){
            return true;
        }
        return false;
    }

    @Override
    public String getHomeTeamName() {
        return extendedMatch.getHomeTeamName();
    }

    @Override
    public String getAwayTeamName() {
        return extendedMatch.getAwayTeamName();
    }

    @Override
    public List<String> getRefereesName() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getRefereesName();
    }

    @Override
    public String getStadiumName() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getStadiumName();
    }

    @Override
    public long getHead2headNumberOfGoals() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getHead2headNumberOfGoals();
    }

    @Override
    public long getHead2headMatches() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getHead2headMatches();
    }

    @Override
    public long getHead2headDraws() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getHead2headDraws();
    }

    @Override
    public long getHead2headWonHomeTeam() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getHead2headWonHomeTeam();
    }

    @Override
    public long getHead2headWonAwayTeam() {
        if(!extendedMatch.checkIfExtendedInfoIsSet()){
            extendedMatch.setExtendedData();
        }
        return extendedMatch.getHead2headWonAwayTeam();
    }


    @Override
    public String toString() {
        return extendedMatch.toString();
    }
}

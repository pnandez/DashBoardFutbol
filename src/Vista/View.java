package Vista;

import Modelo.Match;
import Modelo.POSITIONS;
import Modelo.RESULT;
import Modelo.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class View {

    protected String teamToShow;
    protected List<String> teamsList;
    protected List<String> playersNameList;
    protected Map<POSITIONS, Integer> positionsNumPlayersMap;
    protected Map<RESULT, Long> teamStatsMap;
    protected List<Match> matchList;

    protected View(){
        playersNameList = new ArrayList<>();
        positionsNumPlayersMap = new HashMap<>();
        teamStatsMap = new HashMap<>();
    }

    public abstract void display();

    public void setTeamsList(List<String> teamsList){
        this.teamsList = teamsList;
    }

    public void setTeam(String team){
        this.teamToShow = team;
    }

    public void setDataPlayers(List<String> playersNameList){
        this.playersNameList = playersNameList;
    }

    public void setDataPlayerPositionInTeam(Map<POSITIONS,Integer> mapPositionNum){
        this.positionsNumPlayersMap = mapPositionNum;
    }

    public void setDataMatches(List<Match> matchesList){
        this.matchList = matchesList;
    }

    public void setTeamStats(Map<RESULT, Long> teamStatsMap) {
        this.teamStatsMap = teamStatsMap;
    }
}

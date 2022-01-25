package Vista;

import Controlador.LaLigaController;
import Modelo.*;

import java.util.*;

public abstract class LaLigaView {

    LaLigaController controller;
    protected String teamToShow;
    protected List<String> teamsList;
    protected List<String> playersNameList;
    protected Map<POSITIONS, Integer> positionsNumPlayersMap;
    protected Map<RESULT, Long> teamStatsMap;
    protected List<IMatch> matchList;
    protected Map<String, Long> standingsMap;

    protected LaLigaView(){
        playersNameList = new ArrayList<>();
        positionsNumPlayersMap = new HashMap<>();
        teamStatsMap = new HashMap<>();
        teamsList = new ArrayList<>();
        standingsMap = new LinkedHashMap<>();
    }

    public abstract void display();

    public void setController(LaLigaController c){
        this.controller = c;
    }

    public void setTeamsList(List<String> teamsList){
        this.teamsList = teamsList;
    }

    public void setTeam(String team){
        this.teamToShow = team;
    }

    public void setDataPlayers(List<String> playersNameList){
        this.playersNameList = playersNameList;
    }

    public void setDataPlayerPositionInTeam(Map<POSITIONS,Integer> mapPositionNum) throws Exception {
        this.positionsNumPlayersMap = mapPositionNum;
    }

    public void setDataMatches(List<IMatch> matchesList){
        this.matchList = matchesList;
    }

    public void setTeamStats(Map<RESULT, Long> teamStatsMap) throws Exception {
        this.teamStatsMap = teamStatsMap;
    }

    public void setLaLigaStandings(Map<String, Long> standings){
        this.standingsMap = standings;
    }

}

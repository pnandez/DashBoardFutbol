package Vista;

import Modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewFacade {
    private View vista;

    private Team teamToShow;


    public ViewFacade(){
        vista = new VistaCLI();
    }

    public ViewFacade(View view){
        vista = view;
    }

    public View createView(Team teamToShow, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<Match> matchList, Map<RESULT, Long> mapStatsTeam){

        vista.setTeam(teamToShow.getName());
        List<String> listNamePlayers = new ArrayList<>();
        for(Player p : listPlayers){
            listNamePlayers.add(p.getName());
        }
        vista.setDataPlayers(listNamePlayers);
        vista.setDataPlayerPositionInTeam(positionsIntegerMap);
        vista.setDataMatches(matchList);
        vista.setTeamStats(mapStatsTeam);
        return vista;
    }

    public void updateView(Team teamToShow, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<Match> matchList){

    }

    public View getVista(){
        return vista;
    }
}

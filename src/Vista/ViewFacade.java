package Vista;

import Controlador.LaLigaController;
import Modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewFacade {
    private LaLigaView vista;

    private Team teamToShow;


    public ViewFacade(){
        vista = new VistaCLI();
    }

    public ViewFacade(LaLigaView view){
        vista = view;
    }

    public LaLigaView createView(LaLigaController c, Team teamToShow, List<Team> teamList, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<IMatch> matchProxyList, Map<RESULT, Long> mapStatsTeam, Standings standings){

        vista.setController(c);
        vista.setTeam(teamToShow.getName());
        List<String> teamNameList = new ArrayList<>();
        for (Team t: teamList) {
            teamNameList.add(t.getName());
        }
        vista.setTeamsList(teamNameList);
        List<String> listNamePlayers = new ArrayList<>();
        for(Player p : listPlayers){
            listNamePlayers.add(p.getName());
        }
        vista.setDataPlayers(listNamePlayers);
        try {
            vista.setDataPlayerPositionInTeam(positionsIntegerMap);
            vista.setDataMatches(matchProxyList);
            vista.setTeamStats(mapStatsTeam);
            vista.setLaLigaStandings(standings.getStandingsMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vista;
    }

    public void updateView(Team teamToShow, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<IMatch> matchProxyList, Map<RESULT, Long> mapStatsTeam){
        vista.setTeam(teamToShow.getName());
        List<String> listNamePlayers = new ArrayList<>();
        for(Player p : listPlayers){
            listNamePlayers.add(p.getName());
        }
        vista.setDataPlayers(listNamePlayers);
        try {
            vista.setDataPlayerPositionInTeam(positionsIntegerMap);
            vista.setDataMatches(matchProxyList);
            vista.setTeamStats(mapStatsTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LaLigaView getVista(){
        return vista;
    }
}

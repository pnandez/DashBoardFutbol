package Vista;

import Controlador.Controller;
import Controlador.LaLigaController;
import Modelo.Match;
import Modelo.POSITIONS;
import Modelo.Player;
import Modelo.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewFacade {
    private View vista;

    private Team teamToShow;


    public ViewFacade(){

    }

    public View createView(Team teamToShow, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<Match> matchList){
        vista = new VistaCLI();
        vista.setTeam(teamToShow.getName());
        List<String> listNamePlayers = new ArrayList<>();
        for(Player p : listPlayers){
            listNamePlayers.add(p.getName());
        }
        vista.setDataPlayers(listNamePlayers);
        vista.setDataPlayerPositionInTeam(positionsIntegerMap);
        vista.setDataMatches(matchList);
        return vista;
    }

    public void updateView(Team teamToShow, List<Player> listPlayers, Map<POSITIONS, Integer> positionsIntegerMap, List<Match> matchList){

    }

    public View getVista(){
        return vista;
    }
}

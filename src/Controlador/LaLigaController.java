package Controlador;

import Modelo.*;
import Vista.View;
import Vista.ViewFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaLigaController implements Controller {
    private LaLigaModel modelo;
    private Team teamToShow;
    private View vista;
    private ViewFacade fachadaVista;
    private static LaLigaController laLigaController = null;

    private LaLigaController(LaLigaModel model, View fachadaVista){
        this.modelo = model;
        this.fachadaVista = new ViewFacade();
        this.teamToShow = this.modelo.getTeamByName("CA Osasuna");
        try {
            model.initModel();
            this.vista = this.fachadaVista.createView(this.teamToShow, getAllPlayersFromTeam(),getMapNumberPlayersPosition(), getAllMatchesFromTeam());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private LaLigaController(LaLigaModel model){
        this.modelo = model;
        this.fachadaVista = new ViewFacade();
        try {
            model.initModel();
            this.teamToShow = this.modelo.getTeamByName("CA Osasuna");
            this.vista = this.fachadaVista.createView(this.teamToShow, getAllPlayersFromTeam(),getMapNumberPlayersPosition(), getAllMatchesFromTeam());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static LaLigaController getInstance(LaLigaModel modelo, View vista){
        if(laLigaController == null){
            laLigaController = new LaLigaController(modelo, vista);
        }
        return laLigaController;
    }

    public static LaLigaController getInstance(LaLigaModel modelo){
        if(laLigaController == null){
            laLigaController = new LaLigaController(modelo);
        }
        return laLigaController;
    }


    public void setTeamToShow(String teamName){
        this.teamToShow = modelo.getTeamByName(teamName);
    }


    public Map<POSITIONS, Integer> getMapNumberPlayersPosition() {
        List<Player> listaJugadores = teamToShow.getSquad();
        Map<POSITIONS, Integer> mapToReturn = new HashMap<>();
        mapToReturn.put(POSITIONS.Attacker, 0);
        mapToReturn.put(POSITIONS.Midfielder, 0);
        mapToReturn.put(POSITIONS.Defender, 0);
        mapToReturn.put(POSITIONS.Goalkeeper, 0);
        for(Player p : listaJugadores){
            mapToReturn.put(p.getPos(),mapToReturn.get(p.getPos())+1);
        }
        return mapToReturn;
    }

    public List<Player> getAllPlayersFromTeam(){
        return teamToShow.getSquad();
    }

    public List<Match> getAllMatchesFromTeam(){
        List<Match> matchList = new ArrayList<>();
        for(Match m: modelo.getGamesList()){
            if(m.checkIfTeamPlayed(teamToShow)){
                matchList.add(m);
            }
        }
        return matchList;
    }

    public View getVista() {
        return vista;
    }
}


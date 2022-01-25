package Controlador;

import Modelo.*;
import Vista.LaLigaView;
import Vista.ViewFacade;

import java.util.*;

public class LaLigaController implements Controller {
    private LaLigaModel modelo;
    private Team teamToShow;
    private LaLigaView vista;
    private ViewFacade fachadaVista;

    public LaLigaController(LaLigaModel model, LaLigaView view){
        this.modelo = model;
        this.fachadaVista = new ViewFacade(view);
        this.teamToShow = this.modelo.getTeamList().get(0);
        try {
            this.vista = this.fachadaVista.createView(this, this.teamToShow,getTeamList(), getAllPlayersFromTeam(),getMapNumberPlayersPosition(), getAllMatchesFromTeam(), getStatsFromTeam());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public LaLigaController(LaLigaModel model){
        this.modelo = model;
        this.fachadaVista = new ViewFacade();
        try {
            this.teamToShow = this.modelo.getTeamList().get(0);
            this.vista = this.fachadaVista.createView(this, this.teamToShow, getTeamList(), getAllPlayersFromTeam(),getMapNumberPlayersPosition(), getAllMatchesFromTeam(), getStatsFromTeam());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Team> getTeamList() {
        return modelo.getTeamList();
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

    public List<IMatch> getAllMatchesFromTeam(){
        List<IMatch> matchProxyList = new ArrayList<>();
        for(IMatch m: modelo.getGamesList()){
            if(m.checkIfTeamPlayed(teamToShow)){
                matchProxyList.add(m);
            }
        }
        Collections.sort(matchProxyList, new Comparator<IMatch>() {
            @Override
            public int compare(IMatch o1, IMatch o2) {
                return Long.compare(o1.getMatchDay(), o2.getMatchDay());
            }
        });
        return matchProxyList;
    }

    public void setNewTeamToShow(String newTeamToShow){
        this.teamToShow = modelo.getTeamByName(newTeamToShow);
        fachadaVista.updateView(teamToShow, getAllPlayersFromTeam(),getMapNumberPlayersPosition(),getAllMatchesFromTeam(), getStatsFromTeam());
    }


    private Map<RESULT, Long> getStatsFromTeam() {
        return teamToShow.getTeamStatsMap();
    }

    public LaLigaView getVista() {
        return vista;
    }

    public Team getTeamByID(long id){
        return modelo.getTeamById(id);
    }

    public IMatch getMatchByID(long id) {
        return modelo.getMatchByID(id);
    }
}


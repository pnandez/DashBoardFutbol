package Modelo;

import org.json.simple.*;

import java.util.ArrayList;
import java.util.List;

public class LaLigaModel {
    private static LaLigaModel modelInstance = null;
    private HTTPJSONDataFetcher dataFetcher;
    private String URL = null;
    private JSONObject rawData;
    private List<Team> teamList;
    private List<Match> gamesList;

    private LaLigaModel(){
        teamList = new ArrayList<>();
        gamesList = new ArrayList<>();
        dataFetcher = new HTTPJSONDataFetcher("http://api.football-data.org/v2/");
        dataFetcher.setRequestProperty("X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
    }

    public static LaLigaModel getInstance(){
        if(modelInstance == null)
            modelInstance = new LaLigaModel();
        return modelInstance;
    }

    public void initModel() throws Exception {
        setTeamList();
        Thread.sleep(1000);
        setPlayers();
        Thread.sleep(1000);
        setGamesList();

    }

    private void setTeamList() {
        try {
            JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/competitions/2014/teams","X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
            JSONArray teamsJSONArray = (JSONArray) rawObject.get("teams");
            System.out.println(rawObject.get("headers"));
            for(Object o :teamsJSONArray){
                teamList.add(new Team((JSONObject) o));
            }
        } catch (Exception e) {
            System.out.println("Exception in setTeams: "+ e.getMessage());
        }
    }

    private void setPlayers() {
        //TODO cuando acabe todo quitar el contador y el if y set el thread.sleep a 6000
        System.out.println(teamList.size());
        int contador = 0;
        for(Team t : teamList){
            contador++;
            if(contador < 10) {
                try {
                    JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/teams/" + t.getId(), "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
                    JSONArray teamsJSONArray = (JSONArray) rawObject.get("squad");
                    Thread.sleep(1000);
                    for (Object o : teamsJSONArray) {
                        t.addPlayerToSquad(new Player((JSONObject) o));
                    }

                } catch (Exception e) {
                    System.out.println("Exception in setPlayers: " + e.getCause() + e.getCause() + e.getMessage());
                }

                System.out.println(t.getName() + " -> " + t.getSquad().size());
            }
        }
    }

    private void setGamesList() {
        System.out.println(teamList.size());
            try {
                JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/competitions/2014/matches?status=FINISHED","X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
                JSONArray teamsJSONArray = (JSONArray) rawObject.get("matches");


                for(Object o :teamsJSONArray){
                    gamesList.add(new Match((JSONObject) o));
                }

            } catch (Exception e) {
                System.out.println("Exception in setMatches: " +e.getCause() + e.getCause()+ e.getMessage());
            }

            System.out.println(gamesList.size());
    }

    public Team getTeamByName(String teamToShow) {
        for(Team t: teamList){
            if(t.getName().equals(teamToShow)){
                return t;
            }
        }
        return null;
    }

    public List<Match> getGamesList(){
        return gamesList;
    }
}



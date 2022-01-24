package Modelo;

import org.json.simple.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LaLigaModel {
    private static LaLigaModel modelInstance = null;
    private HTTPJSONDataFetcher dataFetcher;
    private String URL = null;
    private JSONObject rawData;
    private List<Team> teamList;
    private List<IMatch> gamesList;

    private LaLigaModel() {
        teamList = new ArrayList<>();
        gamesList = new ArrayList<>();
        dataFetcher = new HTTPJSONDataFetcher("http://api.football-data.org/v2/");
        dataFetcher.setRequestProperty("X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
        try {
            initModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LaLigaModel getInstance() {
        if (modelInstance == null)
            modelInstance = new LaLigaModel();
        return modelInstance;
    }

    public void initModel() throws Exception {
        setTeamList();
        Thread.sleep(6000);
        setPlayers();
        Thread.sleep(10000);
        setGamesList();
        Thread.sleep(6000);
        setStatsForTeam();

    }

    private void setTeamList() {
        try {
            JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/competitions/2014/teams", "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
            JSONArray teamsJSONArray = (JSONArray) rawObject.get("teams");
            System.out.println(rawObject.get("headers"));
            for (Object o : teamsJSONArray) {
                teamList.add(new Team((JSONObject) o));
            }
        } catch (Exception e) {
            System.out.println("Exception in setTeams: " + e.getMessage());
        }
    }

    private void setPlayers() {
        //TODO cuando acabe todo quitar el contador y el if y set el thread.sleep a 6000
        System.out.println(teamList.size());
        int contador = 0;
        for (Team t : teamList) {
            contador++;
            if (contador < 10) {
                try {
                    JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/teams/" + t.getId(), "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
                    JSONArray teamsJSONArray = (JSONArray) rawObject.get("squad");
                    Thread.sleep(6000);
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
            JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/competitions/2014/matches?status=FINISHED", "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
            JSONArray teamsJSONArray = (JSONArray) rawObject.get("matches");


            for (Object o : teamsJSONArray) {
                gamesList.add(new MatchProxy((JSONObject) o));
            }

        } catch (Exception e) {
            System.out.println("Exception in setMatches: " + e.getCause() + e.getCause() + e.getMessage());
        }

        System.out.println(gamesList.size());
    }

    public Team getTeamById(long ID) {
        for (Team t : teamList) {
            if (t.getId() == (ID)) {
                return t;
            }
        }
        return null;
    }

    public Team getTeamByName(String teamToShow) {
        for (Team t : teamList) {
            if (t.getName().equals(teamToShow)) {
                return t;
            }
        }
        return null;
    }

    public List<IMatch> getGamesList() {
        return gamesList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public IMatch getInfoFromMatch(long ID) {
        try {
            JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/matches/" + ID, "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
            return new ExtendedMatch((JSONObject) rawObject.get("match"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void setStatsForTeam() {
        try {
            JSONObject rawObject = HTTPJSONGET.getDataFromURL("http://api.football-data.org/v2/competitions/2014/standings", "X-Auth-Token", "8075760f2a9f441295a9c7b1a6ad7b03");
            JSONArray standingsArray = (JSONArray) rawObject.get("standings");
            JSONArray tableArray = (JSONArray) ((JSONObject)standingsArray.get(0)).get("table");
            for(Object o : tableArray){

                getTeamById((Long) (((JSONObject)((JSONObject)o).get("team")).get("id"))).setAllStats(((JSONObject) o));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



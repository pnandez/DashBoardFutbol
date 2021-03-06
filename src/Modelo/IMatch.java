package Modelo;

import java.util.Date;
import java.util.List;

public interface IMatch {

    public long getID();

    public long getHomeTeamID();
    public long getAwayTeamID();

    public long getHomeTeamScore();

    public long getAwayTeamScore();
    public Date getMatchDate();
    public long getMatchDay();
    public Boolean checkIfTeamPlayed(Team team);

    public String getHomeTeamName();
    public String getAwayTeamName();

    public List<String> getRefereesName();
    public String getStadiumName();
    public long getHead2headNumberOfGoals();

    public long getHead2headMatches();

    public long getHead2headDraws();
    public long getHead2headWonHomeTeam();

    public long getHead2headWonAwayTeam();

}

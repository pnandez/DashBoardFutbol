package Modelo;

import java.util.Date;
import java.util.List;

public interface IMatch {
    public long getHomeTeamID();
    public long getAwayTeamID();

    public long getHomeTeamScore();

    public long getAwayTeamScore();
    public Date getMatchDate();
    public long getMatchDay();
    public Boolean checkIfTeamPlayed(Team team);

    public String getHomeTeamName();
    public String getAwayTeamName();
    public List<Goal> getGoalList();

    public List<Booking> getBookingList();

}

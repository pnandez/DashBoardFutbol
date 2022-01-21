package Modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendedMatch extends Match{

    private List<Goal> goalList;
    private List<Booking> bookingList;

    public ExtendedMatch(JSONObject object) throws Exception {
        super(object);
        goalList = new ArrayList<>();
        bookingList = new ArrayList<>();
        JSONArray goalsArray = (JSONArray) object.get("goals");
        for (Object obj: goalsArray) {
            goalList.add(new Goal((JSONObject) obj));
        }
        JSONArray bookingsArray = (JSONArray) object.get("bookings");
        for (Object obj: bookingsArray) {
            bookingList.add(new Booking((JSONObject) obj));
        }

    }
}

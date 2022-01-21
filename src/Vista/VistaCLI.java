package Vista;

import Modelo.Match;
import Modelo.POSITIONS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaCLI extends View{



    public VistaCLI(){
        super();
    }



    @Override
    public void display() {
        System.out.println("Mostrando datos del equipo: " + teamToShow);
        System.out.println("Mostrando nombres de jugadores: " + playersNameList);
        System.out.println("Mostrando jugadores por posicion: " + positionsNumPlayersMap);
        System.out.println("Mostrando proximos partidos: " );
        for(Match m : matchList){
            System.out.println(m.toString());
        }
    }

}

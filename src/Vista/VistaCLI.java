package Vista;

import Modelo.IMatch;
import Modelo.MatchProxy;

import java.util.Map;

public class VistaCLI extends LaLigaView {

    public VistaCLI(){
        super();
    }



    @Override
    public void display() {
        System.out.println("Mostrando datos del equipo: " + teamToShow);
        System.out.println("Mostrando nombres de jugadores: " + playersNameList);
        System.out.println("Mostrando jugadores por posicion: " + positionsNumPlayersMap);
        System.out.println("Mostrando proximos partidos: " );
        for(IMatch m : matchList){
            System.out.println(m.toString());
        }
        System.out.println("Mostrando estadísticas del equipo: " + teamStatsMap);
        System.out.println("Mostrando clasificación de la liga: ");
        System.out.println("[Posición, Equipo, Puntos]");
        int contador = 0;
        for(Map.Entry<String, Long> entry : standingsMap.entrySet()){
            System.out.println("[" + contador + ", " + entry.getKey() + ", " + entry.getValue() + "]");
        }
    }

}

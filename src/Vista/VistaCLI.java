package Vista;

import Modelo.IMatch;
import Modelo.MatchProxy;

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
    }

}

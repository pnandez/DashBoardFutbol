package Vista;

import Modelo.POSITIONS;

import java.util.HashMap;
import java.util.Map;

public class vistaGrafica1 extends View{

    private Chart pieChartPartidosGanadosPerdidosEmpatados;
    private Chart barChartNumJugadoresPorPos;

    public vistaGrafica1(){
        super();
        //TODO Comprobar si los xAxis y yAxis name salen en PieChart si no ponerlos genéricos
        try {
            pieChartPartidosGanadosPerdidosEmpatados = new PieChart("Partidos jugados", "Resultado de partidos", "Numero de partidos");
            barChartNumJugadoresPorPos = new BarChart("Número de jugadores por posición.", "Posición", "Número de jugadores", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void display() {

    }

    @Override
    public void setDataPlayerPositionInTeam(Map<POSITIONS, Integer> mapPositionNum){
        this.positionsNumPlayersMap = mapPositionNum;
        Map<String, Double> finalMap = new HashMap<>();
        for (Map.Entry<POSITIONS,Integer> entry : mapPositionNum.entrySet()){
            finalMap.put(entry.getKey().toString(),entry.getValue().doubleValue());
        }
        barChartNumJugadoresPorPos.setDataMap_(finalMap);
    }


}

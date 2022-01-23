package Vista;

import Modelo.POSITIONS;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class vistaGrafica1 extends View{

    private Chart pieChartPartidosGanadosPerdidosEmpatados;
    private Chart barChartNumJugadoresPorPos;
    private JFrame frame;
    private JPanel panelPrincipal;
    private JPanel panelCharts;
    private JPanel panelLists;
    private JComboBox<String> teamSelectorComboBox;
    private JScrollPane scrollPanelTabla;
    private JList<String> listaNombreJugadores;
    private JTable partidosResultadosTable;

    public vistaGrafica1(){
        super();
        //TODO Comprobar si los xAxis y yAxis name salen en PieChart si no ponerlos genéricos
        try {
            pieChartPartidosGanadosPerdidosEmpatados = new PieChart("Partidos jugados", "Resultado de partidos", "Numero de partidos");
            barChartNumJugadoresPorPos = new BarChart("Número de jugadores por posición.", "Posición", "Número de jugadores", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        frame = new JFrame("Dashboard LaLiga");
        panelCharts = new JPanel(new GridBagLayout());
        panelLists = new JPanel(new GridBagLayout());
        panelPrincipal = new JPanel(new GridBagLayout());
        scrollPanelTabla = new JScrollPane();

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

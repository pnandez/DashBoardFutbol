package Vista;

import Modelo.IMatch;
import Modelo.MatchProxy;
import Modelo.POSITIONS;
import Modelo.RESULT;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class vistaGrafica1 extends LaLigaView {

    private Chart pieChartPartidosGanadosPerdidosEmpatados;
    private Chart barChartNumJugadoresPorPos;
    private JFrame frame;
    private JPanel panelPrincipal;
    private JPanel panelCharts;
    private JPanel panelListTable;
    private JPanel panelList;
    private JLabel labelLista;
    private JPanel panelTabla;
    private JComboBox<String> teamSelectorComboBox;
    private JScrollPane scrollPanelTabla;
    private JScrollPane scrollPanelList;
    private JList<String> listaNombreJugadores;
    private JTable partidosResultadosTable;
    private JButton getMoreInfoMatchButton;
    Map<String, Double> finalMapBarChart;
    Map<String, Double> finalMapPieChart;
    DefaultListModel listModel;
    DefaultTableModel tableModel;


    public vistaGrafica1(){
        super();
        try {
            pieChartPartidosGanadosPerdidosEmpatados = new PieChart("Partidos jugados", "Resultado de partidos", "Numero de partidos");
            barChartNumJugadoresPorPos = new BarChart("Número de jugadores por posición.", "Posición", "Número de jugadores", "");
            finalMapBarChart = new HashMap<>();
            finalMapPieChart = new HashMap<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        frame = new JFrame("Dashboard LaLiga");
        panelCharts = new JPanel(new GridBagLayout());
        panelListTable = new JPanel(new GridBagLayout());
        panelList = new JPanel(new GridBagLayout());
        panelTabla = new JPanel(new GridBagLayout());
        panelPrincipal = new JPanel(new GridBagLayout());
        labelLista = new JLabel("Jugadores del equipo:");
        partidosResultadosTable = new JTable();
        listaNombreJugadores = new JList<>();
        teamSelectorComboBox = new JComboBox<>();
        getMoreInfoMatchButton = new JButton("Ver Detalles");
        getMoreInfoMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = partidosResultadosTable.getSelectedRow();
                System.out.println("Button clicked. IMatch info = " + tableModel.getDataVector().elementAt(row).toString());
                System.out.println("Real match info: " + matchList.get(row).toString());
            }
        });
        listModel = new DefaultListModel();
        listaNombreJugadores.setPreferredSize(new Dimension(200, 400));
        scrollPanelList = new JScrollPane(listaNombreJugadores);

        tableModel = new DefaultTableModel();


        partidosResultadosTable.setModel(tableModel);
        tableModel.addColumn("Jornada");
        tableModel.addColumn("Local");
        tableModel.addColumn("Resultado");
        tableModel.addColumn("Visitante");
        partidosResultadosTable.getTableHeader().setOpaque(false);


        GridBagConstraints panelListsConstraints = new GridBagConstraints();
        panelListsConstraints.gridy = 0;
        panelList.add(labelLista, panelListsConstraints);
        panelListsConstraints.gridy = 1;
        panelList.add(scrollPanelList, panelListsConstraints);


        partidosResultadosTable.setPreferredScrollableViewportSize(new Dimension(450,300));
        partidosResultadosTable.setFillsViewportHeight(true);

        scrollPanelTabla = new JScrollPane(partidosResultadosTable);
        scrollPanelTabla.setVisible(true);

        GridBagConstraints panelTablaConstraints = new GridBagConstraints();
        panelTablaConstraints.gridy = 0;
        panelTabla.add(scrollPanelTabla, panelTablaConstraints);
        panelTablaConstraints.gridy = 1;
        panelTabla.add(getMoreInfoMatchButton, panelTablaConstraints);

    }

    @Override
    public void display() {
        Border borde = BorderFactory.createEmptyBorder(40, 10, 40, 10);
        panelPrincipal.setBorder(borde);

        setComboBoxData();

        setList();
        setTable();
        GridBagConstraints panelListTableConstraints = new GridBagConstraints();
        panelListTableConstraints.gridx = 0;
        panelListTable.add(panelList, panelListTableConstraints);
        panelListTableConstraints.gridx = 1;
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(100, 100));
        panelListTable.add(pn, panelListTableConstraints);
        panelListTableConstraints.gridx = 2;
        panelListTable.add(panelTabla, panelListTableConstraints);

        createCharts();

        GridBagConstraints panelPrincipalConstraints = new GridBagConstraints();
        panelPrincipalConstraints.gridy = 0;
        panelPrincipal.add(teamSelectorComboBox, panelPrincipalConstraints);
        panelPrincipalConstraints.gridy = 1;
        panelPrincipal.add(panelListTable, panelPrincipalConstraints);
        panelPrincipalConstraints.gridy = 2;
        panelPrincipal.add(panelCharts, panelPrincipalConstraints);


        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 1,3));
        frame.add(panelPrincipal);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void setTable() {
        tableModel.setRowCount(0);
        for(IMatch m : matchList){
            String result = m.getHomeTeamScore() + " - " + m.getAwayTeamScore();
            tableModel.addRow(new Object []{m.getMatchDay(),m.getHomeTeamName(), result, m.getAwayTeamName()});
        }
    }

    private void setList() {
        listModel.clear();
        listaNombreJugadores.setModel(listModel);
        for (int i = 0; i < playersNameList.size(); i++) {
            listModel.add(i,playersNameList.get(i));
        }



    }

    private void setComboBoxData() {
        for (String teamName: teamsList) {
            teamSelectorComboBox.addItem(teamName);
        }
        teamSelectorComboBox.setSelectedIndex(0);
        teamSelectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNewTeamToShow((String) teamSelectorComboBox.getSelectedItem());
                repaint();
            }
        });
    }

    private void repaint() {
        setTable();
        setList();
        repaintCharts();
        frame.repaint();
        System.out.println("UPDATED" + teamToShow);
    }

    private void repaintCharts() {
        finalMapBarChart.clear();
        for (Map.Entry<POSITIONS,Integer> entry : this.positionsNumPlayersMap.entrySet()){
            finalMapBarChart.put(entry.getKey().toString(),entry.getValue().doubleValue());
        }
        barChartNumJugadoresPorPos.updateDataset(finalMapBarChart);
        finalMapPieChart.clear();
        for(Map.Entry<RESULT,Long> entry: this.teamStatsMap.entrySet()){
            finalMapPieChart.put(entry.getKey().toString(), Double.valueOf(entry.getValue()));
        }
        pieChartPartidosGanadosPerdidosEmpatados.updateDataset(finalMapPieChart);
    }

    private void createCharts() {
        try {
            setDatasets();
            pieChartPartidosGanadosPerdidosEmpatados.createChart();
            barChartNumJugadoresPorPos.createChart();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        GridBagConstraints panelChartsConstraints = new GridBagConstraints();
        try {
            panelChartsConstraints.gridx = 0;
            panelCharts.add(pieChartPartidosGanadosPerdidosEmpatados.DrawChart(),panelChartsConstraints);
            panelChartsConstraints.gridx = 1;
            panelCharts.add(barChartNumJugadoresPorPos.DrawChart(),panelChartsConstraints);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setDatasets(){
        finalMapBarChart.clear();
        for (Map.Entry<POSITIONS,Integer> entry : this.positionsNumPlayersMap.entrySet()){
            finalMapBarChart.put(entry.getKey().toString(),entry.getValue().doubleValue());
        }
        barChartNumJugadoresPorPos.setDataMap_(finalMapBarChart);
        for(Map.Entry<RESULT,Long> entry: this.teamStatsMap.entrySet()){
            finalMapPieChart.put(entry.getKey().toString(), Double.valueOf(entry.getValue()));
        }
        pieChartPartidosGanadosPerdidosEmpatados.setDataMap_(finalMapPieChart);

    }

}

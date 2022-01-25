package Vista;

import Modelo.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
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
    private JPanel panelClasificacion;
    private JComboBox<String> teamSelectorComboBox;
    private JScrollPane scrollPanelTabla;
    private JScrollPane scrollPanelList;
    private JList<String> listaNombreJugadores;
    private JTable partidosResultadosTable;
    private JTable clasificaciónLigaTabla;
    DefaultTableModel tableStandingsModel;
    private JButton getMoreInfoMatchButton;
    Map<String, Double> finalMapBarChart;
    Map<String, Double> finalMapPieChart;
    DefaultListModel listModel;
    DefaultTableModel tableModel;


    public vistaGrafica1() {
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
        panelClasificacion = new JPanel(new GridBagLayout());
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
                displayWindowInfoMatch(controller.getMatchByID(matchList.get(row).getID()));
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


        partidosResultadosTable.setPreferredScrollableViewportSize(new Dimension(450, 300));
        partidosResultadosTable.setFillsViewportHeight(true);

        scrollPanelTabla = new JScrollPane(partidosResultadosTable);
        scrollPanelTabla.setVisible(true);

        GridBagConstraints panelTablaConstraints = new GridBagConstraints();
        panelTablaConstraints.gridy = 0;
        panelTabla.add(scrollPanelTabla, panelTablaConstraints);
        panelTablaConstraints.gridy = 1;
        panelTabla.add(getMoreInfoMatchButton, panelTablaConstraints);

        clasificaciónLigaTabla = new JTable();
        tableStandingsModel = new DefaultTableModel();
        clasificaciónLigaTabla.setModel(tableStandingsModel);
        tableStandingsModel.addColumn("Puesto");
        tableStandingsModel.addColumn("Equipo");
        tableStandingsModel.addColumn("Puntos");


        clasificaciónLigaTabla.getTableHeader().setOpaque(false);
        clasificaciónLigaTabla.setPreferredScrollableViewportSize(new Dimension(450,200));

        JScrollPane scrollPanelClasificación = new JScrollPane(clasificaciónLigaTabla);
        scrollPanelClasificación.setVisible(true);
        panelClasificacion.add(scrollPanelClasificación);
    }

    private void createTablaClasificación() {
        int contador = 0;
        for (Map.Entry<String, Long> entry:standingsMap.entrySet() ) {
            tableStandingsModel.addRow(new Object[]{++contador,entry.getKey(), entry.getValue()});
        }
    }

    @Override
    public void display() {
        Border borde = BorderFactory.createEmptyBorder(40, 10, 40, 10);
        panelPrincipal.setBorder(borde);

        setComboBoxData();

        setList();
        setTable();
        createTablaClasificación();
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
        panelPrincipalConstraints.gridy = 3;
        panelPrincipal.add(panelClasificacion, panelPrincipalConstraints);


        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 1,4));
        frame.add(panelPrincipal);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    private void setDatasets(){
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


    //TODO ADD STADIUM + REFS LIST + H2HTOTALS TABLE
    private void displayWindowInfoMatch(IMatch match){
        JFrame frame2 = new JFrame("Información detallada del partido");
        JPanel panelPrincipalFrame2 = new JPanel(new GridBagLayout());

        JPanel panelLabels = new JPanel(new GridBagLayout());
        JPanel panelList = new JPanel(new GridBagLayout());

        JPanel panelTablas = new JPanel(new GridBagLayout());
        JLabel labelFechas = new JLabel("Jornada: " + match.getMatchDay() + " Fecha: " + match.getMatchDate() + " Estadio: " + match.getStadiumName());
        String result = match.getHomeTeamScore() + " - " + match.getAwayTeamScore();
        JLabel labelEquiposYResultado = new JLabel(match.getHomeTeamName() + " " + result + " " + match.getAwayTeamName());

        JList<String> refereesList = new JList<>();
        DefaultListModel refsListModel = new DefaultListModel();
        refereesList.setModel(refsListModel);
        JLabel labelArbitros = new JLabel("Árbitros del partido");
        setRefereesList(match, refsListModel);

        GridBagConstraints panelListConstraints = new GridBagConstraints();
        panelListConstraints.gridy = 0;
        panelList.add(labelArbitros, panelListConstraints);
        panelListConstraints.gridy = 1;
        panelList.add(refereesList,panelListConstraints);



        JLabel labelHead2Head = new JLabel("Enfrentamientos históricos.");
        DefaultTableModel tablaModelHead2HeadTotals = new DefaultTableModel();
        JTable tableHead2HeadTotals  = new JTable();
        tableHead2HeadTotals.setModel(tablaModelHead2HeadTotals);
        tablaModelHead2HeadTotals.addColumn("Partidos totales");
        tablaModelHead2HeadTotals.addColumn("Goles totales");
        setHead2HeadTotalsTable(match, tablaModelHead2HeadTotals);
        tableHead2HeadTotals.getTableHeader().setOpaque(false);
        tableHead2HeadTotals.setPreferredScrollableViewportSize(new Dimension(400,20));



        DefaultTableModel tablaModelHead2HeadStandings = new DefaultTableModel();
        JTable tableHead2HeadStandings  = new JTable();
        tableHead2HeadStandings.setModel(tablaModelHead2HeadStandings);
        tablaModelHead2HeadStandings.addColumn("Victorias de " + match.getHomeTeamName());
        tablaModelHead2HeadStandings.addColumn("Draw");
        tablaModelHead2HeadStandings.addColumn("Victorias de " + match.getAwayTeamName());
        setHead2HeadStandingsTable(match, tablaModelHead2HeadStandings);
        tableHead2HeadStandings.getTableHeader().setOpaque(false);
        tableHead2HeadStandings.setPreferredScrollableViewportSize(new Dimension(400,20));


        GridBagConstraints panelTablasConstraints = new GridBagConstraints();
        panelTablasConstraints.gridy = 0;
        panelTablas.add(labelHead2Head, panelTablasConstraints);
        panelTablasConstraints.gridy = 1;
        JScrollPane panelScrollTablaStandings = new JScrollPane(tableHead2HeadStandings);
        panelTablas.add(panelScrollTablaStandings,panelTablasConstraints);
        panelTablasConstraints.gridy = 2;
        panelTablas.add(new JPanel(), panelTablasConstraints);
        panelTablasConstraints.gridy = 3;
        JScrollPane panelScrollTablaTotals = new JScrollPane(tableHead2HeadTotals);
        panelTablas.add(panelScrollTablaTotals, panelTablasConstraints);


        GridBagConstraints panelLabelsConstraints = new GridBagConstraints();
        panelLabelsConstraints.gridy = 0;
        panelLabelsConstraints.gridx = 1;
        panelLabels.add(labelFechas,panelLabelsConstraints);
        panelLabelsConstraints.gridy = 1;
        panelLabelsConstraints.gridx = 1;
        panelLabels.add(labelEquiposYResultado,panelLabelsConstraints);

        GridBagConstraints panelPrincipal2Constraints = new GridBagConstraints();
        panelPrincipal2Constraints.gridy = 0;
        panelPrincipal2Constraints.gridx = 1;
        panelPrincipalFrame2.add(panelLabels, panelPrincipal2Constraints);
        panelPrincipal2Constraints.gridy = 1;
        panelPrincipal2Constraints.gridx = 1;
        panelPrincipalFrame2.add(panelList, panelPrincipal2Constraints);
        panelPrincipal2Constraints.gridy = 2;
        panelPrincipal2Constraints.gridx = 1;
        panelPrincipalFrame2.add(panelTablas, panelPrincipal2Constraints);


        frame2.setMinimumSize(new Dimension(600,200));
        frame2.setLayout(new FlowLayout(FlowLayout.CENTER, 1,3));
        frame2.add(panelPrincipalFrame2);
        frame2.setResizable(false);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
    }

    private void setRefereesList(IMatch match, DefaultListModel refsListModel) {
        refsListModel.clear();
        for (int i = 0; i < match.getRefereesName().size(); i++) {
            refsListModel.add(i,match.getRefereesName().get(i));
        }
    }

    private void setHead2HeadTotalsTable(IMatch match,DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{match.getHead2headMatches(), match.getHead2headNumberOfGoals() });

    }

    private void setHead2HeadStandingsTable(IMatch match, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{match.getHead2headWonHomeTeam(), match.getHead2headDraws(), match.getHead2headWonAwayTeam() });
    }

}

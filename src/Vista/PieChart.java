package Vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.ArrayList;
import java.util.Map;

public class PieChart extends Chart{
    public PieChart(){}

    public PieChart(String title, String xAxis, String yAxis) throws Exception{
        super(title,xAxis,yAxis);
    }


    public PieChart(String xAxis, String yAxis) throws Exception {
        super("Grafica de tarta", xAxis, yAxis);
    }

    @Override
    public JFreeChart createChart() throws Exception {
        chart_ = ChartFactory.createPieChart(title_, (PieDataset) createDataset(),true, true, false);
        return chart_;
    }




    @Override
    public AbstractDataset createDataset() throws Exception {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for(Map.Entry<String, Double> entry: dataMap_.entrySet()){
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        return pieDataset;
    }

    @Override
    public AbstractDataset updateDataset(Map<String, Double> map) {
        DefaultPieDataset abstractDataset = (DefaultPieDataset) ((PiePlot)chart_.getPlot()).getDataset();
        abstractDataset.clear();
        for(Map.Entry<String, Double> entry: dataMap_.entrySet()){
            abstractDataset.setValue(entry.getKey(),entry.getValue());
        }
        return abstractDataset;
    }

    protected  void setBg() {};



}

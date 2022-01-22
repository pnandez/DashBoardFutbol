package Vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.ArrayList;
import java.util.Map;

public class PieChart extends Chart{
    public PieChart(){}


    public PieChart(String xAxis, String yAxis) throws Exception {
        super("Grafica de tarta", xAxis, yAxis);
    }

    @Override
    public JFreeChart createChart() throws Exception {
        chart_ = ChartFactory.createLineChart(title_,xAxisName_,yAxisName_, (CategoryDataset) createDataset(), PlotOrientation.VERTICAL,false,true,true);
        chart_ = ChartFactory.createPieChart(title_, (PieDataset) createDataset(),true, true, false);
        return chart_;
    }




    @Override
    public AbstractDataset createDataset() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(Map.Entry<String, Double> entry: dataMap_.entrySet()){
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        return dataset;
    }

    protected  void setBg() {};



}

package Vista;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.general.AbstractDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public abstract class Chart {
    protected String title_;
    protected JFreeChart chart_;
    protected String xAxisName_;
    protected String yAxisName_;
    protected Map<String, Double> dataMap_;


    public Chart(){}


    public Chart(String title, String xAxisName, String yAxisName, Map<String, Double> dataMap) throws Exception {
        title_ = title;
        xAxisName_ = xAxisName;
        yAxisName_ = yAxisName;
        dataMap_ = dataMap;
    }

    protected void createChart(Double[] rangeMinMaxValues) throws Exception {
        this.createChart();
        reArrangeYAxisRange(rangeMinMaxValues);
    }

    public JPanel DrawChart() throws Exception {
        JPanel chartPanel =new ChartPanel(chart_);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setBorder(new EmptyBorder(10,10,10,10));
        return chartPanel;
    }

    protected ArrayList<String> reduceSizeArray(ArrayList<String> axisArray){
        if(axisArray.size() > 400){
            int delimeter = 0;
            delimeter = axisArray.size() / 40;
            ArrayList<String> result = new ArrayList<>();
            for(int i = 0; i < axisArray.size(); i= i+delimeter){
                result.add(axisArray.get(i));
            }
            return result;
        }
        return axisArray;
    }

    public void reArrangeYAxisRange(Double[] rangeMinMaxValues){
        NumberAxis numberAxis = (NumberAxis) chart_.getCategoryPlot().getRangeAxis();

        numberAxis.setRange(rangeMinMaxValues[0], rangeMinMaxValues[1]);

    }

    public abstract JFreeChart createChart() throws Exception;

    public abstract AbstractDataset createDataset() throws Exception;

}

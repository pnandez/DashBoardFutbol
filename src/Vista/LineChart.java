package Vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import java.util.Map;

public class LineChart extends Chart{
    private String categoryAxis_;

    public LineChart(){}

    public LineChart(String title,String xAxis, String yAxis, String categoryAxis, Map<String, Double> dataMap) throws Exception {

        super(title, xAxis, yAxis, dataMap);
        categoryAxis_ = "categoryAxis";
    }

    public LineChart(String title,String xAxis, String yAxis, String categoryAxis, Map<String, Double> dataMap, Double[] rangeMinMaxValues) throws Exception {
        super(title, xAxis, yAxis, dataMap);
        categoryAxis_ = categoryAxis;
    }

    @Override
    public JFreeChart createChart() throws Exception {
        chart_ = ChartFactory.createLineChart(title_,xAxisName_,yAxisName_, (CategoryDataset) createDataset(), PlotOrientation.VERTICAL,false,true,true);
        CategoryAxis axis = chart_.getCategoryPlot().getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        return chart_;
    }

    @Override
    public AbstractDataset createDataset() throws Exception {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Map.Entry<String, Double> entry: dataMap_.entrySet()){
            dataset.addValue(entry.getValue(), categoryAxis_,entry.getKey().toString());
        }
        return dataset;
    }

    @Override
    public AbstractDataset updateDataset(Map<String, Double> map) {
        return null;
    }

}


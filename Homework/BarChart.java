import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;  

public class BarChart extends JInternalFrame
{
    private static final long serialVersionUID = 1L;  
  
    public BarChart(String appTitle, int[] time, int[] space, int[] accuracy) {  
      super(appTitle);  
    
      // Create Dataset  
      CategoryDataset dataset = createDataset(time, space, accuracy);  
        
      //Create chart  
      JFreeChart chart=ChartFactory.createBarChart(  
          "Change-Making Results", //Chart Title  
          "Complexity Type", // Category X axis  
          "Numerical Difference", // Value Y axis  
          dataset,  
          PlotOrientation.VERTICAL,  
          true,true,false  
         );  
    
      ChartPanel panel=new ChartPanel(chart);  
      setContentPane(panel);  
    }  
    
    private CategoryDataset createDataset(int[] time, int[] space, int[] accuracy) {  
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
    
      // Population in 2005  
      dataset.addValue(time[0], "Standard", "Time");  
      dataset.addValue(time[1], "With Multiple Finder", "Time");  
      dataset.addValue(time[2], "With Remainder Finder", "Time");  
    
      // Population in 2010  
      dataset.addValue(space[0], "Standard", "Space");  
      dataset.addValue(space[1], "With Multiple Finder", "Space");  
      dataset.addValue(space[2], "With Remainder Finder", "Space");  
    
      // Population in 2015  
      dataset.addValue(accuracy[0], "Standard", "Accuracy");  
      dataset.addValue(accuracy[1], "With Multiple Finder", "Accuracy");  
      dataset.addValue(accuracy[2], "With Remainder Finder", "Accuracy");  
    
      return dataset;  
    }  
    
    
}

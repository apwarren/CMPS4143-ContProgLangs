import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;  


/**
 * public   :   BarChart
 *  This class implements and creates a bar graph containing
 *  multiple sets of data to be visualized graphically. This class
 *  uses the JFreeChart library to easily implement the graph and 
 *  is derived from the JInternal in order to add the graph to the
 *  overall GUI frame that contains all of the project. This bar 
 *  graph specifically contains three sets of data. It holds 
 *  each of the three algorithms (greedy, greedy w/ multiple,
 *  greedy w/ remainder) and then separates all three of them into
 *  how them perform by measuring time complexity, space complexity,
 *  and the overall accuracy of each algorithm. All complexity
 *  information is given when creating the bar graph and then
 *  properly categorized internally. This class should only be used
 *  when being implemented into a java frame and should never be
 *  altered or changed after a BarChart has been created.
 */
public class BarChart extends JInternalFrame
{
    private static final long serialVersionUID = 1L;  
  
    /**
     * public   :  BarChart
     *  Contstructor used to implement a bar chart graphically.
     *  The class inherits from JInternalFrame to create a bar graph
     *  within a java frame. As this chart will be used and added to an
     *  overall java frame containing other elements, it is to be a
     *  internalframe in order to for it to be within the general frame. 
     *  The contstructor will initialize any elements needed within the
     *  JInternalFrame class using the super() method and then continue
     *  to initialize the overall aspects of the bar graph such as 
     *  X and Y labels, graph title, and how all data should be plotted.
     * 
     * Parameters:
     *      String  : Title of internal frame
     *      int[]   : array containing all time complexities
     *      int[]   : array containing all space complexities
     *      int[]   : array containing all accuracy percentages
     * Returns:
     *      Void
     */
    public BarChart(String appTitle, int[] time, int[] space, int[] accuracy) 
    {  
      //Initialize all elements within the JInternalFrame including the title of the frame
      super(appTitle);
    
      // Create Dataset holding time, space, and accuracy complexities for 3 algorithms
      CategoryDataset dataset = createDataset(time, space, accuracy);  
        
      //Create chart to implement dataset into the form of a bar graph
      JFreeChart chart = ChartFactory.createBarChart
      (  
          "Change-Making Results",  // Chart Title for entire graph
          "Complexity Type",        // Overall Category of X-axis  
          "Numerical Difference",   // Numerical values between algorithms Y-axis  
          dataset,                  // Add all data found for all algorithms and complexities
          PlotOrientation.VERTICAL, // Show as vertical bar graph 
          true,true,false            
         );  
    
      //Place Chart onto a panel before adding to frame
      ChartPanel panel = new ChartPanel(chart);  
      //Set the panel onto an internal frame 
      setContentPane(panel);  
    }  
    
    /**
     * private   :   createDataSet
     *  This method creates a data set to implement into a chart.
     *  The dataset will contain three different sets of data.
     *  It will contain time complexity, space complexity, and
     *  the accuracy of three different algorithm methods. The
     *  data set will properly all of these sets of data accordingly
     *  and space out the three sets to easily be able to distinguish
     *  between all three. This method can only be called within the 
     *  BarChart class itself and is called when constructing an
     *  implementation of a BarChart.
     * 
     * Parameters:
     *      int[]  :  array containing all time complexities
     *      int[]  :  array containing all space complexities
     *      int[]  :  array containg all accuracy percentages
     * Returns:
     *      CategoryDataSet : set of data organized to be implemented into bar graph
     */
    private CategoryDataset createDataset(int[] time, int[] space, int[] accuracy)
    {  
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
    
      //Add Time Complexities
      dataset.addValue(time[0], "Standard", "Time");
      dataset.addValue(time[1], "With Multiple Finder", "Time");  
      dataset.addValue(time[2], "With Remainder Finder", "Time");  
    
      //Add Space Complexities
      dataset.addValue(space[0], "Standard", "Space");  
      dataset.addValue(space[1], "With Multiple Finder", "Space");  
      dataset.addValue(space[2], "With Remainder Finder", "Space");  
    
      //Add Accuracy of each algorithm
      dataset.addValue(accuracy[0], "Standard", "Accuracy");  
      dataset.addValue(accuracy[1], "With Multiple Finder", "Accuracy");  
      dataset.addValue(accuracy[2], "With Remainder Finder", "Accuracy");  
    
      //Return fully filled set of data to add to bar graph
      return dataset;  
    }  
    
    
}

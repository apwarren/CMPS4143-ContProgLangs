import java.lang.reflect.InvocationTargetException;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

public class GUI
{
    int[] timeCases;
    int[] spaceCases;
    int[] bestCases;
    public GUI(int[] time, int[] space, int[] bestCase)
    {
        timeCases = time;
        spaceCases = space;
        bestCases = bestCase;
    }

    public void getGUI() throws InvocationTargetException, InterruptedException, NumberFormatException
    {
        try
        {
            SwingUtilities.invokeAndWait(()->
            { 
                //Create Main Frame Window for graphics
                JFrame frame = new JFrame();
                frame.setSize(1000,600);
                frame.setLocationRelativeTo(null);  
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
                frame.setVisible(true);  
                //-----------------------------------------------------------------------
                //Create Bar chart Showing tested data for complexities
                BarChart chart = new BarChart("Variables", timeCases, new int[]{1,2,3}, bestCases);           
                chart.pack();
                chart.setLocation(20, 40);
                chart.setSize(950, 500);
                chart.setTitle("Algorithm Comparison Chart");
                //-----------------------------------------------------------------------
                //Create Internal Frame to hold User Input for Making Change
                JInternalFrame insideChange = new JInternalFrame();
                insideChange.setLocation(250, 100);
                insideChange.setSize(500, 250);
                insideChange.setVisible(true);
                //----------------------------------------------------------------------
                //Create Text Field to Get user Input for Making Change
                JTextField xField = new JTextField(5);
                JTextField yField = new JTextField(5);
                JPanel myPanel = new JPanel();
                myPanel.setVisible(false);
                myPanel.add(new JLabel("How Much Money do you Have?"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("How Much Money does it Cost?"));
                myPanel.add(yField);
                //---------------------------------------------------------------------
                //Create Button to Activate Making Change
                JButton getChange = new JButton("ATM Change Counter");
                //Listen for when button is clicked to make change
                getChange.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent r)
                    {
                        //Show Prompt for inputing data to make change
                        myPanel.setVisible(true);
                        getChange.setVisible(false);
                        if(myPanel.isVisible())
                        {
                            //Get User Input for both Money and Cost
                            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                                "Get Change", JOptionPane.OK_CANCEL_OPTION);
                            if (result == JOptionPane.OK_OPTION) 
                            {
                                double money = Double.parseDouble(xField.getText()) * 100;
                                double cost = Double.parseDouble(yField.getText()) * 100;
                                int change = (int)(money - cost);
                                if(change < 1)                              
                                    System.out.println("You do not have enough money to make this purchase");
                                else if(change == 0)
                                {
                                    System.out.println("You have the exact total and will not receive change for this purchase.");
                                }
                                else
                                {
                                    //Insert Algorithms or something
                                    myPanel.add(new JLabel("Your change is :" + change));
                                }

                            }
                        }
                    }
                
                });
                insideChange.add(getChange, BorderLayout.CENTER);
                //----------------------------------------------------------------------
                //Create Panel to hold button for displaying bar graph to user
                JPanel panel = new JPanel();
                panel.setVisible(true);         //Show the panel to the user at beginning of execution
                //----------------------------------------------------------------------
                //Create Button for showing and hiding algorithm statistic chart
                JButton chartButton = new JButton("See Statistics Chart");
                panel.add(chartButton);
                chartButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        //Show bar chart
                        chart.setVisible(true);
                        //Hide button for showing chart cause its already being shown
                        chartButton.setVisible(false);
                        //Make a button to return to previous window when done with viewing graph
                        JButton goBack = new JButton("Return");
                        //Show button at top of frame when viewing graph
                        goBack.setVisible(true);
                        //Make a panel to place return button on
                        JPanel addBack = new JPanel();
                        //Show panel when viewing bar graph
                        addBack.setVisible(true);
                        addBack.add(goBack);
                        //Hide button for making change when viewing bar graph
                        getChange.setVisible(false);
                        //Add panel to main window frame
                        frame.add(addBack);
                        //Listen for when the return button is clicked by user
                        goBack.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent r)
                            {
                                chart.setVisible(false);        //Hide bar graph when returning to main screen
                                chartButton.setVisible(true);   //Show Button again for getting statistics screen
                                goBack.setVisible(false);       //Hide the return button as we have already returned
                                getChange.setVisible(true);     //Show the change making button again
                            }
                        });//end of return button listener
                    }//End of showing statistics graph
                });//end of chartButton actionListener

                //Add bar graph to main window frame
                frame.add(chart);
                //Add button panel for change making to an internal windown to move it to center
                insideChange.add(myPanel, BorderLayout.WEST);
                //Add window for making change to main window frame
                frame.add(insideChange);
                //Add panel containing statistics button to main window frame
                frame.add(panel);
                frame.repaint();   
        
            });
        }
        catch(InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException e)
        {
            System.out.println("You have not entered a numerical value and therefore cannot make change");
        }
    }
}

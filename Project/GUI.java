import java.lang.reflect.InvocationTargetException;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

/**
 * public   :   GUI
 *  This class implements and handles all graphic
 *  visualization of the data for the change-making problem.
 *  It creates a graphic user interface in which the user
 *  can view the statisitcal graph data in the form of a 
 *  bar graph. The interface allows allows for using 
 *  the algorithms by providing a change-making option,
 *  in which the user can insert how much money they have
 *  and how much money something costs. The method
 *  will then determine how much change to give to the user
 *  based on all three algorithms used for this project and
 *  then show the user how much of each type of coin will
 *  be given back to them as change. If the cost given exceeds
 *  that of the amount of money the person has, it will
 *  tell the user it cannot make change and then ask for the
 *  user to retype their amounts. The user can switch back
 *  and forth between making change and viewing the graph as
 *  much as they like and the program will continue to run as
 *  long as the user has not hit the exit button at the top
 *  right corner.
 */
public class GUI
{
    //Attributes
    int[] timeCases;        //Time complexities of all three algorithms
    int[] bestCases;        //Accuray of each algorithm in getting the least amount of change

    //Set containing American currency values * 100 to take into account cents-ranges from $.01 - $100
    int[] AmericanCurrency = {1,5,10,25,100,500,1000,2000,5000,10000};
    //How many of each coin/dollar is needed to give back as change
    int[] allChange;

    /**
     * public   :  GUI
     *  Contstructor used to set and initialize all data that 
     *  is to be used when creating the bar graph.
     * 
     * Parameters:
     *      int[]   : array containing all time complexities
     *      int[]   : array containing all space complexities
     *      int[]   : array containing all accuracy percentages
     * Returns:
     *      Void
     */
    public GUI(int[] time, int[] bestCase)
    {
        timeCases = time;     //Store time complexities
        bestCases = bestCase; //Store how accurate each algorithm was
    }

    /**
     * public   :   getGUI
     *  This method implements and handles all graphic
     *  visualization of the data using java swing and JFreeChart
     *  It creates a graphic user interface in which the user
     *  can view the statisitcal graph data in the form of a 
     *  bar graph. The interface allows allows for using 
     *  the algorithms by providing a change-making option,
     *  in which the user can insert how much money they have
     *  and how much money something costs. The method
     *  will then determine how much change to give to the user
     *  based on all three algorithms used for this project and
     *  then show the user how much of each type of coin will
     *  be given back to them as change. If the cost given exceeds
     *  that of the amount of money the person has, it will
     *  tell the user it cannot make change and then ask for the
     *  user to retype their amounts. The user can switch back
     *  and forth between making change and viewing the graph as
     *  much as they like and the program will continue to run as
     *  long as the user has not hit the exit button at the top
     *  right corner. 
    * Parameters:
    *      None
    * Returns:
    *      Void
    */
    public void getGUI() throws InvocationTargetException, InterruptedException, NumberFormatException
    {
        //Show graphics unless an exception gets thrown
        try
        {
            SwingUtilities.invokeAndWait(()->   
            { 
                //Create Main Frame Window for graphics
                JFrame frame = new JFrame();
                frame.setSize(1000,600);            //Is a 1000 x 600 frame
                frame.setLocationRelativeTo(null);  
                //Close program when hitting exit
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
                //Show frame to the user
                frame.setVisible(true);  
                //-----------------------------------------------------------------------

                //Create Bar chart Showing tested data for complexities
                BarChart chart = new BarChart("Variables", timeCases, bestCases);           
                chart.pack();
                chart.setLocation(20, 40);  //Place graph in middle of main frame
                chart.setSize(950, 500);    //Make graph slightly smaller then the main frame
                chart.setTitle("Algorithm Comparison Chart");
                //-----------------------------------------------------------------------

                //Create Internal Frame to hold User Input for Making Change
                JInternalFrame insideChange = new JInternalFrame();
                insideChange.setLocation(250, 100);
                insideChange.setSize(500, 250);
                insideChange.setVisible(true);
                //----------------------------------------------------------------------
                
                //Create Text Field to Get user Input for Making Change
                JTextField xField = new JTextField(5);  //Text for getting how much money person has
                JTextField yField = new JTextField(5);  //Text for how much money item costs
                JPanel myPanel = new JPanel();          //Create panel to add text fields to
                myPanel.setVisible(false);              //Don't show panel unless specifically told to 
                //Prompt user to fill in text boxes to make change in the panel
                myPanel.add(new JLabel("How Much Money do you Have?"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("How Much Money does it Cost?"));
                myPanel.add(yField);
                //---------------------------------------------------------------------

                //Create Output console to show the user their results of change
                Console console = new Console();
                console.hideFrame(); 
                //---------------------------------------------------------------------

                //Create Button to Activate Making Change
                JButton getChange = new JButton("ATM Change Counter");

                //Listen for when button is clicked to make change
                getChange.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent r) //When button is clicked do this:
                    {
                        //Show Prompt for inputing data to make change
                        myPanel.setVisible(true);
                        //Hide button for making change as we are currently doing that
                        getChange.setVisible(false);

                        //We are being prompted to get change
                        if(myPanel.isVisible())
                        {
                            //We want to keep prompting until we have received proper input
                            boolean go = true;
                            //Keep asking to make change until successfully done so or user leaves prompt willingly
                            while(go)
                            {
                                //Get User Input for both Money and Cost with a confirmation button
                                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                                    "Get Change", JOptionPane.OK_CANCEL_OPTION);

                                //The user wants to make change with the values given
                                if (result == JOptionPane.OK_OPTION) 
                                {
                                    //Remove anything currently in the console
                                    console.erase();

                                    //Mulitply money by 100 to include coins
                                    double money = Double.parseDouble(xField.getText()) * 100;
                                    double cost = Double.parseDouble(yField.getText()) * 100;
                                    //change is to represented as an integer
                                    int change = (int)(money - cost);

                                    //Cannot make negative change
                                    if(change < 0)                              
                                        System.out.println("You do not have enough money to make this purchase");
                                        
                                    //No need to make change as exact amount is given
                                    else if(change == 0)
                                    {
                                        System.out.println("You have the exact total and will not receive change.");
                                        //Stop prompting
                                        go = false;
                                        //Listen for when user closes out of console
                                        console.frame.addWindowListener(new java.awt.event.WindowAdapter() 
                                        {
                                            //User has closed console
                                            public void windowClosing(java.awt.event.WindowEvent e) 
                                            {
                                                //Hide the console from the user
                                                console.hideFrame();
                                                //Show the button for wanting to make change
                                                getChange.setVisible(true);
                                                //Hide panel for prompting the user
                                                myPanel.setVisible(false);
                                            }
                                        });
                                    }
                                    //We are able to make change and will do so
                                    else
                                    { 
                                        //Shrink font size to allow for all change to fit screen
                                        console.changeFontSize(18);
                                        //Get array containing how much of each coin is need to make change
                                        allChange = changeAmounts(change);
                                        //Show all change to the user to know how muc of each coin to take
                                        System.out.printf("Your change is: $ %.2f\n", (float)(change) / 100);
                                        System.out.println("You will receive these coins:");
                                        System.out.println("$  0.01: " + allChange[0]);
                                        System.out.println("$  0.05: " + allChange[1]);
                                        System.out.println("$  0.10: " + allChange[2]);
                                        System.out.println("$  0.25: " + allChange[3]);
                                        System.out.println("$  1.00: " + allChange[4]);
                                        System.out.println("$  5.00: " + allChange[5]);
                                        System.out.println("$ 10.00: " + allChange[6]);
                                        System.out.println("$ 20.00: " + allChange[7]);
                                        System.out.println("$ 50.00: " + allChange[8]);
                                        System.out.println("$100.00: " + allChange[9]);

                                        //Stop prompting
                                        go = false;
                                        //Listen for when user closes out of console
                                        console.frame.addWindowListener(new java.awt.event.WindowAdapter() 
                                        {
                                            //User has closed console
                                            public void windowClosing(java.awt.event.WindowEvent e) 
                                            {
                                                //Hide the console from the user
                                                console.hideFrame();
                                                //Show the button for wanting to make change
                                                getChange.setVisible(true);
                                                //Hide panel for prompting the user
                                                myPanel.setVisible(false);
                                            }
                                        });
                                        
                                    }

                                    //Set where the frame should be displayed to the user
                                    console.frame.setLocation(215,240);
                                    //Get the frame to be shown
                                    console.getFrame();
                                    //Show the console's contents to the user
                                    console.showFrame();                                    
                                }

                                //User did not confirm wanting to make change, either hit exit or cancel
                                else
                                {
                                    //Hide the console from the user
                                    console.hideFrame();
                                    //Display button for wanting to make change again
                                    getChange.setVisible(true);
                                    //Hide panel for prompting user input
                                    myPanel.setVisible(false);
                                    //We no longer want to keep prompting
                                    go = false;
                                }
                            }
                        }
                    }
                
                }); //Done with the making change portion of the GUI

                //Add button for making change to internal frame and make button fill entire frame
                insideChange.add(getChange, BorderLayout.CENTER);
                //----------------------------------------------------------------------

                //Create Panel to hold button for displaying bar graph to user
                JPanel panel = new JPanel();
                panel.setVisible(true);         //Show the panel to the user at beginning of execution
                //----------------------------------------------------------------------

                //Create Button for showing and hiding algorithm statistic chart
                JButton chartButton = new JButton("See Statistics Chart");
                //Add button to panel at top of main frame
                panel.add(chartButton); 

                //Listen for when chartButton has been clicked by user
                chartButton.addActionListener(new ActionListener()
                {
                    //Chart button has been clicked by user
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        //Show bar chart
                        chart.setVisible(true);
                        //Hide button for showing chart cause its already being shown
                        chartButton.setVisible(false);
                        //Make a button to return to previous window when done viewing graph
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
                });//Done with viewing bar graph

                //Add bar graph to main window frame
                frame.add(chart);
                //Add button panel for change making to an internal window to move it to center
                insideChange.add(myPanel, BorderLayout.WEST);
                //Add window for making change to main window frame
                frame.add(insideChange);
                //Add panel containing statistics button to main window frame
                frame.add(panel);
                frame.repaint();   
        
            });//Done showing GUI
        }

        //If there was an error when trying to show the GUI catch it and leave program
        catch(InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        //The user did not enter numerical values to make change with
        catch(NumberFormatException e)
        {
            System.out.println("You have not entered a numerical value and therefore cannot make change");
        }
    }//end of getGUI()

    /**
     * private   :   changeAmounts
     *  This method gets the array with containing the fewest
     *  amount of coins needed to make change. It does this by
     *  running all three algorithms and then who ever has the 
     *  best case where the total number of values was the
     *  fewest between the three, that algorithms array
     *  will be return to be used as the best case array
     *  of coin amounts. This method is only called within
     *  the GUI as it is not used until being shown for
     *  making change
    * Parameters:
    *      int  :   desired total change the user needs
    * Returns:
    *      int[]    :   how much of each type of coin the user will receive in change
    */
    private int[] changeAmounts(int total)
    {
        //Create and run the greedy algorithm
        Greedy greed = new Greedy(AmericanCurrency, total);
        //Create and run the greedy algorithm that also seeks out numbers with remainders
        NumWithRemainder remain = new NumWithRemainder(AmericanCurrency, total);
        NumWithMultiple multiple = new NumWithMultiple(AmericanCurrency, total);

        if(greed.BestCase() <= multiple.BestCase())
        {
            //Return coin amounts
            return greed.getChange();
        }
        else if (multiple.BestCase() <= remain.BestCase())
        {
            return multiple.getChange();
        }
        else
        {
            //Return coin amounts
            return remain.getChange();
        }
    }
}


/**
 * public   :  Console
 *  This class implements and acts like the console 
 *  for the outputs for the user in the GUI. When the
 *  user wishes to make change inside the interface, 
 *  the results of the change needed will be printed
 *  out to the user in a console that is represented
 *  as a java frame. This is to visually show the results
 *  to the user in a text frame. The console can have its
 *  colors, font, and font size be changed when desired.
 */
class Console 
{
    final JFrame frame = new JFrame();  //frame holding results of change-making
    JTextArea textArea;                 //Text area containting results of change

    /**
     * public   :  Console
     *  Contstructor used to create a text field that shows
     *  the outputs of the console to the user in the form
     *  of a java frame
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public Console()
    {
        //Make text box to write information to
        textArea = createTextArea();
        //Add text box to the JFrame
        frame.add(textArea);
        frame.pack();
        //Automatically show the console when implemented
        frame.setVisible(true);

        redirectOut();
    }

    /**
     * private   :  createTextArea
     *  Method to handle creating a JTextArea for
     *  showing the console's outputs. This method 
     *  sets the color of the console as well as its
     *  font and font size. This method is intended to
     *  only be called within the console class.
     * 
     * Parameters:
     *      None
     * Returns:
     *      JTextArea   :   text box showing contents of the console
     */
    private JTextArea createTextArea()
    {
        //Text box whould be wider than it is taller. Is a 35 x 7 text box
        textArea = new JTextArea(7, 35);
        //Text background is white
        textArea.setBackground(Color.WHITE);
        //Color of text is black
        textArea.setForeground(Color.BLACK);
        //Text font is sans serif bold with 36 point size
        textArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        //Do not allow text to be edited when shown
        textArea.setEditable(false);

        return textArea;
    }

    /**
     * public   :  redirectOut
     *  Method meant to override the system.out.print
     *  function in order to redirect the output from
     *  appearing in the compiler's console and inserts
     *  all output into a created console made from
     *  a frame and text field. All output is shown
     *  in a window to the user rather than having to seek
     *  it out in a standard terminal.
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public PrintStream redirectOut() 
    {
        //Create outputstream to hold all output found in GUI
        OutputStream out = new OutputStream()
        {
            //Add all output to the text area within the JFrame
            @Override
            public void write(int b) throws IOException
            {
                textArea.append(String.valueOf((char) b));
            }
        };

        //Override the print stream to go to text field rather than console
        PrintStream ps = new PrintStream(out);

        System.setOut(ps);
        System.setErr(ps);

        return ps;
    }

    /**
     * public   :   changeFontSize
     *  This method changes the size of the font in the 
     *  text box to a given size.
     * 
     * Parameters:
     *      int   : size of how big the font needs to be
     * Returns:
     *      Void
     */
    public void changeFontSize(int size)
    {
        //Change the size of the font without changing anything else
        textArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, size));   
    }

    /**
     * public   :  erase
     *  This method deletes the text frame and replaces it in order
     *  to show a blank console that does not contain any previous
     *  output from a prior change-making input.
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public void erase()
    {
        //Remove old text area from frame as we no longer want it
        frame.remove(textArea);
        //Create new text area with nothing in it and add it to the frame
        textArea = createTextArea();
        frame.add(textArea);
    }

    /**
     * public   :  hideFrame
     *  hides the frame displaying the console by setting it
     *  to become invisible.
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public void hideFrame()
    {
        //Hide console from user
        frame.setVisible(false);
    }

    /**
     * public   :  showFrame
     *  shows the frame displaying the console by setting it
     *  to become visible.
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public void showFrame()
    {
        //Show console to the user
        frame.setVisible(true);
    }

    /**
     * public   :  getFrame
     *  Getter method for getting the frame
     *  that's holding the console
     * 
     * 
     * Parameters:
     *      None
     * Returns:
     *      Void
     */
    public JFrame getFrame() 
    {
        //Get window frame containing the console's text box
        return frame;
    }
}
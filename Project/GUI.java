import java.lang.reflect.InvocationTargetException;
import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class GUI
{
    int[] timeCases;
    int[] spaceCases;
    int[] bestCases;
    int[] AmericanCurrency = {1,5,10,25,100,500,1000,2000,5000,10000};
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
                Console console = new Console();
                console.hideFrame();

                //Create Button to Activate Making Change
                JButton getChange = new JButton("ATM Change Counter");                //Listen for when button is clicked to make change
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
                            boolean go = true;
                            while(go)
                            {
                                //Get User Input for both Money and Cost
                                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                                    "Get Change", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) 
                                {
                                    console.erase();
                                    double money = Double.parseDouble(xField.getText()) * 100;
                                    double cost = Double.parseDouble(yField.getText()) * 100;
                                    int change = (int)(money - cost);
                                    if(change < 1)                              
                                        System.out.println("You do not have enough money to make this purchase");
                                        
                                    else if(change == 0)
                                    {
                                        System.out.println("You have the exact total and will not receive change for this purchase.");
                                        break;
                                    }
                                    else
                                    { 
                                        System.out.printf("Your change is: $ %.2f\n", (float)(change) / 100);
                                        System.out.println("You will receive these coins:");
                                        System.out.print("Penny:\nNickel:\nDime:\nQuarter:\n$1:\n$5:\n$10:\n$20:\n$50:\n$100:");
                                        go = false;
                                        console.frame.addWindowListener(new java.awt.event.WindowAdapter() {
                                            public void windowClosing(java.awt.event.WindowEvent e) {
                                                console.hideFrame();
                                                getChange.setVisible(true);
                                                myPanel.setVisible(false);
                                            }
                                        });
                                        
                                    }
                                    console.frame.setLocation(215,240);
                                    console.getFrame();
                                    console.showFrame();                                    
                                }
                                else
                                {
                                    console.hideFrame();
                                    getChange.setVisible(true);
                                    myPanel.setVisible(false);
                                    go = false;
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
        catch(InvocationTargetException e){}
        catch(InterruptedException e){}
        catch(NumberFormatException e)
        {
            System.out.println("You have not entered a numerical value and therefore cannot make change");
        }
    }
}



class Console 
{
    final JFrame frame = new JFrame();
    JTextArea textArea;

    public Console()
    {
        textArea = createTextArea();
        frame.add(textArea);
        frame.pack();
        frame.setVisible(true);

        redirectOut();
    }
    private JTextArea createTextArea()
    {
        textArea = new JTextArea(5, 50);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 36));
        textArea.setEditable(false);
        return textArea;
    }
    public PrintStream redirectOut() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        };
        PrintStream ps = new PrintStream(out);

        System.setOut(ps);
        System.setErr(ps);

        return ps;
    }

    public void erase()
    {
        frame.remove(textArea);
        textArea = createTextArea();
        frame.add(textArea);
    }
    public void hideFrame()
    {
        frame.setVisible(false);
    }
    public void showFrame()
    {
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
}

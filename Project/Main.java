import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * public   :   Main
 *  This class is the driver class intended
 *  to run and implement the three algorithms
 *  used to determine how effective they are
 *  when it comes to getting the least amount of 
 *  change for a given target value. After collecting
 *  all of the data for each algorithm's complexities,
 *  they are then sent to a GUI class intended to 
 *  display the contents of the project's results to the
 *  user. For both the time and accuracy complexities, 
 *  when given a test of test cases, the two data sets
 *  display the overall sum of all test case complexities.
 *  The time complexity is recorded using nanoTime and then
 *  divided in order to obtain a number that is close
 *  in value to the other complexities results when 
 *  viewed in the bar graph.
 */
public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException
    {
        //Create objects for all three algorithms
        NumWithRemainder RemainderAlgo;
        NumWithMultiple MultipleAlgo;
        Greedy greedyAlgo;

        //Create data sets of size 3 to store each algorithms data
        int[] AlgoCases = {0, 0, 0};    //Store accuracy of each algorithm
        int[] TimeCases = {0, 0, 0};    //Store time complexity of each algorithm

        try
        {
            //Open file containing all test cases for change-making
            File testCases = new File("testcases.txt");
            //Scan file to get each test case per line
            Scanner scan = new Scanner(testCases);
            //Scanner to read each number within line
            Scanner fullSet;
            //Current test case being tested
            String test;
            int[] currentSet = new int[0];

            while(scan.hasNextInt())
            {
                //Get line of data for current change set
                test = scan.nextLine();
                //Scan set to store values into array
                fullSet = new Scanner(test);
                //Length of array is # of white spaces + 1
                int size = test.replaceAll("[^ ]", "").length();
                //Initialize size of integer array for the set
                currentSet = new int[size];
                
                //Store all values in line into integer array
                for(int i = 0; i < size; i++)
                {
                    //Add value into current set
                    currentSet[i] = fullSet.nextInt();
                }
                //Last value given is the target value for the set
                int targetVal = fullSet.nextInt();

                //long SpaceR = Instrumentation.getObjectSize();

                //Record how long it takes to find all coins for standard greedy
                long startTime = System.nanoTime();
                greedyAlgo = new Greedy(currentSet, targetVal); //Get how many coins it took
                long endTime = System.nanoTime();
                long durationG = (endTime - startTime);

                //Record how long it takes to find all coins while also checking for multiples
                startTime = System.nanoTime();
                MultipleAlgo = new NumWithMultiple(currentSet, targetVal);    //Get how many coins it took
                endTime = System.nanoTime();
                long durationM = (endTime - startTime);

                //Record how long it takes to find all coins while also checking for remainders
                startTime = System.nanoTime();
                RemainderAlgo = new NumWithRemainder(currentSet, targetVal);    //Get how many coins it took
                endTime = System.nanoTime();
                long durationR = (endTime - startTime);

              
                //Add the amount of coins needed for set to accuracy counter
                AlgoCases[2] += RemainderAlgo.BestCase();
                AlgoCases[1] += MultipleAlgo.BestCase();
                AlgoCases[0] += greedyAlgo.BestCase();

                //Add time it took for each algorithm to execute to time complexity counters
                TimeCases[2] += durationR;
                TimeCases[1] += durationM;
                TimeCases[0] += durationG;
                        
            }

            //No longer reading in sets of data
            scan.close();

            //Create GUI and display results
            GUI display = new GUI(TimeCases, AlgoCases);
            display.getGUI();

             
        }  

        //If an error occurs, show want happened and exit program
        catch(InterruptedException e)
        {
            //Program got interrupted
            e.printStackTrace();
        }
        catch(InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            //The files containing the test cases is missing
            System.out.println("Cannot find the file you are looking for");
            e.printStackTrace();
        }
        
    }
}
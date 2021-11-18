import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException, FileNotFoundException
    {
        NumWithRemainder RemainderAlgo;
        Greedy greedyAlgo;
        int[] AlgoCases = {0, 0, 0};
        int[] TimeCases = {0, 0, 0};

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
                    currentSet[i] = fullSet.nextInt();
                }
                int targetVal = fullSet.nextInt();
                RemainderAlgo = new NumWithRemainder(currentSet, targetVal);
                greedyAlgo = new Greedy(currentSet, targetVal);

                //long SpaceR = Instrumentation.getObjectSize();
                long startTime = System.nanoTime();
                AlgoCases[2] += RemainderAlgo.BestCase();
                long endTime = System.nanoTime();
                long durationR = (endTime - startTime) / 100;  //divide by 1000000 to get milliseconds.

                startTime = System.nanoTime();
                AlgoCases[0] += greedyAlgo.BestCase();
                endTime = System.nanoTime();
                long durationG = (endTime - startTime) / 100;


                TimeCases[2] += durationR;
                TimeCases[0] += durationG;
            }
            scan.close();

            //Create GUI
            GUI display = new GUI(TimeCases, new int[]{1,2,3}, AlgoCases);
            display.getGUI();

             
        }  
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        catch(InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Cannot find the file you are looking for");
            e.printStackTrace();
        }
        
    }
}

/**
 * public   :   NumWithMultiple
 * 
 * This class will implement the existing/standard 
 * greedy algorithm data structurebut will also consider
 * the largest multiple of the target value in the set
 * (if a multiple exists). The algorithm will start 
 * with the largets value in the set, as in the 
 * standard greedy algorithm, but will check if the
 * value is an exact mutliple of the target value. If
 * it is then it will store this value and continue with
 * the standard greedy algorithm until the end. Once
 * the search is over the algorithm will then compare 
 */
public class NumWithMultiple 
{
    // Attributes
    private int[] setValues;        //Values in the given set
    private int targetValue;        //Target value to reach
    private int bestSize;           //Number of values it took to reach the target
    private int[]change;            //Which coins it took to reach the target

    /**
     * public   :  NumWithMultiple
     * 
     * Constructor to initialize given class with the 
     * set values and the target values. Then call 
     * method useMultiple which will take the given 
     * values and determine the best case (case with
     * the least amount of change to reach the target) 
     * based on the with multiple extension to the greedy 
     * algorithm
     * 
     * Parameters:
     *      int[]   : Array with values used to reach the target
     *      int     : Number of coins/change it took to reach the target
     * Returns:
     *      Void
     */
    NumWithMultiple(int[] mySet, int myTarget)
    {
        setValues = mySet;
        targetValue = myTarget;
        bestSize = useMultiple(setValues, targetValue);
    }

    
    /**
     * private   :   useMultiple
     * 
     * This method extend the standard greedy algorithm by
     * checking to see if there is an exact multiple of the
     * target in the set. It will do this by traversing the 
     * set as the traditional greedy algorithm would while 
     * also checking to see if the current value is an
     * exact multiple of the target value. Since the 
     * traversal is done from the greatest value the the least
     * value once an exact multiple is found it is considered 
     * the greatest and the algorith will no longer look for 
     * multiples and will continue with the standard greedy 
     * algorithm. Once the traversal is complete (indicted by
     * the current target value becoming 0) the algorithm will
     * compare the amount of coins taken to reach the taget 
     * based on the multiple and based on the standard greedy
     * and will return the one that is the least.
     * 
     * Parameters:
     *      int[]   : Array with values used to reach the target
     *      int     : Number of coins/change it took to reach the target
     * Returns:
     *      int     : The number of coins/change it took to reach the target
     */
    private int useMultiple(int[] set, int target)
    {
        // array of the same size of set to hold the
        // amount of each value to reach the target
        change = new int[set.length];
        // total number of values to reach target overall
        int totalCounter = 0;
        // the total number of values to reach target 
        // if multiple is found
        int multipleCounter = target;
        // the amount left to reach target with valuse
        int currentTarget = target;
        // used to check if a value is an exact multiple
        int remainder = -1;
        // size of the set
        int size = set.length;
        // flag to stop checking when a multiple has been
        // found
        boolean multipleFound = false;
        // traverse the set starting from the largest value
        for(int i = size - 1; i >= 0; --i)
        {
            // if value in the set is less than the target
            if(set[i] <= currentTarget)
            {
                // add how many times the value can be used to reach the targt
                totalCounter += currentTarget / set[i];
                // update current target with how much left to get
                currentTarget %= set[i];
            }
            // if a multiple has not yet been found
            if(set[i] <= target && !multipleFound)
            {
                remainder = target % set[i];
                // check if the value is an exact multiple of target value
                if (remainder == 0){
                    // update change array to the amount of values of the 
                    // exact multiple it takes to reach target
                    change[i] = target / set[i];
                    // the amount of values it take to reach target will be 
                    // target / value
                    multipleCounter = target / set[i];
                    // multiple has been found so set flag to true
                    multipleFound = true;
                }
            }
            // target has been reach so stop traversal
            if(currentTarget == 0) 
            {
                break;
            }
            
        }

        // if standard gready is less than multiple then return 
        // total amount of coins it took to reach target based on 
        // the standard greedy
        if(totalCounter < multipleCounter)
        {
            return totalCounter;
        }
        // if multple counter is less than or equal to total counter
        // retun the amount of values it took to reach targer based of 
        // the multiple finder extension
            return multipleCounter;
    
    }

    /**
     * public   :  BestCase
     * 
     * This method will return the amount of coins/change
     * that was needed to reach the target value/total
     * after implementing the greedy algorithm with the 
     * multiple finder extension 
     * 
     * Parameters:
     *      None
     * Returns:
     *      int     : The total number of coins/change it took to reach the target
     */
    public int BestCase()
    {
        return bestSize;
    }

    /**
     * public   :  getChange
     * 
     * This method is a getter function that will return the 
     * array that represent the amount of each coin/change value
     * that it took to reach the target value after implementing
     * the greedy algorithm with multiple finder extension
     * 
     * Parameters:
     *      None
     * Returns:
     *      int[]     : How many times each set value was used to reach the target
     */
    public int[] getChange()
    {
        return change;
    }
}
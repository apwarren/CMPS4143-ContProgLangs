/**
 * public   :   Greedy
 *  This class a standard greedy algorithm data structure
 *  for the change-making problem in which the algorithm
 *  will start with the highest value and works its way
 *  down the set of values until it the change reaches
 *  its target value. This algorithm is provided a
 *  set of values and a target value where it will
 *  traverse the set and count how many values it
 *  takes to reach the target where all values can
 *  be counted as many times as needed. All sets will
 *  be expected to have 1 as the first element within it
 *  to ensure that the set can reach the target value at the
 *  end. The set will be represented in the form of an integer
 *  array that is in ascending order. The class can determine
 *  how many values it will take to reach the target value as
 *  well as what those values are. The minimum count is stored
 *  to be given when desired and the values counted are stored
 *  in an integer array in which the location of a value in the
 *  set will be the location of the number of times that value
 *  is needed to reach the target.
 */
public class Greedy 
{
    //Attributes
    private int[] setValues;    //Values that can be used to reach target
    private int targetValue;    //Value for set to reach
    private int bestCase;       //Number of overall values it took to reach target
    private int[] change;       //Number of times each value was used to reach target

    /**
     * public   :  Greedy
     *  Contstructor used to set all given values to the class.
     *  Sets the set to be traversed, the target value, and then
     *  calls the method to implement the greedy algorithm in order
     *  to determine the best case that the algorithm can find the 
     *  least amount of number to reach the target as well as 
     *  how many of each number it took to reach the target.
     * 
     * Parameters:
     *      int[]   : array containing all values that can be used to reach target
     *      int     : Number to reach using values in the int array
     * Returns:
     *      Void
     */
    Greedy(int[] mySet, int myTarget)
    {
        setValues = mySet;          //Initialize set of numbers that can be used
        targetValue = myTarget;     //Initialize what value we are trying to get to

        //Call method to implement the greedy algorithm and find the change needed
        bestCase = getGreedy(setValues, targetValue);
    }

    /**
     * private   :  getGreedy
     * This method implements a standard greedy algorithm in which
     * the method will start with the highest number in the given set
     * and then work its way down until all values it has counted add
     * up to be equivalent to the desired target value. The method
     * keeps track of how many numbers overall it will take to reach
     * the target as well as stores an array containing how many times
     * a particular value in the set was used to reach the target as well.
     * As each time a value is used to reduce the target value, a new target
     * containing the remaining value left find will become the new target
     * until this current desired value reaches 0. Once the current target
     * is zero, this means that we have reach our overall target and can leave
     * the method. This is to prevent have to continue to traverse the set array
     * more than we have to.
     * 
     * Parameters:
     *      int[]   : array containing all values that can be used to reach target
     *      int     : Number to reach using values in the int array
     * Returns:
     *      int     : Number of values overall it took to reach the target value
     */
    private int getGreedy(int[] set, int target)
    {
        //create array the same size as the set 
        //  to store the individual counts of all values
        change = new int[set.length];
        //Number of values overall that have been counted to get to target
        int totalCounter = 0;
        //Remaining amount left of target to still reach with values
        int currentTarget = target;
        //size of the set to traverse through
        int size = set.length;

        //Traverse through the entire array starting at the last index
        for(int i = size - 1; i >= 0; --i)
        {   
            //Value in set can be used to reduce the current target
            if(set[i] <= currentTarget)  
            {
                change[i] = currentTarget / set[i]; //How many times value can reduce current target
                totalCounter += change[i];          //Add number of times value was used to total counter
                currentTarget %= set[i];            //Current target is now the remaining amount left
            }
            
            //We have reached the overall target
            if(currentTarget == 0)
            {
                break;  // No longer need to keep looking as we are done
            }
        }

        //Return total amount of values it took to reach target
        return totalCounter;
    
    }

    /**
     * public   :  BestCase
     * This method is a getter function to obtain the how many
     * numbers was needed to reach the desired total after implementing
     * the greedy algorithm. 
     * 
     * Parameters:
     *      None
     * Returns:
     *      int     : Number of values overall it took to reach the target value
     */
    public int BestCase()
    {
        return bestCase;
    }
    
    /**
     * public   :  getChange
     * This method is a getter function meant to retrieve the
     * array containing the individual amount of times each value
     * was used in order to reach the target after implementing 
     * the greedy algorithm. 
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

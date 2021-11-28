/**
 * public   :   NumWithRemainder
 *  This class acts as a standard greedy algorithm data structure
 *  that also considers values that can also contain a remainder
 *  within the set in order to find the fewese amount of values
 *  needed to reach the target value. As the algorithm traverses 
 *  like a standard greedy algorithm by starting with the
 *  highest value, it will also check each value that it
 *  comes across and sees if that particular value has
 *  its respective remainder within the set as well
 *  in order to jump out of the algorithm early. The 
 *  method will continue to search for a remainder 
 *  until it has either found one or the standard 
 *  greedy has already finished finding a solution.
 *  Each time a remainder is to be searched for 
 *  within the class, it will use a binary
 *  search as values are automatically considered
 *  to be sorted in order to reduce the time complexity
 *  as much as possible and not stray too far from
 *  the a regular greedy algorithm. The class then
 *  stores how many values it took to reach the target
 *  as well as an array containing how much of a 
 *  particular value was needed to reach the target as well.
 */
public class NumWithRemainder
{
    //Attributes
    private int[] setValues;    //Values that can be used to reach target
    private int targetValue;    //Value for set to reach
    private int bestCase;       //Number of overall values it took to reach target
    private int[] change;       //Number of times each value was used to reach target

    /**
     * public   :  NumWithRemainder
     *  Contstructor used to set all given values to the class.
     *  Sets the set to be traversed, the target value, and then
     *  calls the method to implement the greedy with remainder algorithm in order
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
    NumWithRemainder(int[] mySet, int myTarget)
    {
        setValues = mySet;          //Initialize set of numbers that can be used
        targetValue = myTarget;     //Initialize what value we are trying to get to

        //Call method to implement the greedy with remainder algorithm
        bestCase = useRemainder(setValues, targetValue);
    }

    /**
     * private   :   useRemainder
     *  This method performs a standard greedy algorithm data structure
     *  that also considers values that can also contain a remainder
     *  within the set in order to find the fewese amount of values
     *  needed to reach the target value. As the algorithm traverses 
     *  like a standard greedy algorithm by starting with the
     *  highest value, it will also check each value that it
     *  comes across to sees if that particular value has
     *  its remainder within the set as well
     *  in order to jump out of the algorithm early. The 
     *  method will continue to search for a remainder 
     *  until it has either found one or the standard 
     *  greedy has already finished finding a solution.
     *  Each time a remainder is to be searched for 
     *  within the function, it will use a binary
     *  search as values are automatically considered
     *  to be sorted in order to reduce the time complexity
     *  as much as possible and not stray too far from
     *  the a regular greedy algorithm. The method will
     *  then return how many values it took to reach
     *  the target.
     * 
     * Parameters:
     *      int[]   : array containing all values that can be used to reach target
     *      int     : Number to reach using values in the int array
     * Returns:
     *      int     : Number of values overall it took to reach the target value
     */
    private int useRemainder(int[] set, int target)
    {
        //create array the same size as the set 
        //  to store the individual counts of all values
        change = new int[set.length];
        //Number of values overall that have been counted to get to target
        int totalCounter = 0;
        //Number of values it took when finding a remainder in the set
        //Value starts at highest value considered which is the target to
        //avoid it being too low and preventing method from considering
        //the standard greedy algorithm
        int remainderCounter = target;
        //Remaining amount left of target to still reach with values
        int currentTarget = target;
        //How much left of a current value remains to be searched for in the set
        int remainder = -1;
        //size of the set to traverse through
        int size = set.length;
        //Flag to know to stop looking for remainders once its been found
        boolean remainderFound =false;

        //Traverse throught the entire set starting at the last index
        for(int i = size - 1; i >= 0; --i)
        {
            //the current set value is lower or the same as the rest of the target needed
            if(set[i] <= currentTarget)
            {
                //Add how many times number can be used to reduce current target
                totalCounter += currentTarget / set[i];
                //Get how much is left to reach the main target value
                currentTarget %= set[i];
            }

            //If we still have not found a remainder within the set
            if(set[i] <= target && !remainderFound)
            {
                //See how much is left once reducing the target with the current value as much as possible
                remainder = target % set[i];
                //Use a binary search to look for remainder
                if(RemainderSearch(remainder) || remainder == 0) //The remainder is also in list or value is a perfect multiple
                {  
                    //Value was a perfect multiple
                    if(remainder == 0)
                    {
                        //Add how many times that number needed to be used to reach target to respective index
                        change[i] = target / set[i];
                        //Add how many times the value took to reach the target overall
                        remainderCounter = target / set[i];
                    }

                    //Value was not a perfect multiple but it did have a remainder
                    else
                    {
                        //Add how many times current value is used to reach target
                        change[i] = target / set[i];
                         //Leave method/ remainder is found so one extra number for overall count
                        remainderCounter = ((target / set[i]) + 1);
                    }

                    //We have found a remainder
                    remainderFound = true;
                }
            }

            //We have finished the standard greedy algorithm or its more values then the current remainder amount
            if(currentTarget == 0 || remainderCounter < totalCounter) 
            {
                //Leave loop traversal
                break;
            }
            
        }

        //The standard algorithm performed better than the one with the remainder
        if(totalCounter < remainderCounter)
        {
            //Return standard result
            return totalCounter;
        }

        //The remainder algorithm had few number of values needed to reach target
        return remainderCounter;
    
    }

    /**
     * public   :  RemainderSearch
     *  This method implements and perfoms a binary search on the set
     *  of values in order to determine if the remainder value found
     *  in the useRemainder function is also within the set. If the
     *  remainder is found in the function, it will add 1 to the 
     *  counting index of the remainder as it will only take 1
     *  of these to be included to reach the target value. After
     *  it will return true as there was a remainder found within
     *  the set. If no remainder is found, the method will
     *  return false instead as none was found in the set.
     * 
     * Parameters:
     *      int     : The remainder left needed to reach the target to be searched for
     * Returns:
     *      boolean :   Whether the remainder was in the set or not
     */
    private boolean RemainderSearch(int remainder)
    {
        //Get range of where to search in the set
        int left = 0, right = setValues.length - 1;

        while (left <= right)   //There are still indeces left between current range to check
        {
            //Look at middle value within the current range
            int mid = left + (right - left) / 2;
  
            //Remainder is the middle value
            if (setValues[mid] == remainder)
            {
                //It takes one remainder value to reach the target
                change[mid] = 1;
                //End method as remainder has been found
                return true;
            }
  
            // Remainder is greater than current middle value
            if (setValues[mid] < remainder)
                //Continue looking only past values after the current middle
                left = mid + 1;
  
            //Remainder is smaller than current middle value
            else
                //Continue looking only before values after the current middle
                right = mid - 1;
        }
  
        return false; //Remainder is not in the set
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
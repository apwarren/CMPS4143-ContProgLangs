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
 * 
 * 
 * 
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
public class NumWithMultiple 
{
    private int[] setValues;
    private int targetValue;
    private int bestSize;
    private int[]change;

    NumWithMultiple(int[] mySet, int myTarget)
    {
        setValues = mySet;
        targetValue = myTarget;
        bestSize = useMultiple(setValues, targetValue);
    }

    private int useMultiple(int[] set, int target)
    {
        change = new int[set.length];
        int totalCounter = 0;
        int multipleCounter = target;
        int currentTarget = target;
        int remainder = -1;
        int size = set.length;
        boolean multipleFound = false;
        for(int i = size - 1; i >= 0; --i)
        {
            if(set[i] <= currentTarget)
            {
                totalCounter += currentTarget / set[i];
                currentTarget %= set[i];
            }
            if(set[i] <= target && !multipleFound)
            {
                remainder = target % set[i];
                if (remainder == 0){
                    change[i] = target / set[i];
                    multipleCounter = target / set[i];
                    multipleFound = true;
                }
            }
            if(currentTarget == 0) 
            {
                break;
            }
            
        }

        if(totalCounter < multipleCounter)
        {
            return totalCounter;
        }
            return multipleCounter;
    
    }

    public int BestCase()
    {
        return bestSize;
    }

    public int[] getChange()
    {
        return change;
    }
}
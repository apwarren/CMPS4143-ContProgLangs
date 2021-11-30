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
public class NumWithRemainder
{
    private int[] setValues;
    private int targetValue;
    private int bestSize;
    private int[]change;

    NumWithRemainder(int[] mySet, int myTarget)
    {
        setValues = mySet;
        targetValue = myTarget;
        bestSize = useRemainder(setValues, targetValue);
    }

    private int useRemainder(int[] set, int target)
    {
        change = new int[set.length];
        int totalCounter = 0;
        int remainderCounter = target;
        int currentTarget = target;
        int remainder = -1;
        int size = set.length;
        boolean remainderFound =false;
        for(int i = size - 1; i >= 0; --i)
        {
            if(set[i] < currentTarget)
            {
                totalCounter += currentTarget / set[i];
                currentTarget %= set[i];
            }
            if(set[i] < target && !remainderFound)
            {
                remainder = target % set[i];
                if(RemainderSearch(remainder) || remainder == 0) //The remainder is also in list
                {  
                    if(remainder == 0)
                    {
                        change[i] = target / set[i];
                        remainderCounter = target / set[i];
                    }
                    else
                    {
                    change[i] = target / set[i];
                    remainderCounter = ((target / set[i]) + 1); //Leave method/ remainder is found so one extra number
                    }
                    remainderFound = true;
                }
            }
            if(currentTarget == 0) 
            {
                break;
            }
            
        }

        if(totalCounter < remainderCounter)
        {
            return totalCounter;
        }
            return remainderCounter;
    
    }
    private boolean RemainderSearch(int remainder)
    {
        int left = 0, right = setValues.length - 1;
        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
  
            // Check if x is present at mid
            if (setValues[mid] == remainder)
            {
                change[mid] = 1;
                return true;
            }
  
            // Remainder is greater
            if (setValues[mid] < remainder)
                left = mid + 1;
  
            //Remainder is smaller
            else
                right = mid - 1;
        }
  
        return false; //Remainder is not in set
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


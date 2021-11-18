import java.util.ArrayList;

public class NumWithRemainder
{
    private int[] setValues;
    private int targetValue;
    private int bestSize;
    ArrayList<Integer> change = new ArrayList<Integer>();

    NumWithRemainder(int[] mySet, int myTarget)
    {
        setValues = mySet;
        targetValue = myTarget;
        bestSize = useRemainder(setValues, targetValue);
    }

    private int useRemainder(int[] set, int target)
    {
        int totalCounter = 0;
        int remainderCounter = target;
        int currentTarget = target;
        int remainder;
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
                    for(int x = 0; x < target / set[i]; ++x)
                    {
                        change.add(target / set[i]);
                    }
                    if(remainder == 0)
                    {
                        remainderCounter = target / set[i];
                    }
                    else
                    {
                    remainderCounter = ((target / set[i]) + 1); //Leave method/ remainder is found so one extra number
                    change.add(target % set[i]);
                    }
                    remainderFound = true;
                }
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
        int l = 0, r = setValues.length - 1;
        while (l <= r) 
        {
            int m = l + (r - l) / 2;
  
            // Check if x is present at mid
            if (setValues[m] == remainder)
                return true;
  
            // Remainder is greater
            if (setValues[m] < remainder)
                l = m + 1;
  
            //Remainder is smaller
            else
                r = m - 1;
        }
  
        return false; //Remainder is not in set
    }

    public int BestCase()
    {
        return bestSize;
    }

    public Object[] getChange()
    {
        return change.toArray();
    }
}
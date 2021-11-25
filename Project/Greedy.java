import java.util.ArrayList;

public class Greedy 
{
    
    private int[] setValues;
    private int targetValue;
    private int bestSize;
    private int[] change;

    Greedy(int[] mySet, int myTarget)
    {
        setValues = mySet;
        targetValue = myTarget;
        bestSize = getGreedy(setValues, targetValue);
    }

    private int getGreedy(int[] set, int target)
    {
        change = new int[set.length];
        int totalCounter = 0;
        int currentTarget = target;
        int size = set.length;
        for(int i = size - 1; i >= 0; --i)
        {
            if(set[i] < currentTarget)
            {
                for(int x = 0; x < target / set[i]; ++x)
                {
                    change[i] = currentTarget / set[i];
                }
                totalCounter += currentTarget / set[i];
                currentTarget %= set[i];
            }
            if(currentTarget == 0)
            {
                break;
            }
        }

        return totalCounter;
    
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

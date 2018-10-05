
public class BooleanSource
{
    private float probability;
    
    public BooleanSource(float p)
    {
        if(p < 0 || 1 < p)
            throw new IllegalStateException("Illegal p: " + p);
        probability = p;
    }
    
    public boolean query()
    {
        return (Math.random() < probability);
    }

}




public class Runway {
    private float  landTimeLeft, takeoffTimeLeft;

    private int landTime, takeoffTime;
    public Runway(int LandTime, int TakeoffTime)
    {
            landTime = LandTime;
            takeoffTime = TakeoffTime;
        landTimeLeft = 0;
        takeoffTimeLeft = 0;
    }
    
    
    
    public boolean isBusy()
    {
            return(landTimeLeft > 0 || takeoffTimeLeft > 0);
    }
    
    public void reduceRemainingTime()
    {
        if(landTimeLeft > 0)
            landTimeLeft--;
        if(takeoffTimeLeft > 0)
            takeoffTimeLeft--;
    }
    
    public void land()
    {
            if(isBusy())
                throw new IllegalStateException("Runway is busy");
            else
                landTimeLeft = landTime;
    }
    public void takeoff()
    {
        if(isBusy())
            throw new IllegalStateException("Runway is busy");
        else
            takeoffTimeLeft = takeoffTime;
    }
    
}








import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class AirportSim
{   
    public static void RunSim()
    {
        int
        landTime = 0,
        takeoffTime = 0,
        avgTimeBTArrivals = 0,
        avgTimeBTDepartures = 0,
        maxWaitTimeLanding = 0,
        totalTime = 0,
        crashes = 0;
        float
        probArrivals = 0,
        probTakeoffs = 0;
           
        Scanner kb = new Scanner(System.in);
        System.out.println("Time to land: ");
        landTime = kb.nextInt();
        System.out.println("Time to takeoff: ");
        takeoffTime = kb.nextInt();
        System.out.println("Probability of arrival: ");
        probArrivals = kb.nextFloat();
        System.out.println("Probability of departure: ");
        probTakeoffs = kb.nextFloat();
        System.out.println("Avergae time between arrivals: ");
        avgTimeBTArrivals = kb.nextInt();
        System.out.println("Average time between departures: ");
        avgTimeBTDepartures = kb.nextInt();
        System.out.println("Maximum amount of time waiting to land: ");
        maxWaitTimeLanding = kb.nextInt();
        System.out.println("Total simulation run time: ");
        totalTime = kb.nextInt();
        kb.close();
       
        Queue<Integer> arrivals = new LinkedList<Integer>();
        Queue<Integer> departures = new LinkedList<Integer>();       
        BooleanSource arriving = new BooleanSource(probArrivals);       
        BooleanSource departing = new BooleanSource(probTakeoffs);       
        Averager landings = new Averager();
        Averager takeoffs = new Averager();
        Runway runway = new Runway(landTime, takeoffTime);
        int next;
       
       
        for(int currentSecond = 1; currentSecond <= totalTime; currentSecond++)
        {
            
            
            if(arriving.query())
            {
                arrivals.add(currentSecond);
            }
            else if(departing.query())
            {
                departures.add(currentSecond);
            }
            
            if((!runway.isBusy()) && ((!arrivals.isEmpty()) || (!departures.isEmpty())))
            {

                if(!arrivals.isEmpty())
                {
                    next = arrivals.remove();
                    if(currentSecond - next > maxWaitTimeLanding)
                        crashes++;
                    else
                    {
                    
                    landings.addNumber(currentSecond - next);
                    runway.land();
                    }
                }
                else
                {
                    next = departures.remove();
                    takeoffs.addNumber(currentSecond - next);
                    runway.takeoff();
                }
                
            
            }
            runway.reduceRemainingTime();
        }
        System.out.println("Takeoffs: " + landings.howManyNumbers());
        System.out.println("Landings: " + takeoffs.howManyNumbers());
        System.out.println("Crashes: " + crashes);
        System.out.println("Average wait time for arrivals: " + landings.average());
        System.out.println("Average wait time for takeoffs: " + takeoffs.average());
    }    
    public static void main(String[] args)
    {
        RunSim();
    }
}












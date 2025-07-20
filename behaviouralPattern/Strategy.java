/*

Problem:
    - matchRide() is selecting the strategy violating SRP and OCP.
    - no separation of concerns.

- We can still use factory with abstractions and concretes but we have to pass the matching type from client and factory decides the strategy.
- Strategy pattern is the good fit when it comes to algo selection.

class RideMatchingService{
    // violates SRP and OCP
    public void matchRide(String riderLocation, String matchingType){
        if(matchingType.equalsIgnoreCase("NEAREST_LOCATION")){
            // nearest location algorithm
            System.out.println("Matching rider at " + riderLocation + " with nearest driver.");
        } else if(matchingType.equalsIgnoreCase("SURGE_PRIORITY")){
            // priority algo
            System.out.println("Matching rider at " + riderLocation + " based on surge pricing priority.");
        } else if(matchingType.equalsIgnoreCase("AIRPORT_QUEUE")){
            // FIFO
            System.out.println("Matching rider at " + riderLocation + " from airport queue.");
        } else {
            System.out.println("Invalid Matching Strategy");
        }
    }
}

class Main{
    public static void main(String[] args) {
        RideMatchingService rideMatchingService = new RideMatchingService();
        
        rideMatchingService.matchRide("flagstaff", "NEAREST_LOCATION");

        rideMatchingService.matchRide("flagstaff", "SURGE_PRIORITY");

        rideMatchingService.matchRide("flagstaff", "AIRPORT_QUEUE");

    }
}

*/

interface MatchingStrategy{
    void matchRider(String location);
}

class NearestLocationStrategy implements MatchingStrategy{

    @Override
    public void matchRider(String location) {
        System.out.println("Matching rider at " + location + " with nearest driver.");
    }

}

class SurgePriorityStrategy implements MatchingStrategy{

    @Override
    public void matchRider(String location) {
        System.out.println("Matching rider at " + location + " based on surge pricing priority.");
    }

}

class AirportQueueStrategy implements MatchingStrategy{

    @Override
    public void matchRider(String location) {
        System.out.println("Matching rider at " + location + " from airport queue.");
    }

}

class RideMatchingService{
    private MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy){
        this.strategy = strategy;
    }

    public void matchRider(String location){
        this.strategy.matchRider(location);
    }
}

public class Strategy {
    public static void main(String[] args) {
        RideMatchingService rideMatchingService = new RideMatchingService(new AirportQueueStrategy());
        rideMatchingService.matchRider("flagstaff");

        rideMatchingService.setStrategy(new NearestLocationStrategy());
        rideMatchingService.matchRider("flagstaff");
    
    }
}

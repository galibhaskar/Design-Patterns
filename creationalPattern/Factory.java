/* 
Problem that Factory Solves:
    - Hide the object creation complexity based on the input using factory.

When to use:
    - client code needs to work with multiple objects based on the input.
    - decision of which object to create based on parameters.


Basic Structure of Factory Pattern:
    - Product: Interface or Abstract Class
    - Concrete Products: Concrete implementations of the class.
    - Factory: A class with a method that returns the instances of the concrete products based on the input.

Cons:
    - more complex code
    - more code overhead for multiple factories
*/

interface Logistics{
    void send();
}

class Road implements Logistics{
    @Override
    public void send() {
        System.out.println("Sending through Road");
    }
};

class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending through Air");
    }
};

class Train implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending through Train");
    }
}

class LogisticsFactory {
    public static Logistics getLogistics(String mode){
        // Follows OCP, when new mode is added,
            // we can add logic to create new object like train
        if(mode.equalsIgnoreCase("Air")){
            return new Air();
        } else if(mode.equalsIgnoreCase("Train")){
            return new Train();
        }

        return new Road();
    }
};

class LogisticsService {
    public void sendPackage(String mode){
        // Follows SRP(focus on getting logistics based on mode and send)

        // Logistics service doesn't know anything about which object is created, 
            // it only depends on Logistics interface and invokes the send()
        // LogisticsFactory is taking responsibility in creating the object based on the mode.
        Logistics logistics = LogisticsFactory.getLogistics(mode);

        logistics.send();
    }
};

public class Factory {
    public static void main(String[] args) {
        LogisticsService logisticsService = new LogisticsService();

        logisticsService.sendPackage("Train");
    }
}

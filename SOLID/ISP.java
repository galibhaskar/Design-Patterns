/* 
ISP: 
 - Don't force the class to implement the methods that it do not use.
    (instead segregate the interface and implement the required ones in the classes)

Problem:

//bloated interface

interface Uber{
    // methods for driver
    void acceptRide();
    void endRide();

    // methods for rider
    void bookRide();
    void payRide();
}

class Rider implements Uber{

    @Override
    public void acceptRide() {
        throw new UnsupportedOperationException("Unimplemented method 'acceptRide'");
    }

    @Override
    public void endRide() {
        throw new UnsupportedOperationException("Unimplemented method 'endRide'");
    }

    @Override
    public void bookRide() {
        System.out.println("Booking ride....");
    }

    @Override
    public void payRide() {
        System.out.println("Paying for the ride....");
    }

}

class Driver implements Uber{

    @Override
    public void acceptRide() {
       System.out.println("Accepting the ride...");
    }

    @Override
    public void endRide() {
       System.out.println("Ending the ride...");
    }

    @Override
    public void bookRide() {
        throw new UnsupportedOperationException("Unimplemented method 'bookRide'");
    }

    @Override
    public void payRide() {
        throw new UnsupportedOperationException("Unimplemented method 'payRide'");
    }
}

*/

interface RiderInterface {
    void bookRide();
    void payRide();
}

interface DriverInterface{
    void acceptRide();
    void endRide();
}

class Driver implements DriverInterface{

    @Override
    public void acceptRide() {
        System.out.println("Accepting the ride...");
    }

    @Override
    public void endRide() {
        System.out.println("Ending the ride...");
    }

}

class Rider implements RiderInterface{

    @Override
    public void bookRide() {
        System.out.println("Booking the ride...");
    }

    @Override
    public void payRide() {
        System.out.println("Paying the ride...");
    }

}


/* Example - 2 

Problem:

//bloated interface 

interface Bird{
    void eat();

    void fly();
}

class Crow implements Bird{

    @Override
    public void fly() {
        System.out.println("crow is flying");
    }

    @Override
    public void eat() {
        System.out.println("crow is eating");
    }

}

class Ostrich implements Bird{
    @Override
    public void fly() {
        throw UnsupportedOperationException("ostrich cannot fly");
    }

    @Override
    public void eat() {
        System.out.println("Ostrich is eating");
    }

}

*/

interface Bird {
    void eat();
}

interface Flyable{
    void fly();
}

class Crow implements Bird, Flyable{

    @Override
    public void fly() {
        System.out.println("crow is flying");
    }

    @Override
    public void eat() {
        System.out.println("crow is eating");
    }

}

class Ostrich implements Bird{

    @Override
    public void eat() {
        System.out.println("Ostrich is eating");
    }

}


public class ISP {
    public static void main(String[] args) {
        DriverInterface driver = new Driver();
        driver.acceptRide();
        driver.endRide();

        RiderInterface rider = new Rider();
        rider.bookRide();
        rider.payRide();


        /* Example-2 testing */
        Bird bird = new Crow();
        bird.eat();

        Flyable flyingCrow = new Crow();
        flyingCrow.fly();

        Bird ostrich = new Ostrich();
        ostrich.eat();
    }
}

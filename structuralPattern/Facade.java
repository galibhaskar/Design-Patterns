/*
Problem that Facade Solves:
    - Client dealing with multiple components or services
    (like manual car - brake, accelerator, clutch, and gear
        instead use automatic car - facade interface(internally takes care of clutch and gear))

When to use it:
    - when subsystems are complex and 
        we want to provide simpler interface experience for the client.
    - when we want to reduce tight coupling between client code and subsystems.

Cons:
    - violates the SRP
    - fragile coupling(deals with multiple subsystems, when one subsystem fails, there should be an option to revert the previous actions performed)
*/

class PaymentService{
    public void makePayment(String bookingId, double amount){
        System.out.println("Making payment for "+bookingId+" with amount:"+amount);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Reserved the seat:"+seatNumber+" for the movie:"+movieId);
    }
}

class NotificationService{
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Sending booking confirmation for the user:"+userEmail);
    }
}

class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Generating ticket for the movie:"+movieId+" seat:"+seatNumber);
    }
}

// Facade Class
class MovieFacade{
    private PaymentService paymentService;
    private NotificationService notificationService;
    private TicketService ticketService;
    private SeatReservationService seatReservationService;

    public MovieFacade(){
        // can be done using dependency injection instead of object creation in constructor.
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.ticketService = new TicketService();
    }

    // we can use "builder pattern" as there are multiple parameters
    public void bookMovieTicket(String bookingId, double amount, String movieId, String seatNumber, String userEmail){
        paymentService.makePayment(bookingId, amount);
        seatReservationService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        notificationService.sendBookingConfirmation(userEmail);
    }
}

class Booking{
    public final String bookingID;
    public final double amount;
    public final String movieID;
    public final String seatNumber;
    public final String userEmail;

    private Booking(Builder obj){
        this.bookingID = obj.bookingID;
        this.movieID = obj.movieID;
        this.seatNumber = obj.seatNumber;
        this.userEmail = obj.userEmail;
        this.amount = obj.amount;
    }

    public static class Builder{
        private String bookingID;
        private double amount;
        private String movieID;
        private String seatNumber;
        private String userEmail;

        public Builder withBookingID(String bookingID){
            this.bookingID = bookingID;

            return this;
        }

        public Builder withAmount(double amount){
            this.amount = amount;

            return this;
        }

        public Builder withSeatNumber(String seatNumber){
            this.seatNumber = seatNumber;

            return this;
        }

        public Builder withMovieID(String movieID){
            this.movieID = movieID;

            return this;
        }

        public Builder withUserEmail(String userEmail){
            this.userEmail = userEmail;

            return this;
        }

        public Booking build(){
            return new Booking(this);
        }
    }
}


// Facade Class with Builder
class MovieFacadeWithBuilder{
    private PaymentService paymentService;
    private NotificationService notificationService;
    private TicketService ticketService;
    private SeatReservationService seatReservationService;

    public MovieFacadeWithBuilder(){
        // can be done using dependency injection instead of object creation in constructor.
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(Booking booking){
        paymentService.makePayment(booking.bookingID, booking.amount);
        seatReservationService.reserveSeat(booking.movieID, booking.seatNumber);
        ticketService.generateTicket(booking.movieID, booking.seatNumber);
        notificationService.sendBookingConfirmation(booking.userEmail);
    }
}

public class Facade {
    public static void main(String[] args) {
        MovieFacadeWithBuilder movieFacade = new MovieFacadeWithBuilder();

        Booking booking = new Booking.Builder()
                        .withBookingID("ID123")
                        .withAmount(100)
                        .withMovieID("M001")
                        .withSeatNumber("K12")
                        .withUserEmail("user@abc.com")
                        .build();

        movieFacade.bookMovieTicket(booking);

        MovieFacade movieFacade2 = new MovieFacade();

        movieFacade2.bookMovieTicket("ID123", 100, "M001", "K12", "user@abc.com");
    }
}

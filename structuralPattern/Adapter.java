/*
Problem that Adapter Solves:
- Whenever we have incompatible interfaces like (PaymentGateway and RazorPay API), 
    adapter pattern acts as wrapper/translator.
- It acts a bridge between Target Interface(expected interface) and Adaptee(existing functionality with external API or class) 
 
When to use it:
- interface that system expects doesn't match with new class.
- When we want to reuse the legacy code
- integrating with third-party APIs.

Cons:
- Adds an extra layer of abstraction that is hard to understand the architecture.


*/
interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayUGoGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("payment through payugo");
    }
}

class RazorPayAPI {
    public void makePayment(String orderId, double amount){
        System.out.println("Making payment through Razor Pay");
    }
}

// Adpater Class
class RazorPayGateway implements PaymentGateway{
    private final RazorPayAPI razorPayAPIInstance;

    public RazorPayGateway(){
        this.razorPayAPIInstance = new RazorPayAPI();
    }

    @Override
    public void pay(String orderId, double amount) {
        this.razorPayAPIInstance.makePayment(orderId, amount);
    }
}

class CheckoutService{
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(String orderId, double amount){
        this.paymentGateway.pay(orderId, amount);
    }
}

public class Adapter {
    public static void main(String[] args) {
        CheckoutService checkoutService1 = new CheckoutService(new PayUGoGateway());
        checkoutService1.processPayment("123", 100);

        CheckoutService checkoutService2 = new CheckoutService(new RazorPayGateway());
        checkoutService2.processPayment("341324", 400);
    }
}

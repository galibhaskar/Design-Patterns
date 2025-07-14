/*

Problem that Factory Solves:
    Situation when we have to select factory based on the type out of multiple factories.

When to use:
    - When consistency across a family of related products must be maintained 
        (e.g., a US payment gateway paired with a US-style invoice, India with GST,..).


Basic Structure of Abstract Factory Pattern:
    1. Create basic factories:
        Product: Interface or Abstract Class
        Concrete Products: Concrete implementations of the class.
        Factory: A class with a method that returns the instances of the concrete products based on the input.
    2. Create Factory of Factories using interface(RegionFactory)
        combine the related products for a factory in concrete implementation
            (e.g.., USRegionFactory -> only Stripe and Paypal, 
                        IndiaRegionFactory -> only PayUGo, RazorPay)
    

Pros:
    - Promotes consistency by combining related components together.
    - Supports OCP
    - Provides layer of abstraction using interfaces for object creation.

Cons:
    - Increased code complexity

*/

// interface that helps in creating factory based on payment type
interface PaymentGateway{
    void processPayment(double amount);
}

// Concrete Class
class RazorPayGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("payment processing through razor pay with amount:"+amount);
    }
}

// Concrete Class
class StripeGateway implements PaymentGateway{

    @Override
    public void processPayment(double amount) {
        System.out.println("payment processing through stripe with amount:"+amount);
    }
}

// Concrete Class
class PayPalGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("payment processing through paypal with amount:"+amount);
    }
}

// Concrete Class
class PayUGoGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("payment processing through payugo with amount:"+amount);
    }
}


interface Invoice{
    void generateInvoice();
}

class USInvoice implements Invoice{

    @Override
    public void generateInvoice() {
        System.out.println("generating invoice for the US");
    }
}

class GSTInvoice implements Invoice{

    @Override
    public void generateInvoice() {
        System.out.println("generating invoice for the india");
    }

}

// Maintaining a factory that operates factory for gateways based on the type through an interface
interface RegionFactory{
    PaymentGateway createPaymentGateway(String gatewayType);

    Invoice createInvoice();
}   


// Concrete factory
class IndiaRegionFactory implements RegionFactory{

    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if(gatewayType.equalsIgnoreCase("PayUGo")){
            return new PayUGoGateway();
        } else if(gatewayType.equalsIgnoreCase("Razor Pay")){
            return new RazorPayGateway();
        }

        throw new IllegalArgumentException("gateway type is not supported");
    }

    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }

}

// Concrete factory
class USRegionFactory implements RegionFactory{

    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if(gatewayType.equalsIgnoreCase("Stripe")){
            return new StripeGateway();
        } else if(gatewayType.equalsIgnoreCase("Paypal")){
            return new PayPalGateway();
        }

        throw new IllegalArgumentException("gateway type is not supported");
    }

    @Override
    public Invoice createInvoice() {
        return new USInvoice();
    }
    
}

class CheckoutService{
    PaymentGateway paymentGateway;

    Invoice invoice;

    public CheckoutService(RegionFactory factory, String gatewayType){
        this.paymentGateway = factory.createPaymentGateway(gatewayType);

        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount){
        this.paymentGateway.processPayment(amount);

        this.invoice.generateInvoice();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        CheckoutService indiaCheckout = new CheckoutService(new IndiaRegionFactory(), "PayUGo");

        indiaCheckout.completeOrder(100);


        CheckoutService usaCheckout = new CheckoutService(new USRegionFactory(), "stripe");

        usaCheckout.completeOrder(200);
    }
}

/*
    Problem:
        - Behaviour of the object based on the state
            (Order Placed -> Preparing -> Shipped -> Delivered)


class Order{
    private String state;

    public Order(){
        this.state = "ORDER_PLACED";
    }

    public void cancelOrder(){
        // tight coupling and violates OCP
        if(state.equalsIgnoreCase("ORDER_PLACED") || 
            state.equalsIgnoreCase("PREPARING")){
                state = "CANCELLED";

                System.out.println("Order cancelled");
        } else{
            System.out.println("Cannot cancel the order");
        }
    }

    public void nextState(){
        // tight coupling and violates OCP
        switch(state){
            case "ORDER_PLACED":
                this.state = "PREPARING";
                break;
            
            case "PREPARING":
                this.state = "SHIPPING";
                break;

            case "SHIPPING":
                this.state = "DELIVERED";
                break;
            
            default:
                System.out.println("Next state invalid");
        }
    }

    public void printState(){
        System.out.println("Current State:"+this.state);
    }
}

class Main{
    public static void main(String[] args) {
        Order order = new Order();

        order.printState();
        order.nextState();
        order.printState();
        order.nextState();
        order.printState();
        order.cancelOrder();
        order.printState();
        order.nextState();
        order.printState();
    }
}

 */



interface OrderState{
    void nextState(OrderContext context);

    void cancelOrder(OrderContext context);

    String getStateName();
}

class NewOrderState implements OrderState{
    private String name;

    public NewOrderState(){
        this.name = "ORDER_PLACED";
    }

    @Override
    public void nextState(OrderContext context) {
        System.out.println("Order Placed...");

        context.setState(new PreparingOrderState());
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("Order cancelled");

        context.setState(new CancelledOrderState());
    }

    @Override
    public String getStateName() {
        return name;
    }

}

class PreparingOrderState implements OrderState{
    private String name;

    public PreparingOrderState(){
        this.name = "PREPARING";
    }

    @Override
    public void nextState(OrderContext context) {
        System.out.println("Order is getting prepared...");
        
        context.setState(new ShippingOrderState());
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("Order cancelled");

        context.setState(new CancelledOrderState());
    }

    @Override
    public String getStateName() {
        return name;
    }

}

class ShippingOrderState implements OrderState{
    private String name;

    public ShippingOrderState(){
        this.name = "SHIPPING";
    }

    @Override
    public void nextState(OrderContext context) {
        System.out.println("Shipping your Order....");

        context.setState(new DeliveredOrderState());
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("Cannot cancel the order...");
    }

    @Override
    public String getStateName() {
        return name;
    }

}

class DeliveredOrderState implements OrderState{
    private String name;

    public DeliveredOrderState(){
        this.name = "DELIVERED";
    }

    @Override
    public void nextState(OrderContext context) {
        System.out.println("Order delivered");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("Cannot cancel the order");
    }

    @Override
    public String getStateName() {
        return name;
    }

}

class CancelledOrderState implements OrderState{
    private String name;

    public CancelledOrderState(){
        this.name = "CANCELLED";
    }

    @Override
    public void nextState(OrderContext context) {
        System.out.println("Order cancelled");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("Order is already cancelled");
    }

    @Override
    public String getStateName() {
        return name;
    }

}

class OrderContext{
    private OrderState state;

    public OrderContext(){
        this.state = new NewOrderState();
    }

    public void setState(OrderState state){
        this.state = state;
    }

    public void next(){
        state.nextState(this);
    }

    public void cancel(){
        state.cancelOrder(this);
    }

    public void printCurrentState(){
        System.out.println(state.getStateName());
    }
}

public class State {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.printCurrentState();

        order.next();

        order.printCurrentState();

        order.next();

        order.printCurrentState();

        order.cancel();

        order.printCurrentState();

        order.next();

        order.printCurrentState();
    }
}

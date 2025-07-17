/* 
Problem that Decorator Solves:
    - Class explosion with different combinations for subclasses.

When to use it:
    - add responsibilities to objects dynamically during runtime without modifying their structure.
    - instead of creating subclasses during compile time, 
        we wrap objects of one class with object of another class during runtime.
            (like a gift wrap or stacking the objects on top of each other).

How to implement:
    - Component interface: methods
    - Concrete Component: base class that acts as initial item in wrapping. 
    - Abstract Decorator: Implements component interface and 
            holds the reference to Concrete component object using interface,
            contains constructor that initializes the Concrete Component object.
    - Concreate Decorator: Extends Abstract Decorator and in the constructor, 
        we initialize the base class using super and override the component interface methods

Cons:
    - Stack trace debugging is difficult
    - Overhead of multiple wrapping classes
    - Steep learning curve for new developers.

Real world examples:
1. Food delivery applications(adding various food combinations)
2. Google docs for formatting(bold, italic, underline,...)
*/


// component interface 

import java.util.*;

interface Pizza{
    double getCost();

    String getDescription();
}

// concrete component
class PlainPizza implements Pizza{

    @Override
    public double getCost() {
        return 20.0;
    }

    @Override
    public String getDescription() {
        return "Plain pizza";
    }
}

// concrete component
class MargheritaPizza implements Pizza{

    @Override
    public double getCost() {
        return 40.0;
    }

    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }
}

// Abstract Decorator implementing component interface
abstract class PizzaDecorator implements Pizza{
    // has-a relationship that gets initialized when concrete decorator is defined.
    protected Pizza pizza;

    public PizzaDecorator(Pizza type){
        this.pizza = type;
    }
}

// Concrete Decorator extending Abstract Decorator with constructor to initialize basetype
class ExtraCheese extends PizzaDecorator{
    public ExtraCheese(Pizza pizzaType) {
        super(pizzaType);
    }
    
    @Override
    public double getCost() {
        return pizza.getCost() + 20.0;
    }
    
    @Override
    public String getDescription() {
        return pizza.getDescription() + " with cheese";
    }
    
}

// Concrete Decorator extending Abstract Decorator with constructor to initialize basetype
class Olives extends PizzaDecorator{
    public Olives(Pizza type) {
        super(type);
    }
    
    @Override
    public double getCost() {
        return pizza.getCost() + 9.99;
    }
    
    @Override
    public String getDescription() {
        return pizza.getDescription() + " with olives";
    }
}

// Concrete Decorator extending Abstract Decorator with constructor to initialize basetype
class StuffedCrust extends PizzaDecorator{
    public StuffedCrust(Pizza type){
        super(type);
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 10.0;
    }

    @Override
    public String getDescription() {
       return pizza.getDescription() + " with stuffed crust";
    }

}

class PizzaFactory{
    private static Pizza createBasePizza(String type){
        if(type.equalsIgnoreCase("Plain")){
            return new PlainPizza();
        } 
        return new MargheritaPizza();
    }

    public static Pizza createPizza(String type, List<String> toppings){
        Pizza base = createBasePizza(type);

        for(String topping: toppings){
            switch(topping){
                case "cheese":
                    base = new ExtraCheese(base);
                    break;
                case "olives":
                    base = new Olives(base);
                    break;
                case "stuffedCrust":
                    base = new StuffedCrust(base);
                    break;
                default:
                    System.out.println("Unknown toppings!"+ topping+" skipped!");
            }
        }

        return base;
    }
}

public class Decorator {
    public static void main(String[] args) {
        // Pizza pizzaOrder1 = new Olives(new ExtraCheese(new PlainPizza()));

        // System.out.println(pizzaOrder1.getDescription()+ " cost:"+ pizzaOrder1.getCost());
        
        // Pizza pizzaOrder2 = new StuffedCrust(new Olives(new ExtraCheese(new MargheritaPizza())));
        
        // System.out.println(pizzaOrder2.getDescription()+ " cost:"+ pizzaOrder2.getCost());
        
        Pizza pizzaOrder1 = PizzaFactory.createPizza("plain", List.of("olives", "cheese"));
        
        System.out.println(pizzaOrder1.getDescription()+ " cost:"+ pizzaOrder1.getCost());
        
        Pizza pizzaOrder2 = PizzaFactory.createPizza("margherita", List.of("olives", "cheese", "stuffedCrust"));
        
        System.out.println(pizzaOrder2.getDescription()+ " cost:"+ pizzaOrder2.getCost());
    }
}

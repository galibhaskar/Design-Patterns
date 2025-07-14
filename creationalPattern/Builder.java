/*
Problem that Builder Solves:
    Telescoping Constructor (Anti-pattern) - Constructor overloading with different param combinations.

When to use:
    - if immutability(no updates to object after creation) is preferred
    - class with multiple optional parameters.
    - great for classes with complex configurations or custom attributes.

When to avoid:
    - class with 1-2 fields
    - dont need immutability or custom object build

Basic Structure of Builder Pattern:
    - All parameters in main class are tagged with FINAL.
    - constructor as private that accepts builder as parameter.
    - inner static class - builder that has same fields but NOT TAGGED AS FINAL.
    - create a constructor for inner static class with required params.
    - add methods for each optional params (ex: withCheese, withSides,... - Fluent API Style) that accepts parameters and return the Builder Object.
    - finally, add build() method that creates immutable object for the main class using builder object.
*/

import java.util.*;

class BurgerMeal {
    // required parameters
    private final String bunType;
    private final String pattyType;

    //optional parameters
    private final boolean hasCheese;
    private final String side;
    private final List<String> toppings; 
    private final boolean hasDrink;

    private BurgerMeal(MealBuilder builder){
        this.bunType = builder.bunType;
        this.pattyType = builder.pattyType;
        this.hasCheese = builder.hasCheese;
        this.side = builder.side;
        this.toppings = builder.toppings;
        this.hasDrink = builder.hasDrink;
    }

    @Override
    public String toString(){
        return "bun:"+bunType+" patty:"+pattyType+" side:"+side+" drink:"+hasDrink+" toppings:"+ toppings+" cheese:"+hasCheese;
    }

    public static class MealBuilder{
        private String bunType;
        private String pattyType;

        private boolean hasCheese;
        private String side;
        private List<String> toppings; 
        private boolean hasDrink;

        public MealBuilder(String bunType, String pattyType){
            this.bunType = bunType;

            this.pattyType = pattyType;
        }

        public MealBuilder withCheese(boolean value){
            this.hasCheese = value;

            return this;
        }

        public MealBuilder withSide(String value){
            this.side = value;

            return this;
        }

        public MealBuilder withDrink(boolean value){
            this.hasDrink = value;

            return this;
        }

        public MealBuilder withToppings(List<String> value){
            this.toppings = value;

            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    };
};

public class Builder {
    public static void main(String[] args) {
        // Note: dont use same prefix as main class like BurgerMealBuilder
        BurgerMeal meal1 = new BurgerMeal.MealBuilder("wheat", "veg")
            .build();
    
        System.out.println(meal1.toString());

        BurgerMeal meal2 = new BurgerMeal.MealBuilder("multi-grain", "non-veg")
                .withCheese(true)
                .withSide("fries")
                .withDrink(true)
                .build();

        System.out.println(meal2.toString());
    }
}



// -------------------------------------------------------------------------------
// Real-world examples
// -------------------------------------------------------------------------------

// 1.Lombok - famous java Library that internally implements builder design pattern, getters, setters,...

// import lombok.Builder;

// @Builder
// class User{
//     private String name;
//     private int age;
//     private String address;
// }

// User user = new User().name("abc").age(20).address("AZ").build();



// 2.Amazon Cart - each user builds the cart with different items, 
//      with different quantity, with customizable options(like gift wrap),....
/* 

Problem:
    - When tax calculator has to support USA, 
        a new parameter - region is added, which affects the client code

Previous Code:

class TaxCalculator{
    public double calculateTax(double amount){
        return (amount + 0.18 * amount);
    }
}

modification:
    - adding region as parameter to support USA tax calculation

class TaxCalculator{
    public double calculateTax(double amount, String region){
        if(region.equalsIgnoreCase("USA")){
            return (amount + 0.20 * amount);
        } else if (region.equalsIgnoreCase("INDIA")){
            return (amount + 0.18 * amount);
        } else{    
            return amount;
        }
    }
}

VIOLATING OCP(Open Closed Principle - Open for Extension, Closed for Modification)


MISCONCEPTIONS of OCP:
- There is no hard rule for not modifying the code: 
    OCP states we should avoid changes to the core logic that affects the clients,
        instead prefer extending the core logic.    

- OCP is overkill with too many classes:
    - Its the matter of maintainability and testability.
    
*/

interface TaxCalculator{
    double calculateTax(double amount);
}

class IndiaTaxCalculator implements TaxCalculator{

    @Override
    public double calculateTax(double amount) {
        return amount + 0.18 * amount;
    }

}

class USATaxCalculator implements TaxCalculator{

    @Override
    public double calculateTax(double amount) {
       return amount + 0.20 * amount;
    }

}

// can extend the functionality for calculating UK tax with new class,
    // and client just have to use the UKTaxCalculator Instance.

public class OCP {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new IndiaTaxCalculator();

        System.out.println(taxCalculator.calculateTax(100));

        taxCalculator = new USATaxCalculator();

        System.out.println(taxCalculator.calculateTax(100));
    }
}

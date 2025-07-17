import java.util.*;


/* Problem:
class Product{
    private String name;

    private double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public double getprice(){
        return price;
    }

    public void display(){
        System.out.println("Product:"+name+" price:"+price);
    }
}

class ProductBundle{
    List<Product> products;

    String bundleName;

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;

        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public double getprice(){
        double totalPrice = 0.0;

        for(Product prod: products){
            totalPrice += prod.getprice();
        }

        return totalPrice;
    }

    public void display(){
        System.out.println("Product Bundle:" + this.bundleName);
        
        for(Product prod: products){
            prod.display();
        }
    }
}

public class Composite {
    public static void main(String[] args) {
        Product book = new Product("Book1", 10);
        Product airpods = new Product("Airpods Pro 2", 200);
        Product ipad = new Product("ipad pro", 900);
        Product iphone = new Product("iphone 16", 1200);
        Product mac = new Product("macbook pro", 1200);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(mac);
        schoolKit.addProduct(ipad);
        schoolKit.addProduct(book);
        
        ProductBundle appleKit = new ProductBundle("Apple Kit");
        appleKit.addProduct(mac);
        appleKit.addProduct(airpods);
        appleKit.addProduct(iphone);
        appleKit.addProduct(ipad);

        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(schoolKit);
        cart.add(appleKit);
        cart.add(iphone);

        double totalCartPrice = 0;
        
        // Issue: As we are iterating on the object on client side,
        // client has to take an extra effort to differentiate different object types 
        // using "instance of" and typecast to invoke the methods.

        // This will be hard if we have large hierarical structure.
        

        for(Object item: cart){

            if(item instanceof Product){
                totalCartPrice += ((Product)item).getprice();
                ((Product)item).display();
            } else if(item instanceof ProductBundle){
                totalCartPrice += ((ProductBundle)item).getprice();
                ((ProductBundle)item).display();
            }
        }

        System.out.println("Total Price:"+totalCartPrice);
    }
}

*/

interface CartItem{
    double getprice();

    void display();
}

class Product implements CartItem{
    private String name;

    private double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public double getprice(){
        return price;
    }

    @Override
    public void display(){
        System.out.println("Product:"+name+" price:"+price);
    }
}

class ProductBundle implements CartItem{
    List<CartItem> bundle;

    String bundleName;

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;

        bundle = new ArrayList<>();
    }

    public void addProduct(CartItem item){
        bundle.add(item);
    }

    @Override
    public double getprice(){
        double totalPrice = 0.0;

        for(CartItem item: bundle){
            totalPrice += item.getprice();
        }

        return totalPrice;
    }

    @Override
    public void display(){
        System.out.println("Product Bundle:" + this.bundleName);
        
        for(CartItem item: bundle){
            item.display();
        }
    }
}


public class Composite{
    public static void main(String[] args) {
        Product book = new Product("Book1", 10);
        Product airpods = new Product("Airpods Pro 2", 200);
        Product ipad = new Product("ipad pro", 900);
        Product iphone = new Product("iphone 16", 1200);
        Product mac = new Product("macbook pro", 1200);
        Product appleCalculator = new Product("appleCalculator", 120);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(mac);
        schoolKit.addProduct(ipad);
        schoolKit.addProduct(book);
        
        ProductBundle appleKit = new ProductBundle("Apple Kit");
        appleKit.addProduct(mac);
        appleKit.addProduct(airpods);
        appleKit.addProduct(iphone);
        appleKit.addProduct(ipad);

        // ProductBundle nested with ProductBundle and Product
        ProductBundle appleEducationKit = new ProductBundle("Apple Education Kit");
        appleEducationKit.addProduct(appleKit);
        appleEducationKit.addProduct(book);
        appleEducationKit.addProduct(appleCalculator);

        List<CartItem> cart = new ArrayList<>();
        cart.add(book);
        cart.add(schoolKit);
        cart.add(appleKit);
        cart.add(iphone);
        cart.add(appleEducationKit);

        double totalCartPrice = 0;

        // Client is independent of all the different types and 
            // dependent only on abstraction

        // This why we can form any nested object of any depth.
        for(CartItem item: cart){
            totalCartPrice += item.getprice();
            item.display();
        }

        System.out.println("Total Price:"+totalCartPrice);
    }
}
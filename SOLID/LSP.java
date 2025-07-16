/*
LSP:
    - If S is subtype of T, then objects of type T can be substituted by objects of S,
        without altering the correctness of the program.

How to Spot LSP Violations?
To spot LSP violations, ask yourself these questions:

- Does the subclass override methods in a way that changes meaning or assumptions?
- Can I replace the base class with the subclass everywhere without side effects?
- Does the subclass throw unexpected exceptions or return wrong values?
- Does the subclass weaken any preconditions or strengthen postconditions?

If the answer to any of these questions is "yes", 
    there might be a LSP violation in the code.


Problem:

 class Rectangle {
    int width;

    int height;

    public Rectangle(int width, int height){
        this.width = width;

        this.height = height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int calculateArea(){
        return width * height;
    }

    public int calculatePerimeter(){
        return 2 * (height + width);
    }
 }

class Square extends Rectangle{
    private int side;

    public Square(int side){
        this.side = side;
    }

    @Override
    public void setHeight(int height){
        throw new UnsupportedOperationException("Square will have only side");
    }

    @Override
    public void setWidth(int width){
        throw new UnsupportedOperationException("Square will have only side");
    }

    @Override
    public int calculateArea(){
        return side * side;
    }

    public int calculatePerimeter(){
        return 4 * side;
    }
}

public class LSP {
    public static void main(String[] args) {
        Rectangle rectangle = new Square();

        // throws exception
        rectangle.setHeight(10);

        // throws exception
        rectangle.setWidth(5);
    }
}

 */

interface Shape{
   int calculateArea();

   int calculatePerimeter();
}

class Rectangle implements Shape{
    private int height;

    private int width;

   public Rectangle(int height, int width){
        this.height = height;
        this.width = width;
   }

    @Override
    public int calculateArea() {
        return height * width;
    }

    @Override
    public int calculatePerimeter() {
        return 2 * (height + width);
    }

}

class Square implements Shape{
    private int side;

    public Square(int side){
        this.side = side;
    }

    @Override
    public int calculateArea() {
        return side * side;
    }

    @Override
    public int calculatePerimeter() {
        return 4 * side;
    }

}

/* Example - 2 */
abstract class Notification{
    abstract void sendNotification();
}

class EmailNotification extends Notification{

    @Override
    void sendNotification() {
        System.out.println("Sending email notification");
    }

}

class TextNotification extends Notification{

    @Override
    void sendNotification() {
       System.out.println("Sending text notification");
    }

}

class WhatsAppNotification extends Notification{

    @Override
    void sendNotification() {
        System.out.println("Sending whatsapp notification");
    }

}


public class LSP{
    public static void main(String[] args) {
        Shape item = new Rectangle(10, 20);

        System.out.println(item.calculateArea());

        item = new Square(10);

        System.out.println(item.calculateArea());


        /* Example 2 testing */

        Notification notification = new TextNotification();

        // subclasses substitution without affecting the correctness of the code.
        // Notification notification = new EmailNotification();
        // Notification notification = new WhatsappNotification();

        notification.sendNotification();
    }
}



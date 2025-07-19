/*
Problem that Prototype Solves:
    - Simplifies the Complex object creation with similar data using cloning

When to use it:
 - Object creation is resource-intensive or complex.
 - You require many similar objects with slight variations.
 - You want to avoid writing repetitive initialization logic(following DRY principle).
 - You need runtime object creation without tight class coupling.

Basic Structure:
    - implements cloneable to class/ extends cloneable for the interface
    - override clone() method 
    - have a central registry for fetching the clone(like cache) --> best practice, not mandatory.

Cons:
    - Deep cloning can be hard(use it when shallow copy works)
    - Trouble with circular references during cloning
 */

import java.util.*;


interface EmailTemplate extends Cloneable{
    EmailTemplate clone();

    void send(String email);

    void setContent(String content);
}

class WelcomeEmail implements EmailTemplate{
    private String content;

    private String subject;

    public WelcomeEmail(){
        this.subject = "Welcome from TUF Plus";

        this.content = "content";
    }

    @Override
    public WelcomeEmail clone(){
        try{
            return (WelcomeEmail)super.clone();
        }catch(Exception ex){
            System.out.println("Exception while cloning" + ex.getMessage());

            throw new RuntimeException("Cloning exception");
        }
    }

    @Override
    public void send(String email) {
        System.out.println("Sending email to:"+email+" with subject:"+subject+" content:"+content);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}

class DiscountEmail implements EmailTemplate{
    private String content;

    private String subject;

    public DiscountEmail(){
        this.subject = "Discount for TUF Plus Subscription";

        this.content = "content";
    }

    @Override
    public DiscountEmail clone(){
        try{
            return (DiscountEmail)super.clone();
        }catch(Exception ex){
            System.out.println("Exception while cloning" + ex.getMessage());

            throw new RuntimeException("Cloning exception");
        }
    }

    @Override
    public void send(String email) {
        System.out.println("Sending email to:"+email+" with subject:"+subject+" content:"+content);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}



class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static{
        templates.put("welcome", new WelcomeEmail());
        templates.put("discount", new DiscountEmail());
    }

    public static EmailTemplate getTemplate(String key){
        return templates.get(key).clone();
    }
}

public class Prototype {
    public static void main(String[] args) {
        EmailTemplate email1 = EmailTemplateRegistry.getTemplate("welcome");
        email1.send("user1@abc.com");
        System.out.println(email1);
        
        EmailTemplate email2 = EmailTemplateRegistry.getTemplate("welcome");
        email2.setContent("Welcome to User2");
        email1.send("user2@abc.com");

        System.out.println(email2);
    }
}

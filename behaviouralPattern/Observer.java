import java.util.*;

/*
    Problem:
        - Tight Coupling of Subject and Observer in single class
    
    - Observer pattern:
        - decouples subject and observers using interface.
        - works for smaller scale 
            (for large-scale, prefer event-driven architecture like pub-sub, event streams)


import java.util.*;

class YouTubeChannel {
    
    // violating the SRP

    public void uploadNewVideo(String videoTitle) {
        // Upload the video
        System.out.println("Uploading: " + videoTitle + "\n");

        // Manually notify users
        System.out.println("Sending email to user1@example.com");
        System.out.println("Pushing in-app notification to user3@example.com");

        // tightcoupling makes it difficult to extend when we have to add whatsapp notifications.
    }
}

class Main {
    public static void main(String[] args) {
        // Create a channel and upload a new video
        YouTubeChannel channel = new YouTubeChannel();
        channel.uploadNewVideo("Design Patterns in Java");
    }
}

*/

// Abstract Observer
interface Subscriber{
    void update(String title);
}

// Concrete Observer
class EmailSubscriber implements Subscriber{
    private String email;

    public EmailSubscriber(String email){
        this.email = email;
    }

    @Override
    public void update(String title) {
        System.out.println("Updating the user over email:"+title + " to user:"+email);
    }

}

// Concrete Observer
class MobileSubscriber implements Subscriber{
    private String phone;

    public MobileSubscriber(String phone){
        this.phone = phone;
    }

    @Override
    public void update(String title) {
        System.out.println("Updating the user over mobile:"+title+" to contact:"+phone);
    }

}

// Abstract Subject
interface Channel{
    void subscribe(Subscriber subscriber);

    void unSubscribe(Subscriber subscriber);

    void notifySubscribers(String title);
    
    void uploadVideo(String title);
}

// Concrete Subject
class YoutubeChannel implements Channel{
    private List<Subscriber> subscribers;

    public YoutubeChannel(){
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
       this.subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String title) {
       for(Subscriber subscriber : subscribers){
        subscriber.update(title);
       }
    }

    @Override
    public void uploadVideo(String title){
        System.out.println("Video uploaded:"+title);
        notifySubscribers(title);
    }

}


public class Observer {
    public static void main(String[] args) {
        Subscriber emailSubscriber1 = new EmailSubscriber("user1@abc.com");

        Subscriber emailSubscriber2 = new EmailSubscriber("user2@abc.com");

        Subscriber mobileSubcriber = new MobileSubscriber("123123123");

        Channel channel1 = new YoutubeChannel();
        channel1.subscribe(emailSubscriber2);
        channel1.subscribe(mobileSubcriber);

        Channel channel2 = new YoutubeChannel();
        channel2.subscribe(emailSubscriber1);
        channel2.subscribe(emailSubscriber2);

        channel1.uploadVideo("Video in Channel 1");
        channel2.uploadVideo("Video in Channel 2");
    }   
}

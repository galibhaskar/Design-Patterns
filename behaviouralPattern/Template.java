/*

Problem:
    - Flow of the events cannot be enforced using interface methods.
    - Template Pattern enforces the order using
        - final template method(defines the order)
        - private or final methods for internal methods.
        - abstract methods that needs to be defined.

interface NotificationSystem{
    void sendMessage(String message, String to);
}

class EmailNotification implements NotificationSystem{

    @Override
    public void sendMessage(String message, String to) {
        System.out.println("Checking rate limits");

        System.out.println("Validating the email:"+to);

        String formatted = message.trim();

        System.out.println("Logging before send:"+ formatted+" to:"+to);

        String composedMessage = "[Email]:"+formatted;

        System.out.println("Sending email to :"+ to+ " content:"+composedMessage);

        System.out.println("Reporting analytics...");
    }

}

class SMSNotification implements NotificationSystem{

    @Override
    public void sendMessage(String message, String to) {
        System.out.println("Checking rate limits");

        System.out.println("Validating the phone:"+to);

        String formatted = message.trim();

        System.out.println("Logging before send:"+ formatted+" to:"+to);

        String composedMessage = "[SMS]:"+formatted;

        System.out.println("Sending SMS to :"+ to+ " content:"+composedMessage);

        System.out.println("Reporting custom SMS analytics...");
    }

}

class WANotification implements NotificationSystem{

    @Override
    public void sendMessage(String message, String to) {
        // should take care of enforcing the checks in the similar order like SMS and Email.
            // Will be difficult to enforce the order.
    }

}
*/

abstract class NotificationSender{
    public final void sendMessage(String message, String to) {
        checkRateLimits(to);
        
        validateReceipient(to);

        String formatted = message.trim();

        presendAuditLog(formatted, to);

        String composedMessage = composeMessage(formatted);

        send(composedMessage, to);

        postSendAnalytics(to);
    }

    private void checkRateLimits(String to){
        System.out.println("Checking rate limits");
    }

    private void validateReceipient(String to){
        System.out.println("Validating the receipient:"+to);
    }

    private void presendAuditLog(String message, String to){
        System.out.println("Logging before send:"+ message+" to:"+to);
    }

    public abstract String composeMessage(String message);

    public abstract void send(String message, String to);

    protected void postSendAnalytics(String to){
        System.out.println("Reporting analytics...");
    }
}

class EmailNotificationSender extends NotificationSender{

    @Override
    public String composeMessage(String message) {
        return "[Email]:" + message;
    }

    @Override
    public void send(String message, String to) {
        System.out.println("Sending email to :"+ to+ " content:"+message);
    }

}

class SMSNotificationSender extends NotificationSender{

    @Override
    public String composeMessage(String message) {
        return "[SMS]:" + message;
    }

    @Override
    public void send(String message, String to) {
        System.out.println("Sending SMS to :"+ to+ " content:"+message);
    }

}

public class Template {
    public static void main(String[] args) {
        NotificationSender notificationSender = new EmailNotificationSender();
        
        String message = notificationSender.composeMessage("Welcome to Template Pattern");
        
        notificationSender.send(message, "user1@abc.com");

        NotificationSender notificationSender2 = new SMSNotificationSender();

        String message2 = notificationSender2.composeMessage("SMS message");

        notificationSender2.send(message2, "123123123");
    }
}

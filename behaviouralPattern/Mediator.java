
/*
    Usecase:
        - Objects shouldn't communicate directly with each other,
            they communicate with a centralized resource(mediator)
    
    (ATC-Air traffic Control, Chat Server in Messaging App)
 */

import java.util.*;

interface ChatMediator{
    void registerUser(User user);
    
    void sendMessage(String message, User sender, String receiverName);

    void broadcastMessage(String message, User sender);
}

class ChatServer implements ChatMediator{
    private Map<String, User> users;

    public ChatServer(){
        this.users = new HashMap<>();
    }

    @Override
    public void sendMessage(String message, User sender, String receiverName) {
        User receiver = users.get(receiverName);

        if(receiver != null && receiver != sender){
            receiver.receive(message, sender.getName());
        }
    }

    @Override
    public void broadcastMessage(String message, User sender) {
        for(User user: users.values()){
            if(user != sender){
                user.receive(message, sender.getName());
            }
        }
    }

    @Override
    public void registerUser(User user) {
        users.put(user.getName(), user);
    }

}

interface User{
    void send(String message, String receiverName);
    
    void receive(String message, String senderName);

    void broadcastMessage(String message);

    String getName();
}

class ChatUser implements User{
    private String name;

    private ChatServer server;

    public ChatUser(String name, ChatServer server){
        this.name = name;

        this.server = server;
    }

    @Override
    public void send(String message, String receiverName) {
        this.server.sendMessage(message, this, receiverName);
    }

    @Override
    public void receive(String message, String senderName) {
        System.out.println("=========Chat of user:"+name+"==============");
        System.out.println(senderName + ":"+message);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void broadcastMessage(String message) {
        this.server.broadcastMessage(message, this);
    }

}

public class Mediator {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User user1 = new ChatUser("user-1", server);
        User user2 = new ChatUser("user-2", server);
        User user3 = new ChatUser("user-3", server);

        server.registerUser(user1);
        server.registerUser(user2);
        server.registerUser(user3);

        user1.send("Hello from user-1", "user-2");
        user2.send("Hello from user-2", "user-1");

        user3.broadcastMessage("Hello from user-3");
    }
}

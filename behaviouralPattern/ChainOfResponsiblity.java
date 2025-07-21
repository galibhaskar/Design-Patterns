/*
    Problem:
        - Assign responsibilities in the run time dynamically by passing the request to the handler in the chain.
    
    - Similar to middleware concept
        (RateLimitHandler -> AuthHandler -> AuthorizationHandler -> ServiceHandler ->...)
 */

class Request{
    private String name;

    private String type;

    public Request(String name, String type){
        this.name = name;

        this.type = type;
    }

    public String getRequestType(){
        return type;
    }

    public String getRequestName(){
        return name;
    }
}

abstract class Handler{
   protected Handler nextHandler;

   public void setNextHandler(Handler handler){
    this.nextHandler = handler;
   }

   public void validate(Request request){
        if(handleRequest(request)){
            if(nextHandler != null){
                System.out.println("Passing request to next handler...");

                nextHandler.validate(request);
            } else {
                System.out.println("End of chain");
            }
        } else {
            System.out.println("Request validation failed...");
        }
   }

   abstract boolean handleRequest(Request request);
}

class RateLimitHandler extends Handler{

    @Override
    boolean handleRequest(Request request) {
        System.out.println("Checking request:"+request.getRequestName()+" for rate limiting...");

        // if(ratelimited) return false;

        return true;
    }

}

class AuthHandler extends Handler{

    @Override
    boolean handleRequest(Request request) {
        System.out.println("Checking request:"+request.getRequestName()+" for authentication...");

        // if(auth failed) return false;

        return true;
    }

}

class AuthorizationHandler extends Handler{

    @Override
    boolean handleRequest(Request request) {
        System.out.println("Checking request:"+request.getRequestName()+" for authorization...");

        // if(auth failed) return false;

        return true;
    }

}


public class ChainOfResponsiblity {
    public static void main(String[] args) {
        Request request1 = new Request("Request-1", "User Request");

        Handler ratelimitHandler = new RateLimitHandler();
        Handler authHandler = new AuthHandler();
        Handler authorizationHandler = new AuthorizationHandler();

        ratelimitHandler.setNextHandler(authHandler);
        authHandler.setNextHandler(authorizationHandler);

        ratelimitHandler.validate(request1);
    }
}

/*
Problem that Singleton Solves:
    Create only one instance across the application

When to use(Pros):
    - Dealing with Global & Shared resources - DB connections, logging instances, ...

Cons:
    - Violates SRP
    - Difficult to test & extensibility due to tight coupling(look for alternative like dependency injection, if required)
    - Used with parameters, often confused with factory pattern.
*/

class JudgeAnalytics{
    // eager loading: thread safe
        // problem: object is created when class is loaded, even though singleton object is not used anywhere wasting resources.
    // private static final JudgeAnalytics judgeAnalyticsObj = new JudgeAnalytics();

    // lazy loading: not thread safe without locking
        // volatile: stating read the data from main memory instead of cache, ensuring all threads have updated instance data.
    // private static volatile JudgeAnalytics judgeAnalyticsObj;

    private static int runClicks;

    // restricting other classes not to use default constructor.
    private JudgeAnalytics(){}

    public int getClicks(){
        return runClicks;
    }

    public void incrementClick(){
        runClicks++;
    }

    // Creating Singleton Instance Methods in Multi-threading Environment:
    // // Solution 1: synchronized keyword for thread safety
        // cons: even though instance is created to read the instance, locking is involved.
    // public synchronized static JudgeAnalytics getInstance(){
    //     if(judgeAnalyticsObj == null){
    //         judgeAnalyticsObj = new JudgeAnalytics();
    //     }

    //     return judgeAnalyticsObj;
    // }

    // Solution-2: Double check synchronized keyword for thread safety
    // public static JudgeAnalytics getInstance(){
    //     // first check: to check if instance is already created or not, if created, don't take any lock. 
    //     if(judgeAnalyticsObj == null){
    //         synchronized(JudgeAnalytics.class){
    //             // 2nd check: preventing other threads not to enter this block
    //             if(judgeAnalyticsObj == null){
    //                 judgeAnalyticsObj = new JudgeAnalytics();
    //             }
    //         }
    //     }

    //     return judgeAnalyticsObj;
    // }


    // Solution-3: Bill Pugh Singleton
    // inner classes attributes are not loaded during class loading until referenced, preventing unnecesary judge analytics object creation 
    private static class Holder{
        // eager loading happens when inner class - Holder is referenced, 
            // but provides feel as Lazy loading,
                // which is thread-safe without synchronized & volatile.
        private static final JudgeAnalytics judgeAnalyticsObj = new JudgeAnalytics();
    }

    public static JudgeAnalytics getInstance(){
        // inner class loading happens when we reference the static class attributes or methods.
        // thread safe and solves the problem with eager loading in main class.
        return Holder.judgeAnalyticsObj;
    }
};

public class Singleton {
    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics1 = JudgeAnalytics.getInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();
     
        judgeAnalytics1.incrementClick();
        judgeAnalytics2.incrementClick();

        System.out.println(judgeAnalytics1);
        System.out.println(judgeAnalytics2);

       System.out.println(judgeAnalytics1.getClicks());
    }
}

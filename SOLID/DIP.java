/*
DIP: 
    - High-level modules(Recommendation Algo) shouldn't depend on low-level modules(Recommendation Strategies).
    - Both should depend on abstraction.
    - Abstraction(RecommendationStrategy) shouldn't depend on details.
    - Details(Genre, TrendingNow,..) should depend on abstractions

 */

interface RecommendationStrategy{
    void getRecommendations();
}


class RecentlyAddedStrategy implements RecommendationStrategy{

    @Override
    public void getRecommendations() {
        
    }

}

class TrendingNowStrategy implements RecommendationStrategy{

    @Override
    public void getRecommendations() {
        
    }

}

class GenreBasedStrategy implements RecommendationStrategy{

    @Override
    public void getRecommendations() {
        
    }

}

class RecommendationAlgorithm{
    RecommendationStrategy strategy;
    
    public RecommendationAlgorithm(RecommendationStrategy strategy){
        this.strategy = strategy;
    }

    public void recommend(){
        this.strategy.getRecommendations();
    }
}



public class DIP {
    public static void main(String[] args) {
        RecommendationAlgorithm algorithm = new RecommendationAlgorithm(new GenreBasedStrategy());

        algorithm.recommend();
    }
}

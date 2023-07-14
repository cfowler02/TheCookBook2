package TheCookBook2.activity.results;

import java.util.HashMap;
import java.util.Map;

public class RateFoodRecipeResult {
    private final Map<String, Integer> ratings;

    public RateFoodRecipeResult(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "RateFoodRecipeResult{" +
                "ratings=" + ratings +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static RateFoodRecipeResult.Builder builder() {
        return new RateFoodRecipeResult.Builder();
    }
    public static class Builder {
        private Map<String, Integer> ratings;
        public RateFoodRecipeResult.Builder withRatings(Map<String, Integer> ratings) {
            this.ratings = new HashMap<>(ratings);
            return this;
        }
        public RateFoodRecipeResult build() {
            return new RateFoodRecipeResult(ratings);
        }
    }
}

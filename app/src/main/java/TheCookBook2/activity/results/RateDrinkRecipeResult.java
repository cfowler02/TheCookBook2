package TheCookBook2.activity.results;

import java.util.HashMap;
import java.util.Map;

public class RateDrinkRecipeResult {
    private final Map<String, Integer> ratings;

    public RateDrinkRecipeResult(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "RateDrinkRecipeResult{" +
                "ratings=" + ratings +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static RateDrinkRecipeResult.Builder builder() {
        return new RateDrinkRecipeResult.Builder();
    }
    public static class Builder {
        private Map<String, Integer> ratings;
        public RateDrinkRecipeResult.Builder withRatings(Map<String, Integer> ratings) {
            this.ratings = new HashMap<>(ratings);
            return this;
        }
        public RateDrinkRecipeResult build() {
            return new RateDrinkRecipeResult(ratings);
        }
    }
}

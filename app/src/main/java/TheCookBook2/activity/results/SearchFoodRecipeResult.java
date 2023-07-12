package TheCookBook2.activity.results;

import TheCookBook2.models.FoodRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFoodRecipeResult {
    private final List<FoodRecipeModel> foodRecipeModels;

    private SearchFoodRecipeResult(List<FoodRecipeModel> foodRecipeModels) {
        this.foodRecipeModels = foodRecipeModels;
    }

    public List<FoodRecipeModel> getFoodRecipeModels() {
        return foodRecipeModels;
    }

    @Override
    public String toString() {
        return "SearchFoodRecipeResult{" +
                "foodRecipeModels=" + foodRecipeModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchFoodRecipeResult.Builder builder() {
        return new SearchFoodRecipeResult.Builder();
    }

    public static class Builder {
        private List<FoodRecipeModel> foodRecipeModels ;

        public SearchFoodRecipeResult.Builder withFoodRecipeModels(List<FoodRecipeModel> foodRecipeModels) {
            this.foodRecipeModels = new ArrayList<>(foodRecipeModels);
            return this;
        }

        public SearchFoodRecipeResult build() {
            return new SearchFoodRecipeResult(foodRecipeModels);
        }
    }
}

package TheCookBook2.activity.results;

import TheCookBook2.models.FoodRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class ViewFoodRecipeResult {
    private final List<FoodRecipeModel> foodRecipeModels;

    private ViewFoodRecipeResult(List<FoodRecipeModel> foodRecipeModels) {
        this.foodRecipeModels = foodRecipeModels;
    }

    public List<FoodRecipeModel> getFoodRecipeModels() {
        return foodRecipeModels;
    }

    @Override
    public String toString() {
        return "ViewFoodRecipeResult{" +
                "foodRecipeModels=" + foodRecipeModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static ViewFoodRecipeResult.Builder builder() {
        return new ViewFoodRecipeResult.Builder();
    }

    public static class Builder {
        private List<FoodRecipeModel> foodRecipeModels ;

        public ViewFoodRecipeResult.Builder withFoodRecipeModels(List<FoodRecipeModel> foodRecipeModels) {
            this.foodRecipeModels = new ArrayList<>(foodRecipeModels);
            return this;
        }

        public ViewFoodRecipeResult build() {
            return new ViewFoodRecipeResult(foodRecipeModels);
        }
    }
}

package TheCookBook2.activity.results;

import TheCookBook2.models.FoodRecipeModel;

public class DeleteFoodRecipeResult {
    private final FoodRecipeModel foodRecipeModel;

    public DeleteFoodRecipeResult(FoodRecipeModel foodRecipeModel) {
        this.foodRecipeModel = foodRecipeModel;
    }

    public FoodRecipeModel getFoodRecipeModel() {
        return foodRecipeModel;
    }

    @Override
    public String toString() {
        return "DeleteFoodRecipeResult{" +
                "foodRecipeModel=" + foodRecipeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FoodRecipeModel foodRecipeModel;

        public Builder withFoodRecipe(FoodRecipeModel foodRecipeModel) {
            this.foodRecipeModel = foodRecipeModel;
            return this;
        }

        public DeleteFoodRecipeResult build() {
            return new DeleteFoodRecipeResult(foodRecipeModel);
        }
    }
}

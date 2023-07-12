package TheCookBook2.activity.results;

import TheCookBook2.models.FoodRecipeModel;

public class GetFoodRecipeResult {

    private final FoodRecipeModel foodRecipeModel;

    private GetFoodRecipeResult(FoodRecipeModel foodRecipeModel) {
        this.foodRecipeModel = foodRecipeModel;
    }

    public FoodRecipeModel getFoodRecipe() {
        return foodRecipeModel;
    }

    @Override
    public String toString() {
        return "GetFoodRecipeResult{" +
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

        public GetFoodRecipeResult build() {
            return new GetFoodRecipeResult(foodRecipeModel);
        }
    }
}

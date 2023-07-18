package TheCookBook2.activity.results;

import TheCookBook2.models.DrinkRecipeModel;

public class DeleteDrinkRecipeResult {
    private final DrinkRecipeModel drinkRecipeModel;

    public DeleteDrinkRecipeResult(DrinkRecipeModel drinkRecipeModel) {
        this.drinkRecipeModel = drinkRecipeModel;
    }

    public DrinkRecipeModel getDrinkRecipeModel() {
        return drinkRecipeModel;
    }

    @Override
    public String toString() {
        return "DeleteDrinkRecipeResult{" +
                "drinkRecipeModel=" + drinkRecipeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DrinkRecipeModel drinkRecipeModel;

        public Builder withFoodRecipe(DrinkRecipeModel drinkRecipeModel) {
            this.drinkRecipeModel = drinkRecipeModel;
            return this;
        }

        public DeleteDrinkRecipeResult build() {
            return new DeleteDrinkRecipeResult(drinkRecipeModel);
        }
    }
}

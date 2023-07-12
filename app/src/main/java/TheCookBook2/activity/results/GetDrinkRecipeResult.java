package TheCookBook2.activity.results;

import TheCookBook2.models.DrinkRecipeModel;

public class GetDrinkRecipeResult {

    private final DrinkRecipeModel drinkRecipeModel;

    private GetDrinkRecipeResult(DrinkRecipeModel drinkRecipeModel) {
        this.drinkRecipeModel = drinkRecipeModel;
    }

    public DrinkRecipeModel getDrinkRecipe() {
        return drinkRecipeModel;
    }

    @Override
    public String toString() {
        return "GetDrinkRecipeResult{" +
                "drinkRecipeModel=" + drinkRecipeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DrinkRecipeModel drinkRecipeModel;

        public Builder withDrinkRecipe(DrinkRecipeModel drinkRecipeModel) {
            this.drinkRecipeModel = drinkRecipeModel;
            return this;
        }

        public GetDrinkRecipeResult build() {
            return new GetDrinkRecipeResult(drinkRecipeModel);
        }
    }
}

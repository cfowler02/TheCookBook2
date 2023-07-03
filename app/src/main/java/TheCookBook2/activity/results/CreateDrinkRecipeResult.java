package TheCookBook2.activity.results;

import TheCookBook2.models.DrinkRecipeModel;

public class CreateDrinkRecipeResult {
    private final DrinkRecipeModel drinkRecipe;

    private CreateDrinkRecipeResult(DrinkRecipeModel drinkRecipe) {
        this.drinkRecipe = drinkRecipe;
    }

    public DrinkRecipeModel getDrinkRecipe() {
        return drinkRecipe;
    }

    @Override
    public String toString() {
        return "CreateDrinkRecipeResult{" +
                "drinkRecipe=" + drinkRecipe +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DrinkRecipeModel drinkRecipe;

        public Builder withDrinkRecipe(DrinkRecipeModel drinkRecipe) {
            this.drinkRecipe = drinkRecipe;
            return this;
        }

        public CreateDrinkRecipeResult build() {
            return new CreateDrinkRecipeResult(drinkRecipe);
        }
    }
}

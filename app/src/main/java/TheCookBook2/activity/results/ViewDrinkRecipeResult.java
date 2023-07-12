package TheCookBook2.activity.results;

import TheCookBook2.models.DrinkRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class ViewDrinkRecipeResult {
    private final List<DrinkRecipeModel> drinkRecipeModels;

    private ViewDrinkRecipeResult(List<DrinkRecipeModel> drinkRecipeModels) {
        this.drinkRecipeModels = drinkRecipeModels;
    }

    public List<DrinkRecipeModel> getDrinkRecipeModels() {
        return drinkRecipeModels;
    }

    @Override
    public String toString() {
        return "ViewDrinkRecipeResult{" +
                "drinkRecipeModels=" + drinkRecipeModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static ViewDrinkRecipeResult.Builder builder() {
        return new ViewDrinkRecipeResult.Builder();
    }

    public static class Builder {
        private List<DrinkRecipeModel> drinkRecipeModels ;

        public ViewDrinkRecipeResult.Builder withDrinkRecipeModels(List<DrinkRecipeModel> drinkRecipeModels) {
            this.drinkRecipeModels = new ArrayList<>(drinkRecipeModels);
            return this;
        }

        public ViewDrinkRecipeResult build() {
            return new ViewDrinkRecipeResult(drinkRecipeModels);
        }
    }
}

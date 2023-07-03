package TheCookBook2.converters;

import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.DrinkRecipeModel;
import TheCookBook2.models.FoodRecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    public FoodRecipeModel toFoodRecipeModel(FoodRecipe foodRecipe) {
        return FoodRecipeModel.builder()
                .withCreator(foodRecipe.getCreator())
                .withRecipeTitle(foodRecipe.getRecipeTitle())
                .withIngredients(foodRecipe.getIngredients())
                .withInstructionSteps(foodRecipe.getInstructionSteps())
                .withDescription(foodRecipe.getDescription())
                .withDescriptionTags(foodRecipe.getDescriptionTags())
                .withTimeEstimate(foodRecipe.getTimeEstimate())
                .withFoodCategory(foodRecipe.getFoodCategory())
                .withFoodItem(foodRecipe.getFoodItem())
                .withAllergies(foodRecipe.getAllergies())
                .withRatings(foodRecipe.getRatings())
                .build();
    }

    public List<FoodRecipeModel> toFoodRecipeModelList(List<FoodRecipe> foodRecipes) {
        List<FoodRecipeModel> foodRecipeModels = new ArrayList<>();

        for (FoodRecipe f : foodRecipes) {
            foodRecipeModels.add(toFoodRecipeModel(f));
        }

        return foodRecipeModels;
    }

    public DrinkRecipeModel toDrinkRecipeModel(DrinkRecipe drinkRecipe) {
        return DrinkRecipeModel.builder()
                .withCreator(drinkRecipe.getCreator())
                .withRecipeTitle(drinkRecipe.getRecipeTitle())
                .withIngredients(drinkRecipe.getIngredients())
                .withInstructionSteps(drinkRecipe.getInstructionSteps())
                .withDescription(drinkRecipe.getDescription())
                .withDescriptionTags(drinkRecipe.getDescriptionTags())
                .withDrinkCategory(drinkRecipe.getDrinkCategory())
                .withDrinkItem(drinkRecipe.getDrinkItem())
                .withAllergies(drinkRecipe.getAllergies())
                .withRatings(drinkRecipe.getRatings())
                .build();
    }

    public List<DrinkRecipeModel> toDrinkRecipeModelList(List<DrinkRecipe> drinkRecipes) {
        List<DrinkRecipeModel> drinkRecipeModels = new ArrayList<>();

        for (DrinkRecipe d : drinkRecipes) {
            drinkRecipeModels.add(toDrinkRecipeModel(d));
        }

        return drinkRecipeModels;
    }
}

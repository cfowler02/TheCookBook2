package TheCookBook2.test.helper;

import TheCookBook2.dynamodb.models.DrinkRecipe;

import java.util.*;

public class DrinkRecipeTestHelper {
    public static DrinkRecipe generateDrinkRecipe() {
        DrinkRecipe drinkRecipe = new DrinkRecipe();
        drinkRecipe.setCreator("creator");
        drinkRecipe.setRecipeTitle("recipe title");
        Map<String, String> ingredients = new HashMap<>();
        ingredients.put("ingredient", "amount");
        drinkRecipe.setIngredients(ingredients);
        LinkedList<String> instructionSteps = new LinkedList<>();
        instructionSteps.add("step 1");
        drinkRecipe.setInstructionSteps(instructionSteps);
        drinkRecipe.setDescription("description");
        Set<String> descriptionTags = new HashSet<>();
        descriptionTags.add("description tag");
        drinkRecipe.setDescriptionTags(descriptionTags);
        drinkRecipe.setDrinkCategory("drink category");
        drinkRecipe.setDrinkItem("drink item");
        Set<String> allergies = new HashSet<>();
        drinkRecipe.setAllergies(allergies);
        Map<String, Integer> ratings = new HashMap<>();
        drinkRecipe.setRatings(ratings);

        return drinkRecipe;
    }
}

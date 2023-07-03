package TheCookBook2.test.helper;

import com.amazonaws.services.dynamodbv2.xspec.S;
import TheCookBook2.dynamodb.models.FoodRecipe;

import java.util.*;

public class FoodRecipeTestHelper {
    public static FoodRecipe generateFoodRecipe() {
        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator("creator");
        foodRecipe.setRecipeTitle("recipe title");
        Map<String, String> ingredients = new HashMap<>();
        ingredients.put("ingredient", "amount");
        foodRecipe.setIngredients(ingredients);
        LinkedList<String> instructionSteps = new LinkedList<>();
        instructionSteps.add("step 1");
        foodRecipe.setInstructionSteps(instructionSteps);
        foodRecipe.setDescription("description");
        Set<String> descriptionTags = new HashSet<>();
        descriptionTags.add("description tag");
        foodRecipe.setDescriptionTags(descriptionTags);
        foodRecipe.setTimeEstimate(1);
        foodRecipe.setFoodCategory("food category");
        foodRecipe.setFoodItem("food item");
        Set<String> allergies = new HashSet<>();
        foodRecipe.setAllergies(allergies);
        Map<Integer, Integer> ratings = new HashMap<>();
        ratings.put(-1, 0);
        ratings.put(0, 0);
        ratings.put(1, 0);
        foodRecipe.setRatings(ratings);

        return foodRecipe;
    }
}

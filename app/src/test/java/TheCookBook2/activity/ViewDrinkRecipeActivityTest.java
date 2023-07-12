package TheCookBook2.activity;

import TheCookBook2.activity.requests.ViewDrinkRecipeRequest;
import TheCookBook2.activity.results.ViewDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.models.DrinkRecipeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ViewDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;

    private ViewDrinkRecipeActivity viewDrinkRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        viewDrinkRecipeActivity = new ViewDrinkRecipeActivity(drinkRecipeDao);
    }

    @Test
    public void handleRequest_returnsRecipeModelListInResult() {
        // GIVEN
        Map<String, String> ingredients1 = new HashMap<>();
        ingredients1.put("butter", "as much as you want");
        Map<String, String> ingredients2 = new HashMap<>();
        ingredients2.put("Bread", "two slices");
        ingredients2.put("Cheese slice", "2-4");
        Set<String> descriptionTags1 = new HashSet<>();
        descriptionTags1.add("weird");
        Set<String> descriptionTags2 = new HashSet<>();
        descriptionTags2.add("easy");
        descriptionTags2.add("classic");


        List<DrinkRecipe> expected = List.of(
                newRecipe("creator", "butter soup", ingredients1, descriptionTags1, "American",
                        "Butter soup"), newRecipe("creator", "Grilled Cheese" , ingredients2,
                        descriptionTags2, "American", "Grilled Cheese"));

        when(drinkRecipeDao.viewDrinkRecipe()).thenReturn(expected);

        ViewDrinkRecipeRequest request = ViewDrinkRecipeRequest.builder()
                .build();

        // WHEN
        ViewDrinkRecipeResult result = viewDrinkRecipeActivity.handleRequest(request);

        // THEN
        List<DrinkRecipeModel> resultRecipes = result.getDrinkRecipeModels();
        assertEquals(expected.size(), resultRecipes.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCreator(), resultRecipes.get(i).getCreator());
            assertEquals(expected.get(i).getRecipeTitle(), resultRecipes.get(i).getRecipeTitle());
            assertEquals(expected.get(i).getIngredients(), resultRecipes.get(i).getIngredients());
            assertEquals(expected.get(i).getInstructionSteps(), resultRecipes.get(i).getInstructionSteps());
            assertEquals(expected.get(i).getDescription(), resultRecipes.get(i).getDescription());
            assertEquals(expected.get(i).getDescriptionTags(), resultRecipes.get(i).getDescriptionTags());
            assertEquals(expected.get(i).getDrinkCategory(), resultRecipes.get(i).getDrinkCategory());
            assertEquals(expected.get(i).getDrinkItem(), resultRecipes.get(i).getDrinkItem());
            assertEquals(expected.get(i).getAllergies(), resultRecipes.get(i).getAllergies());
            assertEquals(expected.get(i).getRatings(), resultRecipes.get(i).getRatings());
        }
    }


    private static DrinkRecipe newRecipe(String creator, String recipeTitle, Map<String, String> ingredients,
                                         Set<String> descriptionTags, String drinkCategory, String drinkItem){
        DrinkRecipe drinkRecipe = new DrinkRecipe();

        drinkRecipe.setCreator(creator);
        drinkRecipe.setRecipeTitle(recipeTitle);
        drinkRecipe.setIngredients(ingredients);
        drinkRecipe.setDescriptionTags(descriptionTags);
        drinkRecipe.setDrinkCategory(drinkCategory);
        drinkRecipe.setDrinkItem(drinkItem);
        drinkRecipe.setDescription("a generic description");
        LinkedList<String> instructions = new LinkedList<>();
        instructions.add("buy takeout");
        drinkRecipe.setInstructionSteps(instructions);
        Map<Integer, Integer> ratings = new HashMap<>();
        ratings.put(-1,0);
        ratings.put(0,0);
        ratings.put(1,0);
        drinkRecipe.setRatings(ratings);
        drinkRecipe.setAllergies(null);

        return drinkRecipe;
    }

}

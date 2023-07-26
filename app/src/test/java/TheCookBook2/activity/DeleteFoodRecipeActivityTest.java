package TheCookBook2.activity;

import TheCookBook2.activity.requests.DeleteFoodRecipeRequest;
import TheCookBook2.activity.requests.GetFoodRecipeRequest;
import TheCookBook2.activity.results.DeleteFoodRecipeResult;
import TheCookBook2.activity.results.GetFoodRecipeResult;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;

    private DeleteFoodRecipeActivity deleteFoodRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteFoodRecipeActivity = new DeleteFoodRecipeActivity(foodRecipeDao);
    }

    @Test
    public void handleRequest_savedFoodRecipeFound_deletesFoodRecipe(){
        // GIVEN
        String expectedCreator = "expectedCreator";
        String expectedRecipeTitle = "expectedRecipeTitle";
        Map<String, String> expectedIngredients = new HashMap();
        expectedIngredients.put("expectedIngredient", "expectedIngredientAmount");
        LinkedList<String> expectedInstructionSteps = new LinkedList<>();
        expectedInstructionSteps.add("expectedStep");
        String expectedDescription = "expectedDescription";
        Set<String> expectedDescriptionTags = new HashSet<>();
        expectedDescriptionTags.add("expectedTag");
        int expectedTimeEstimate = 1;
        String expectedFoodCategory = "expectedFoodCategory";
        String expectedFoodItem = "expectedFoodItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 0);

        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator(expectedCreator);
        foodRecipe.setRecipeTitle(expectedRecipeTitle);
        foodRecipe.setIngredients(expectedIngredients);
        foodRecipe.setInstructionSteps(expectedInstructionSteps);
        foodRecipe.setDescription(expectedDescription);
        foodRecipe.setDescriptionTags(expectedDescriptionTags);
        foodRecipe.setTimeEstimate(expectedTimeEstimate);
        foodRecipe.setFoodCategory(expectedFoodCategory);
        foodRecipe.setFoodItem(expectedFoodItem);
        foodRecipe.setAllergies(expectedAllergies);
        foodRecipe.setRatings(expectedRatings);

        when(foodRecipeDao.getFoodRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(foodRecipe);

        DeleteFoodRecipeRequest request = DeleteFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .build();

        // WHEN
        DeleteFoodRecipeResult result = deleteFoodRecipeActivity.handleRequest(request);


        // THEN
        assertEquals(expectedCreator, result.getFoodRecipeModel().getCreator());
        assertEquals(expectedRecipeTitle, result.getFoodRecipeModel().getRecipeTitle());
        assertEquals(expectedIngredients, result.getFoodRecipeModel().getIngredients());
        assertEquals(expectedInstructionSteps, result.getFoodRecipeModel().getInstructionSteps());
        assertEquals(expectedDescription, result.getFoodRecipeModel().getDescription());
        assertEquals(expectedDescriptionTags, result.getFoodRecipeModel().getDescriptionTags());
        assertEquals(expectedTimeEstimate, result.getFoodRecipeModel().getTimeEstimate());
        assertEquals(expectedFoodCategory, result.getFoodRecipeModel().getFoodCategory());
        assertEquals(expectedFoodItem, result.getFoodRecipeModel().getFoodItem());
        assertEquals(expectedAllergies, result.getFoodRecipeModel().getAllergies());
        assertEquals(expectedRatings, result.getFoodRecipeModel().getRatings());
    }
}

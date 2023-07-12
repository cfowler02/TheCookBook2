package TheCookBook2.activity;

import TheCookBook2.activity.requests.GetFoodRecipeRequest;
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

public class GetFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;

    private GetFoodRecipeActivity getFoodRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getFoodRecipeActivity = new GetFoodRecipeActivity(foodRecipeDao);
    }

    @Test
    public void handleRequest_savedFoodRecipeFound_returnsFoodRecipeModelInResult() {
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
        Map<Integer, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put(-1, 0);
        expectedRatings.put(0, 1);
        expectedRatings.put(1, 2);

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

        GetFoodRecipeRequest request = GetFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .build();

        // WHEN
        GetFoodRecipeResult result = getFoodRecipeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedCreator, result.getFoodRecipe().getCreator());
        assertEquals(expectedRecipeTitle, result.getFoodRecipe().getRecipeTitle());
        assertEquals(expectedIngredients, result.getFoodRecipe().getIngredients());
        assertEquals(expectedInstructionSteps, result.getFoodRecipe().getInstructionSteps());
        assertEquals(expectedDescription, result.getFoodRecipe().getDescription());
        assertEquals(expectedDescriptionTags, result.getFoodRecipe().getDescriptionTags());
        assertEquals(expectedTimeEstimate, result.getFoodRecipe().getTimeEstimate());
        assertEquals(expectedFoodCategory, result.getFoodRecipe().getFoodCategory());
        assertEquals(expectedFoodItem, result.getFoodRecipe().getFoodItem());
        assertEquals(expectedAllergies, result.getFoodRecipe().getAllergies());
        assertEquals(expectedRatings, result.getFoodRecipe().getRatings());
    }
}
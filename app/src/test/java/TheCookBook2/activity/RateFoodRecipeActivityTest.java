package TheCookBook2.activity;

import TheCookBook2.activity.requests.RateDrinkRecipeRequest;
import TheCookBook2.activity.requests.RateFoodRecipeRequest;
import TheCookBook2.activity.results.RateDrinkRecipeResult;
import TheCookBook2.activity.results.RateFoodRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.dynamodb.models.FoodRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RateFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;
    @InjectMocks
    private RateFoodRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RateFoodRecipeActivity(foodRecipeDao);

    }

    @Test
    void handleRequest_withoutRating_updatesFoodRecipeRatings() {
        //GIVEN
        String expectedCreator = "expectedCreator";
        String expectedRecipeTitle = "expectedRecipeTitle";
        Map<String, String> expectedIngredients = new HashMap();
        expectedIngredients.put("expectedIngredient", "expectedIngredientAmount");
        LinkedList<String> expectedInstructionSteps = new LinkedList<>();
        expectedInstructionSteps.add("expectedStep");
        String expectedDescription = "expectedDescription";
        Set<String> expectedDescriptionTags = new HashSet<>();
        expectedDescriptionTags.add("expectedTag");
        String expectedFoodCategory = "expectedFoodCategory";
        String expectedFoodItem = "expectedFoodItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator(expectedCreator);
        foodRecipe.setRecipeTitle(expectedRecipeTitle);
        foodRecipe.setIngredients(expectedIngredients);
        foodRecipe.setInstructionSteps(expectedInstructionSteps);
        foodRecipe.setDescription(expectedDescription);
        foodRecipe.setDescriptionTags(expectedDescriptionTags);
        foodRecipe.setTimeEstimate(0);
        foodRecipe.setFoodCategory(expectedFoodCategory);
        foodRecipe.setFoodItem(expectedFoodItem);
        foodRecipe.setAllergies(expectedAllergies);
        foodRecipe.setRatings(expectedRatings);

        RateFoodRecipeRequest request1 = RateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(foodRecipeDao.getFoodRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(foodRecipe);
        when(foodRecipeDao.saveFoodRecipe(foodRecipe)).thenReturn(foodRecipe);

        //WHEN
        RateFoodRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        assertEquals(expectedRatingsTested, result1.getRatings());
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());
    }

    @Test
    void handleRequest_withDifferentRating_updatesDrinkRecipeRatings() {
        //GIVEN
        String expectedCreator = "expectedCreator";
        String expectedRecipeTitle = "expectedRecipeTitle";
        Map<String, String> expectedIngredients = new HashMap();
        expectedIngredients.put("expectedIngredient", "expectedIngredientAmount");
        LinkedList<String> expectedInstructionSteps = new LinkedList<>();
        expectedInstructionSteps.add("expectedStep");
        String expectedDescription = "expectedDescription";
        Set<String> expectedDescriptionTags = new HashSet<>();
        expectedDescriptionTags.add("expectedTag");
        String expectedFoodCategory = "expectedFoodCategory";
        String expectedFoodItem = "expectedFoodItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 1);

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator(expectedCreator);
        foodRecipe.setRecipeTitle(expectedRecipeTitle);
        foodRecipe.setIngredients(expectedIngredients);
        foodRecipe.setInstructionSteps(expectedInstructionSteps);
        foodRecipe.setDescription(expectedDescription);
        foodRecipe.setDescriptionTags(expectedDescriptionTags);
        foodRecipe.setTimeEstimate(0);
        foodRecipe.setFoodCategory(expectedFoodCategory);
        foodRecipe.setFoodItem(expectedFoodItem);
        foodRecipe.setAllergies(expectedAllergies);
        foodRecipe.setRatings(expectedRatings);

        RateFoodRecipeRequest request1 = RateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(foodRecipeDao.getFoodRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(foodRecipe);
        when(foodRecipeDao.saveFoodRecipe(foodRecipe)).thenReturn(foodRecipe);

        //WHEN
        RateFoodRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        assertEquals(expectedRatingsTested, result1.getRatings());
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());
    }

    @Test
    void handleRequest_withSameRating_removesDrinkRecipeRating() {
        //GIVEN
        String expectedCreator = "expectedCreator";
        String expectedRecipeTitle = "expectedRecipeTitle";
        Map<String, String> expectedIngredients = new HashMap();
        expectedIngredients.put("expectedIngredient", "expectedIngredientAmount");
        LinkedList<String> expectedInstructionSteps = new LinkedList<>();
        expectedInstructionSteps.add("expectedStep");
        String expectedDescription = "expectedDescription";
        Set<String> expectedDescriptionTags = new HashSet<>();
        expectedDescriptionTags.add("expectedTag");
        String expectedFoodCategory = "expectedFoodCategory";
        String expectedFoodItem = "expectedFoodItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 0);

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator(expectedCreator);
        foodRecipe.setRecipeTitle(expectedRecipeTitle);
        foodRecipe.setIngredients(expectedIngredients);
        foodRecipe.setInstructionSteps(expectedInstructionSteps);
        foodRecipe.setDescription(expectedDescription);
        foodRecipe.setDescriptionTags(expectedDescriptionTags);
        foodRecipe.setTimeEstimate(0);
        foodRecipe.setFoodCategory(expectedFoodCategory);
        foodRecipe.setFoodItem(expectedFoodItem);
        foodRecipe.setAllergies(expectedAllergies);
        foodRecipe.setRatings(expectedRatings);

        RateFoodRecipeRequest request1 = RateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(foodRecipeDao.getFoodRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(foodRecipe);
        when(foodRecipeDao.saveFoodRecipe(foodRecipe)).thenReturn(foodRecipe);

        //WHEN
        RateFoodRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        assertEquals(null, result1.getRatings().get("test"));
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());
    }
}

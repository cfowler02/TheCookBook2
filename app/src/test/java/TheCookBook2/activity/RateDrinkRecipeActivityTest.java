package TheCookBook2.activity;

import TheCookBook2.activity.requests.RateDrinkRecipeRequest;
import TheCookBook2.activity.results.RateDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;

import TheCookBook2.dynamodb.models.DrinkRecipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RateDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;
    @InjectMocks
    private RateDrinkRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RateDrinkRecipeActivity(drinkRecipeDao);

    }

    @Test
    void handleRequest_withoutRating_updatesDrinkRecipeRatings() {
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
        String expectedDrinkCategory = "expectedDrinkCategory";
        String expectedDrinkItem = "expectedDrinkItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        DrinkRecipe drinkRecipe = new DrinkRecipe();
        drinkRecipe.setCreator(expectedCreator);
        drinkRecipe.setRecipeTitle(expectedRecipeTitle);
        drinkRecipe.setIngredients(expectedIngredients);
        drinkRecipe.setInstructionSteps(expectedInstructionSteps);
        drinkRecipe.setDescription(expectedDescription);
        drinkRecipe.setDescriptionTags(expectedDescriptionTags);
        drinkRecipe.setDrinkCategory(expectedDrinkCategory);
        drinkRecipe.setDrinkItem(expectedDrinkItem);
        drinkRecipe.setAllergies(expectedAllergies);
        drinkRecipe.setRatings(expectedRatings);

        RateDrinkRecipeRequest request1 = RateDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);
        when(drinkRecipeDao.saveDrinkRecipe(drinkRecipe)).thenReturn(drinkRecipe);

        //WHEN
        RateDrinkRecipeResult result1 = activity.handleRequest(request1);

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
        String expectedDrinkCategory = "expectedDrinkCategory";
        String expectedDrinkItem = "expectedDrinkItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 1);

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        DrinkRecipe drinkRecipe = new DrinkRecipe();
        drinkRecipe.setCreator(expectedCreator);
        drinkRecipe.setRecipeTitle(expectedRecipeTitle);
        drinkRecipe.setIngredients(expectedIngredients);
        drinkRecipe.setInstructionSteps(expectedInstructionSteps);
        drinkRecipe.setDescription(expectedDescription);
        drinkRecipe.setDescriptionTags(expectedDescriptionTags);
        drinkRecipe.setDrinkCategory(expectedDrinkCategory);
        drinkRecipe.setDrinkItem(expectedDrinkItem);
        drinkRecipe.setAllergies(expectedAllergies);
        drinkRecipe.setRatings(expectedRatings);

        RateDrinkRecipeRequest request1 = RateDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);
        when(drinkRecipeDao.saveDrinkRecipe(drinkRecipe)).thenReturn(drinkRecipe);

        //WHEN
        RateDrinkRecipeResult result1 = activity.handleRequest(request1);

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
        String expectedDrinkCategory = "expectedDrinkCategory";
        String expectedDrinkItem = "expectedDrinkItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 0);

        Map<String, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put("test", 0);


        DrinkRecipe drinkRecipe = new DrinkRecipe();
        drinkRecipe.setCreator(expectedCreator);
        drinkRecipe.setRecipeTitle(expectedRecipeTitle);
        drinkRecipe.setIngredients(expectedIngredients);
        drinkRecipe.setInstructionSteps(expectedInstructionSteps);
        drinkRecipe.setDescription(expectedDescription);
        drinkRecipe.setDescriptionTags(expectedDescriptionTags);
        drinkRecipe.setDrinkCategory(expectedDrinkCategory);
        drinkRecipe.setDrinkItem(expectedDrinkItem);
        drinkRecipe.setAllergies(expectedAllergies);
        drinkRecipe.setRatings(expectedRatings);

        RateDrinkRecipeRequest request1 = RateDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(0)
                .withUser("test")
                .build();

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);
        when(drinkRecipeDao.saveDrinkRecipe(drinkRecipe)).thenReturn(drinkRecipe);

        //WHEN
        RateDrinkRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        assertEquals(null, result1.getRatings().get("test"));
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());
    }
}

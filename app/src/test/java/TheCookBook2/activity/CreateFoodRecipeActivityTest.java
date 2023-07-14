package TheCookBook2.activity;

import TheCookBook2.activity.requests.CreateFoodRecipeRequest;
import TheCookBook2.activity.results.CreateFoodRecipeResult;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.verify;

public class CreateFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;
    //@InjectMocks
    private CreateFoodRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        activity = new CreateFoodRecipeActivity(foodRecipeDao);
    }

    @Test
    public void handleRequest_withMostAttributes_createsAndSavesFoodRecipe() {
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

        CreateFoodRecipeRequest request1 = CreateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withIngredients(expectedIngredients)
                .withInstructionSteps(expectedInstructionSteps)
                .withDescription(expectedDescription)
                .withDescriptionTags(expectedDescriptionTags)
                .withTimeEstimate(expectedTimeEstimate)
                .withFoodCategory(expectedFoodCategory)
                .withFoodItem(expectedFoodItem)
                .withAllergies(expectedAllergies)
                .withRatings(expectedRatings)
                .build();

        when(foodRecipeDao.saveFoodRecipe(eq(foodRecipe))).thenReturn(foodRecipe);

        //When
        CreateFoodRecipeResult result1 = activity.handleRequest(request1);

        //Then
        verify(foodRecipeDao).saveFoodRecipe(eq(foodRecipe));
        assertEquals(expectedCreator, result1.getFoodRecipe().getCreator());
        assertEquals(expectedRecipeTitle, result1.getFoodRecipe().getRecipeTitle());
        assertEquals(expectedIngredients, result1.getFoodRecipe().getIngredients());
        assertEquals(expectedInstructionSteps, result1.getFoodRecipe().getInstructionSteps());
        assertEquals(expectedDescription, result1.getFoodRecipe().getDescription());
        assertEquals(expectedDescriptionTags, result1.getFoodRecipe().getDescriptionTags());
        assertEquals(expectedTimeEstimate, result1.getFoodRecipe().getTimeEstimate());
        assertEquals(expectedFoodCategory, result1.getFoodRecipe().getFoodCategory());
        assertEquals(expectedFoodItem, result1.getFoodRecipe().getFoodItem());
        assertEquals(expectedAllergies, result1.getFoodRecipe().getAllergies());
        assertEquals(expectedRatings, result1.getFoodRecipe().getRatings());
    }
}
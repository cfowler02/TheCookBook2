package TheCookBook2.activity;

import TheCookBook2.activity.requests.CreateDrinkRecipeRequest;
import TheCookBook2.activity.results.CreateDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.verify;

public class CreateDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;
    @InjectMocks
    private CreateDrinkRecipeActivity activity;

    @BeforeEach
    void setup() {
        initMocks(this);
        activity = new CreateDrinkRecipeActivity(drinkRecipeDao);
    }

    @Test
    public void handleRequest_withMostAttributes_createsAndSavesDrinkRecipe() {
        //Given
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
        Map<Integer, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put(-1, 0);
        expectedRatings.put(0, 1);
        expectedRatings.put(1, 2);

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

        CreateDrinkRecipeRequest request1 = CreateDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withIngredients(expectedIngredients)
                .withInstructionSteps(expectedInstructionSteps)
                .withDescription(expectedDescription)
                .withDescriptionTags(expectedDescriptionTags)
                .withDrinkCategory(expectedDrinkCategory)
                .withDrinkItem(expectedDrinkItem)
                .withAllergies(expectedAllergies)
                .withRatings(expectedRatings)
                .build();

        when(drinkRecipeDao.saveDrinkRecipe(eq(drinkRecipe))).thenReturn(drinkRecipe);

        //When
        CreateDrinkRecipeResult result1 = activity.handleRequest(request1);

        //Then
        verify(drinkRecipeDao).saveDrinkRecipe(eq(drinkRecipe));
        assertEquals(expectedCreator, result1.getDrinkRecipe().getCreator());
        assertEquals(expectedRecipeTitle, result1.getDrinkRecipe().getRecipeTitle());
        assertEquals(expectedIngredients, result1.getDrinkRecipe().getIngredients());
        assertEquals(expectedInstructionSteps, result1.getDrinkRecipe().getInstructionSteps());
        assertEquals(expectedDescription, result1.getDrinkRecipe().getDescription());
        assertEquals(expectedDescriptionTags, result1.getDrinkRecipe().getDescriptionTags());
        assertEquals(expectedDrinkCategory, result1.getDrinkRecipe().getDrinkCategory());
        assertEquals(expectedDrinkItem, result1.getDrinkRecipe().getDrinkItem());
        assertEquals(expectedAllergies, result1.getDrinkRecipe().getAllergies());
        assertEquals(expectedRatings, result1.getDrinkRecipe().getRatings());
    }
}

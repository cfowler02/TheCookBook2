package TheCookBook2.activity;

import TheCookBook2.activity.requests.DeleteDrinkRecipeRequest;
import TheCookBook2.activity.results.DeleteDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;

    private DeleteDrinkRecipeActivity deleteDrinkRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteDrinkRecipeActivity = new DeleteDrinkRecipeActivity(drinkRecipeDao);
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
        String expectedDrinkCategory = "expectedDrinkCategory";
        String expectedDrinkItem = "expectedDrinkItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<String, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put("test", 0);

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

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);

        DeleteDrinkRecipeRequest request = DeleteDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .build();

        // WHEN
        DeleteDrinkRecipeResult result = deleteDrinkRecipeActivity.handleRequest(request);


        // THEN
        assertEquals(expectedCreator, result.getDrinkRecipeModel().getCreator());
        assertEquals(expectedRecipeTitle, result.getDrinkRecipeModel().getRecipeTitle());
        assertEquals(expectedIngredients, result.getDrinkRecipeModel().getIngredients());
        assertEquals(expectedInstructionSteps, result.getDrinkRecipeModel().getInstructionSteps());
        assertEquals(expectedDescription, result.getDrinkRecipeModel().getDescription());
        assertEquals(expectedDescriptionTags, result.getDrinkRecipeModel().getDescriptionTags());
        assertEquals(expectedDrinkCategory, result.getDrinkRecipeModel().getDrinkCategory());
        assertEquals(expectedDrinkItem, result.getDrinkRecipeModel().getDrinkItem());
        assertEquals(expectedAllergies, result.getDrinkRecipeModel().getAllergies());
        assertEquals(expectedRatings, result.getDrinkRecipeModel().getRatings());
    }
}

package TheCookBook2.activity;



import TheCookBook2.activity.requests.GetDrinkRecipeRequest;
import TheCookBook2.activity.results.GetDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;

    private GetDrinkRecipeActivity getDrinkRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getDrinkRecipeActivity = new GetDrinkRecipeActivity(drinkRecipeDao);
    }

    @Test
    public void handleRequest_savedDrinkRecipeFound_returnsDrinkRecipeModelInResult() {
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

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);

        GetDrinkRecipeRequest request = GetDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .build();

        // WHEN
        GetDrinkRecipeResult result = getDrinkRecipeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedCreator, result.getDrinkRecipe().getCreator());
        assertEquals(expectedRecipeTitle, result.getDrinkRecipe().getRecipeTitle());
        assertEquals(expectedIngredients, result.getDrinkRecipe().getIngredients());
        assertEquals(expectedInstructionSteps, result.getDrinkRecipe().getInstructionSteps());
        assertEquals(expectedDescription, result.getDrinkRecipe().getDescription());
        assertEquals(expectedDescriptionTags, result.getDrinkRecipe().getDescriptionTags());
        assertEquals(expectedDrinkCategory, result.getDrinkRecipe().getDrinkCategory());
        assertEquals(expectedDrinkItem, result.getDrinkRecipe().getDrinkItem());
        assertEquals(expectedAllergies, result.getDrinkRecipe().getAllergies());
        assertEquals(expectedRatings, result.getDrinkRecipe().getRatings());
    }
}
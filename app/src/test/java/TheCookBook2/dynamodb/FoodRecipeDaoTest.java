package TheCookBook2.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.exceptions.FoodRecipeNotFoundException;
import TheCookBook2.metrics.MetricsConstants;
import TheCookBook2.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class FoodRecipeDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;


    private FoodRecipeDao foodRecipeDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        foodRecipeDao = new FoodRecipeDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getFoodRecipe_witCreatorAndRecipeTitle_callsMapper() {
        // GIVEN
        String creator = "creator";
        String recipeTitle = "recipe title";
        when(dynamoDBMapper.load(FoodRecipe.class, creator, recipeTitle)).thenReturn(new FoodRecipe());

        // WHEN
        FoodRecipe foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);

        // THEN
        assertNotNull(foodRecipe);
        verify(dynamoDBMapper).load(FoodRecipe.class, creator, recipeTitle);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT), anyDouble());

    }

    @Test
    public void getFoodRecipe_KeysNotFound_throwsFoodRecipeNotFoundException() {
        // GIVEN
        String nonexistentCreator = "NotReal";
        String nonexistentRecipeTitle = "AlsoNotReal";
        when(dynamoDBMapper.load(FoodRecipe.class, nonexistentCreator, nonexistentRecipeTitle)).thenReturn(null);

        // WHEN + THEN
        assertThrows(FoodRecipeNotFoundException.class, () -> foodRecipeDao.getFoodRecipe(nonexistentCreator, nonexistentRecipeTitle));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveFoodRecipe_callsMapperWithFoodRecipe() {
        // GIVEN
        FoodRecipe foodRecipe = new FoodRecipe();

        // WHEN
        FoodRecipe result = foodRecipeDao.saveFoodRecipe(foodRecipe);

        // THEN
        verify(dynamoDBMapper).save(foodRecipe);
        assertEquals(foodRecipe, result);
    }
}

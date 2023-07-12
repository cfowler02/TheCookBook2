package TheCookBook2.activity;

import TheCookBook2.activity.requests.GetFoodRecipeRequest;
import TheCookBook2.activity.results.GetFoodRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.FoodRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetFoodRecipeActivity {

    private final Logger log = LogManager.getLogger();

    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public GetFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public GetFoodRecipeResult handleRequest(final GetFoodRecipeRequest getFoodRecipeRequest) {
        log.info("Received GetFoodRecipeRequest {}", getFoodRecipeRequest);
        String creator = getFoodRecipeRequest.getCreator();
        String recipeTitle = getFoodRecipeRequest.getRecipeTitle();
        FoodRecipe foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);
        FoodRecipeModel foodRecipeModel = new ModelConverter().toFoodRecipeModel(foodRecipe);

        return GetFoodRecipeResult.builder()
                .withFoodRecipe(foodRecipeModel)
                .build();
    }
}

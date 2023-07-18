package TheCookBook2.activity;


import TheCookBook2.activity.requests.DeleteFoodRecipeRequest;
import TheCookBook2.activity.results.DeleteFoodRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.FoodRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();

    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public DeleteFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public DeleteFoodRecipeResult handleRequest(final DeleteFoodRecipeRequest deleteFoodRecipeRequest) {
        log.info("Received DeleteFoodRecipeRequest {}", deleteFoodRecipeRequest);
        String creator = deleteFoodRecipeRequest.getCreator();
        String recipeTitle = deleteFoodRecipeRequest.getRecipeTitle();
        FoodRecipe foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);
        FoodRecipeModel foodRecipeModel = new ModelConverter().toFoodRecipeModel(foodRecipe);
        foodRecipeDao.deleteFoodRecipe(foodRecipe);

        return DeleteFoodRecipeResult.builder()
                .withFoodRecipe(foodRecipeModel)
                .build();
    }
}

package TheCookBook2.activity;

import TheCookBook2.activity.requests.ViewFoodRecipeRequest;
import TheCookBook2.activity.results.ViewFoodRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.FoodRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class ViewFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public ViewFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public ViewFoodRecipeResult handleRequest(final ViewFoodRecipeRequest viewFoodRecipeRequest) {
        log.info("Received ViewFoodRecipeActivity {}", viewFoodRecipeRequest);

        List<FoodRecipe> results = foodRecipeDao.viewFoodRecipe();
        List<FoodRecipeModel> foodRecipeModels = new ModelConverter().toFoodRecipeModelList(results);

        return ViewFoodRecipeResult.builder()
                .withFoodRecipeModels(foodRecipeModels)
                .build();
    }
}

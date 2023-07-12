package TheCookBook2.activity;


import TheCookBook2.activity.requests.SearchFoodRecipeRequest;
import TheCookBook2.activity.results.SearchFoodRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.FoodRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import java.util.List;

import static TheCookBook2.utils.NullUtils.ifNull;

public class SearchFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public SearchFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public SearchFoodRecipeResult handleRequest(final SearchFoodRecipeRequest searchFoodRecipeRequest) {
        log.info("Received SearchFoodRecipeRequest {}", searchFoodRecipeRequest);
        String criteria = searchFoodRecipeRequest.getCriteria();
        String filter = searchFoodRecipeRequest.getFilter();

        List<FoodRecipe> results = foodRecipeDao.searchFoodRecipe(filter, criteria);
        List<FoodRecipeModel> foodRecipeModels = new ModelConverter().toFoodRecipeModelList(results);

        return SearchFoodRecipeResult.builder()
                .withFoodRecipeModels(foodRecipeModels)
                .build();
    }
}

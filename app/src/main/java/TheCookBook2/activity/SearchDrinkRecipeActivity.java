package TheCookBook2.activity;

import TheCookBook2.activity.requests.SearchDrinkRecipeRequest;
import TheCookBook2.activity.results.SearchDrinkRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.models.DrinkRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static TheCookBook2.utils.NullUtils.ifNull;

public class SearchDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public SearchDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public SearchDrinkRecipeResult handleRequest(final SearchDrinkRecipeRequest searchDrinkRecipeRequest) {
        log.info("Received SearchDrinkRecipeRequest {}", searchDrinkRecipeRequest);
        String criteria = searchDrinkRecipeRequest.getCriteria();
        String filter = searchDrinkRecipeRequest.getFilter();

        List<DrinkRecipe> results = drinkRecipeDao.searchDrinkRecipe(filter, criteria);
        log.error("FILTER {}", filter);
        log.error("CRITERIA {}", criteria);
        log.error("RESULTS {}", results);
        List<DrinkRecipeModel> drinkRecipeModels = new ModelConverter().toDrinkRecipeModelList(results);
        log.error(drinkRecipeModels);

        return SearchDrinkRecipeResult.builder()
                .withDrinkRecipeModels(drinkRecipeModels)
                .build();
    }
}

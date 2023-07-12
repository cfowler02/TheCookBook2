package TheCookBook2.activity;

import TheCookBook2.activity.requests.ViewDrinkRecipeRequest;
import TheCookBook2.activity.results.ViewDrinkRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.models.DrinkRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class ViewDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public ViewDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public ViewDrinkRecipeResult handleRequest(final ViewDrinkRecipeRequest viewDrinkRecipeRequest) {
        log.info("Received ViewDrinkRecipeActivity {}", viewDrinkRecipeRequest);

        List<DrinkRecipe> results = drinkRecipeDao.viewDrinkRecipe();
        List<DrinkRecipeModel> drinkRecipeModels = new ModelConverter().toDrinkRecipeModelList(results);

        return ViewDrinkRecipeResult.builder()
                .withDrinkRecipeModels(drinkRecipeModels)
                .build();
    }
}

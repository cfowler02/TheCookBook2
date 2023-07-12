package TheCookBook2.activity;

import TheCookBook2.activity.requests.GetDrinkRecipeRequest;
import TheCookBook2.activity.results.GetDrinkRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.models.DrinkRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetDrinkRecipeActivity {

    private final Logger log = LogManager.getLogger();

    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public GetDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public GetDrinkRecipeResult handleRequest(final GetDrinkRecipeRequest getDrinkRecipeRequest) {
        log.info("Received GetDrinkRecipeRequest {}", getDrinkRecipeRequest);
        String creator = getDrinkRecipeRequest.getCreator();
        String recipeTitle = getDrinkRecipeRequest.getRecipeTitle();
        DrinkRecipe drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);
        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(drinkRecipe);

        return GetDrinkRecipeResult.builder()
                .withDrinkRecipe(drinkRecipeModel)
                .build();
    }
}

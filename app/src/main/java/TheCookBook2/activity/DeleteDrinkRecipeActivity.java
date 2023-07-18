package TheCookBook2.activity;


import TheCookBook2.activity.requests.DeleteDrinkRecipeRequest;
import TheCookBook2.activity.results.DeleteDrinkRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.models.DrinkRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();

    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public DeleteDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public DeleteDrinkRecipeResult handleRequest(final DeleteDrinkRecipeRequest deleteDrinkRecipeRequest) {
        log.info("Received DeleteDrinkRecipeRequest {}", deleteDrinkRecipeRequest);
        String creator = deleteDrinkRecipeRequest.getCreator();
        String recipeTitle = deleteDrinkRecipeRequest.getRecipeTitle();
        DrinkRecipe drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);
        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(drinkRecipe);
        drinkRecipeDao.deleteDrinkRecipe(drinkRecipe);

        return DeleteDrinkRecipeResult.builder()
                .withFoodRecipe(drinkRecipeModel)
                .build();
    }
}

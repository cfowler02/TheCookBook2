package TheCookBook2.activity;

import TheCookBook2.activity.requests.CreateDrinkRecipeRequest;
import TheCookBook2.activity.results.CreateDrinkRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;

import TheCookBook2.models.DrinkRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public CreateDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public CreateDrinkRecipeResult handleRequest(final CreateDrinkRecipeRequest createDrinkRecipeRequest) {
        log.info("Received CreateDrinkRecipeRequest {}", createDrinkRecipeRequest);

        DrinkRecipe newDrinkRecipe = new DrinkRecipe();
        newDrinkRecipe.setCreator(createDrinkRecipeRequest.getCreator());
        newDrinkRecipe.setRecipeTitle(createDrinkRecipeRequest.getRecipeTitle());
        newDrinkRecipe.setIngredients(createDrinkRecipeRequest.getIngredients());
        newDrinkRecipe.setInstructionSteps(createDrinkRecipeRequest.getInstructionSteps());
        newDrinkRecipe.setDescription(createDrinkRecipeRequest.getDescription());
        newDrinkRecipe.setDescriptionTags(createDrinkRecipeRequest.getDescriptionTags());
        newDrinkRecipe.setDrinkCategory(createDrinkRecipeRequest.getDrinkCategory());
        newDrinkRecipe.setDrinkItem(createDrinkRecipeRequest.getDrinkItem());
        newDrinkRecipe.setAllergies(createDrinkRecipeRequest.getAllergies());
        newDrinkRecipe.setRatings(createDrinkRecipeRequest.getRatings());
        drinkRecipeDao.saveDrinkRecipe(newDrinkRecipe);

        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(newDrinkRecipe);
        return CreateDrinkRecipeResult.builder()
                .withDrinkRecipe(drinkRecipeModel)
                .build();
    }
}

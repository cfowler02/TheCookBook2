package TheCookBook2.activity;

import TheCookBook2.activity.requests.CreateFoodRecipeRequest;
import TheCookBook2.activity.results.CreateFoodRecipeResult;
import TheCookBook2.converters.ModelConverter;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.models.FoodRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public CreateFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public CreateFoodRecipeResult handleRequest(final CreateFoodRecipeRequest createFoodRecipeRequest) {
        log.info("Received CreateFoodRecipeRequest {}", createFoodRecipeRequest);

        FoodRecipe newFoodRecipe = new FoodRecipe();
        newFoodRecipe.setCreator(createFoodRecipeRequest.getCreator());
        newFoodRecipe.setRecipeTitle(createFoodRecipeRequest.getRecipeTitle());
        newFoodRecipe.setIngredients(createFoodRecipeRequest.getIngredients());
        newFoodRecipe.setInstructionSteps(createFoodRecipeRequest.getInstructionSteps());
        newFoodRecipe.setDescription(createFoodRecipeRequest.getDescription());
        newFoodRecipe.setDescriptionTags(createFoodRecipeRequest.getDescriptionTags());
        newFoodRecipe.setTimeEstimate(createFoodRecipeRequest.getTimeEstimate());
        newFoodRecipe.setFoodCategory(createFoodRecipeRequest.getFoodCategory());
        newFoodRecipe.setFoodItem(createFoodRecipeRequest.getFoodItem());
        newFoodRecipe.setAllergies(createFoodRecipeRequest.getAllergies());
        newFoodRecipe.setRatings(createFoodRecipeRequest.getRatings());
        foodRecipeDao.saveFoodRecipe(newFoodRecipe);

        FoodRecipeModel foodRecipeModel = new ModelConverter().toFoodRecipeModel(newFoodRecipe);
        return CreateFoodRecipeResult.builder()
                .withFoodRecipe(foodRecipeModel)
                .build();
    }
}

package TheCookBook2.activity;


import TheCookBook2.activity.results.RateFoodRecipeResult;
import TheCookBook2.activity.requests.RateFoodRecipeRequest;
import TheCookBook2.dynamodb.FoodRecipeDao;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.exceptions.FoodRecipeNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RateFoodRecipeActivity {
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public RateFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public RateFoodRecipeResult handleRequest(
            final RateFoodRecipeRequest rateFoodRecipeRequest) {

        String creator = rateFoodRecipeRequest.getCreator();
        String recipeTitle = rateFoodRecipeRequest.getRecipeTitle();

        FoodRecipe foodRecipe;
        try {foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);
        } catch (FoodRecipeNotFoundException ex) {
            throw new FoodRecipeNotFoundException("Food Recipe is not in our database.");
        }

        int ratingToAdd = rateFoodRecipeRequest.getRating();
        String user = rateFoodRecipeRequest.getUser();

        Map<String, Integer> ratings;

        if (foodRecipe.getRatings() == null) {
            ratings = new HashMap<>();
        } else  {
            ratings = new HashMap<>(foodRecipe.getRatings());
        }

        if (ratings.get(user) != null && ratings.get(user) == ratingToAdd){
            ratings.remove(user);
        } else if (ratings.get(user) != null && ratings.get(user) != ratingToAdd) {
            ratings.replace(user, ratingToAdd);
        } else {
            ratings.put(user, ratingToAdd);
        }

        foodRecipe.setRatings(ratings);
        foodRecipeDao.saveFoodRecipe(foodRecipe);

        return RateFoodRecipeResult.builder()
                .withRatings(ratings)
                .build();
    }
}

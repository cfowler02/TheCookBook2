package TheCookBook2.activity;

import TheCookBook2.activity.requests.RateDrinkRecipeRequest;
import TheCookBook2.activity.results.RateDrinkRecipeResult;
import TheCookBook2.dynamodb.DrinkRecipeDao;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.exceptions.DrinkRecipeNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RateDrinkRecipeActivity {
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public RateDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public RateDrinkRecipeResult handleRequest(
            final RateDrinkRecipeRequest rateDrinkRecipeRequest) {

        String creator = rateDrinkRecipeRequest.getCreator();
        String recipeTitle = rateDrinkRecipeRequest.getRecipeTitle();

        DrinkRecipe drinkRecipe;
        try {drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);
        } catch (DrinkRecipeNotFoundException ex) {
            throw new DrinkRecipeNotFoundException("Drink Recipe is not in our database.");
        }

        int ratingToAdd = rateDrinkRecipeRequest.getRating();
        String user = rateDrinkRecipeRequest.getUser();

        Map<String, Integer> ratings;

        if (drinkRecipe.getRatings() == null) {
            ratings = new HashMap<>();
        } else  {
            ratings = new HashMap<>(drinkRecipe.getRatings());
        }

        if (ratings.get(user) != null && ratings.get(user) == ratingToAdd){
            ratings.remove(user);
        } else if (ratings.get(user) != null && ratings.get(user) != ratingToAdd) {
            ratings.replace(user, ratingToAdd);
        } else {
            ratings.put(user, ratingToAdd);
        }

        drinkRecipe.setRatings(ratings);
        drinkRecipeDao.saveDrinkRecipe(drinkRecipe);

        return RateDrinkRecipeResult.builder()
                .withRatings(ratings)
                .build();
    }
}

package TheCookBook2.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import TheCookBook2.dynamodb.models.FoodRecipe;
import TheCookBook2.exceptions.FoodRecipeNotFoundException;
import TheCookBook2.metrics.MetricsConstants;
import TheCookBook2.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class FoodRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public FoodRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public FoodRecipe getFoodRecipe(String creator, String recipeTitle) {
        FoodRecipe foodRecipe = this.dynamoDBMapper.load(FoodRecipe.class, creator, recipeTitle);

        if (foodRecipe == null) {
            metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 1);
            throw new FoodRecipeNotFoundException("Could not find food recipe with creator and title " +
                    creator + recipeTitle);
        }
        metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 0);
        return foodRecipe;
    }

    public FoodRecipe saveFoodRecipe(FoodRecipe foodRecipe) {
        this.dynamoDBMapper.save(foodRecipe);
        return foodRecipe;
    }

    public void deleteFoodRecipe(FoodRecipe foodRecipe) {
        this.dynamoDBMapper.delete(foodRecipe);
    }

    public List<FoodRecipe> searchFoodRecipe(String filter, String criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        DynamoDBQueryExpression<FoodRecipe> dynamoDBQueryExpression = new DynamoDBQueryExpression<FoodRecipe>();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":criteria", new AttributeValue().withS(criteria));
        List<FoodRecipe> recipeList = new ArrayList<>();

        if (filter == "Creator"){
            dynamoDBQueryExpression.withKeyConditionExpression("creator = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.query(FoodRecipe.class, dynamoDBQueryExpression));
        } else if (filter == "RecipeTitle") {
            dynamoDBScanExpression.withFilterExpression("contains(recipe_title, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression));
        } else if (filter == "Ingredient") {
            dynamoDBScanExpression.withFilterExpression("contains(ingredients, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression));
        } else if (filter == "DescriptionTag") {
            dynamoDBScanExpression.withFilterExpression("contains(description_tags, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression));
        } else if (filter == "FoodCategory") {
            dynamoDBScanExpression.withFilterExpression("contains(food_category, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression));
        } else if (filter == "FoodItem") {
            dynamoDBScanExpression.withFilterExpression("food_recipe = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression));
        }

        return recipeList;
    }

    public List<FoodRecipe> viewFoodRecipe() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return this.dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression);
    }
}

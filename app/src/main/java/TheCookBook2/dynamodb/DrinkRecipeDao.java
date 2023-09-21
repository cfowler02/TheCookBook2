package TheCookBook2.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import TheCookBook2.dynamodb.models.DrinkRecipe;
import TheCookBook2.exceptions.DrinkRecipeNotFoundException;
import TheCookBook2.metrics.MetricsConstants;
import TheCookBook2.metrics.MetricsPublisher;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DrinkRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public DrinkRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public DrinkRecipe getDrinkRecipe(String creator, String recipeTitle) {
        DrinkRecipe drinkRecipe = this.dynamoDBMapper.load(DrinkRecipe.class, creator, recipeTitle);

        if (drinkRecipe == null) {
            metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 1);
            throw new DrinkRecipeNotFoundException("Could not find drink recipe with creator and title " +
                    creator + recipeTitle);
        }
        metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 0);
        return drinkRecipe;
    }

    public DrinkRecipe saveDrinkRecipe(DrinkRecipe drinkRecipe) {
        System.out.println(drinkRecipe.toString());
        this.dynamoDBMapper.save(drinkRecipe);
        return drinkRecipe;
    }

    public void deleteDrinkRecipe(DrinkRecipe drinkRecipe){
        this.dynamoDBMapper.delete(drinkRecipe);
    }

    public List<DrinkRecipe> searchDrinkRecipe(String filter, String criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        DynamoDBQueryExpression<DrinkRecipe> dynamoDBQueryExpression = new DynamoDBQueryExpression<DrinkRecipe>();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":criteria", new AttributeValue().withS(criteria));
        List<DrinkRecipe> recipeList = new ArrayList<>();

        System.out.println(criteria);
        System.out.println(valueMap);
        System.out.println(filter);

        if (filter.equals("Creator")){
            System.out.println("1");
            dynamoDBQueryExpression.withKeyConditionExpression("creator = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.query(DrinkRecipe.class, dynamoDBQueryExpression));
            System.out.println(dynamoDBMapper.query(DrinkRecipe.class, dynamoDBQueryExpression));
        } else if (filter.equals("RecipeTitle")) {
            System.out.println("2");
            dynamoDBScanExpression.withFilterExpression("contains(recipe_title, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter.equals("Ingredient")) {
            System.out.println("3");
            dynamoDBScanExpression.withFilterExpression("contains(ingredients, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
            //System.out.print("RECIPELIST {}", recipeList);
            System.out.println(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter.equals("DescriptionTag")) {
            System.out.println("4");
            dynamoDBScanExpression.withFilterExpression("contains(description_tags, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter.equals("DrinkCategory")) {
            System.out.println("5");
            dynamoDBScanExpression.withFilterExpression("drink_category = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter.equals("DrinkItem")) {
            System.out.println("6");
            dynamoDBScanExpression.withFilterExpression("drink_recipe = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        }

        System.out.println(recipeList);

        return recipeList;
    }

    public List<DrinkRecipe> viewDrinkRecipe() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return this.dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression);
    }
}

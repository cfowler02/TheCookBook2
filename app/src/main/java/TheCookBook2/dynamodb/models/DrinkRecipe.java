package TheCookBook2.dynamodb.models;

import TheCookBook2.converters.RecipeLinkedListConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.*;

@DynamoDBTable(tableName = "drink_recipes")
public class DrinkRecipe {

    private String creator;
    private String recipeTitle;
    private Map<String, String> ingredients;
    private LinkedList<String> instructionSteps;
    private String description;
    private Set<String> descriptionTags;
    private String drinkCategory;
    private String drinkItem;
    private Set<String> allergies;
    private Map<String, Integer> ratings;

    @DynamoDBHashKey(attributeName = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @DynamoDBRangeKey(attributeName = "recipe_title")
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    @DynamoDBAttribute(attributeName = "ingredients")
    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    @DynamoDBTypeConverted(converter = RecipeLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "instruction_steps")
    public LinkedList<String> getInstructionSteps() {
        return instructionSteps;
    }

    public void setInstructionSteps(LinkedList<String> instructionSteps) {
        this.instructionSteps = instructionSteps;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "description_tags")
    public Set<String> getDescriptionTags() {
        return descriptionTags;
    }

    public void setDescriptionTags(Set<String> descriptionTags) {
        this.descriptionTags = descriptionTags;
    }

    @DynamoDBAttribute(attributeName = "drink_category")
    public String getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory = drinkCategory;
    }
    @DynamoDBAttribute(attributeName = "drink_item")
    public String getDrinkItem() {
        return drinkItem;
    }

    public void setDrinkItem(String drinkItem) {
        this.drinkItem = drinkItem;
    }
    @DynamoDBAttribute(attributeName = "allergies")
    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

    @DynamoDBAttribute(attributeName = "ratings")
    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DrinkRecipe that = (DrinkRecipe) o;
        return Objects.equals(creator, that.creator) &&
                Objects.equals(recipeTitle, that.recipeTitle) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(instructionSteps, that.instructionSteps) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionTags, that.descriptionTags) &&
                Objects.equals(drinkCategory, that.drinkCategory) &&
                Objects.equals(drinkItem, that.drinkItem) &&
                Objects.equals(allergies, that.allergies) &&
                Objects.equals(ratings, that.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, recipeTitle, ingredients, instructionSteps, description, descriptionTags,
                drinkCategory, drinkItem, allergies, ratings);
    }

    @Override
    public String toString() {
        return "DrinkRecipe{" +
                "creator='" + creator + '\'' +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", ingredients=" + ingredients +
                ", instructionSteps=" + instructionSteps +
                ", description='" + description + '\'' +
                ", descriptionTags=" + descriptionTags +
                ", drinkCategory='" + drinkCategory + '\'' +
                ", drinkItem='" + drinkItem + '\'' +
                ", allergies=" + allergies +
                ", ratings=" + ratings +
                '}';
    }
}

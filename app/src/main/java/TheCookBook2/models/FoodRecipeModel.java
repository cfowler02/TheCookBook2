package TheCookBook2.models;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;



public class FoodRecipeModel {
    private final String creator;
    private final String recipeTitle;
    private final Map<String, String> ingredients;
    private final LinkedList<String> instructionSteps;
    private final String description;
    private final Set<String> descriptionTags;
    private final int timeEstimate;
    private final String foodCategory;
    private final String foodItem;
    private final Set<String> allergies;
    private final Map<Integer, Integer> ratings;

    private FoodRecipeModel(String creator, String recipeTitle, Map<String, String> ingredients,
                            LinkedList<String> instructionSteps, String description, Set<String> descriptionTags,
                            int timeEstimate, String foodCategory, String foodItem, Set<String> allergies,
                            Map<Integer, Integer> ratings) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.instructionSteps = instructionSteps;
        this.description = description;
        this.descriptionTags = descriptionTags;
        this.timeEstimate = timeEstimate;
        this.foodCategory = foodCategory;
        this.foodItem = foodItem;
        this.allergies = allergies;
        this.ratings = ratings;
    }


    public String getCreator() {
        return creator;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public LinkedList<String> getInstructionSteps() {
        return instructionSteps;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getDescriptionTags() {
        return descriptionTags;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoodRecipeModel that = (FoodRecipeModel) o;
        return timeEstimate == that.timeEstimate && Objects.equals(creator, that.creator) &&
                Objects.equals(recipeTitle, that.recipeTitle) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(instructionSteps, that.instructionSteps) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionTags, that.descriptionTags) &&
                Objects.equals(foodCategory, that.foodCategory) &&
                Objects.equals(foodItem, that.foodItem) &&
                Objects.equals(allergies, that.allergies) &&
                Objects.equals(ratings, that.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, recipeTitle, ingredients, instructionSteps, description, descriptionTags,
                timeEstimate, foodCategory, foodItem, allergies, ratings);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String creator;
        private String recipeTitle;
        private Map<String, String> ingredients;
        private LinkedList<String> instructionSteps;
        private String description;
        private Set<String> descriptionTags;
        private int timeEstimate;
        private String foodCategory;
        private String foodItem;
        private Set<String> allergies;
        private Map<Integer, Integer> ratings;

        public Builder withCreator(String creator) {
            this.creator = creator;
            return this;
        }

        public Builder withRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
            return this;
        }

        public Builder withIngredients(Map<String, String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder withInstructionSteps(LinkedList<String> instructionSteps) {
            this.instructionSteps = instructionSteps;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDescriptionTags(Set<String> descriptionTags) {
            this.descriptionTags = descriptionTags;
            return this;
        }

        public Builder withTimeEstimate(int timeEstimate) {
            this.timeEstimate = timeEstimate;
            return this;
        }

        public Builder withFoodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public Builder withFoodItem(String foodItem) {
            this.foodItem = foodItem;
            return this;
        }

        public Builder withAllergies(Set<String> allergies) {
            this.allergies = allergies;
            return this;
        }

        public Builder withRatings(Map<Integer, Integer> ratings) {
            this.ratings = ratings;
            return this;
        }

        public FoodRecipeModel build() {
            return new FoodRecipeModel(creator, recipeTitle, ingredients, instructionSteps, description,
                    descriptionTags, timeEstimate, foodCategory, foodItem, allergies, ratings);
        }
    }
}



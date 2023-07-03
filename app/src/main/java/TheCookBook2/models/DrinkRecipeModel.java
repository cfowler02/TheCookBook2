package TheCookBook2.models;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DrinkRecipeModel {
    private final String creator;
    private final String recipeTitle;
    private final Map<String, String> ingredients;
    private final LinkedList<String> instructionSteps;
    private final String description;
    private final Set<String> descriptionTags;
    private final String drinkCategory;
    private final String drinkItem;
    private final Set<String> allergies;
    private final Map<Integer, Integer> ratings;

    private DrinkRecipeModel(String creator, String recipeTitle, Map<String, String> ingredients,
                             LinkedList<String> instructionSteps, String description, Set<String> descriptionTags,
                             String drinkCategory, String drinkItem, Set<String> allergies,
                             Map<Integer, Integer> ratings) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.instructionSteps = instructionSteps;
        this.description = description;
        this.descriptionTags = descriptionTags;
        this.drinkCategory = drinkCategory;
        this.drinkItem = drinkItem;
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


    public String getDrinkCategory() {
        return drinkCategory;
    }

    public String getDrinkItem() {
        return drinkItem;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
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
        private String drinkCategory;
        private String drinkItem;
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


        public Builder withDrinkCategory(String drinkCategory) {
            this.drinkCategory = drinkCategory;
            return this;
        }

        public Builder withDrinkItem(String drinkItem) {
            this.drinkItem = drinkItem;
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

        public DrinkRecipeModel build() {
            return new DrinkRecipeModel(creator, recipeTitle, ingredients, instructionSteps, description,
                    descriptionTags, drinkCategory, drinkItem, allergies, ratings);
        }
    }
}



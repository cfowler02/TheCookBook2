package TheCookBook2.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RateDrinkRecipeRequest.Builder.class)
public class RateDrinkRecipeRequest {
    private final String creator;
    private final String recipeTitle;
    private final int rating;
    private final String user;

    public RateDrinkRecipeRequest(String creator, String recipeTitle, int rating, String user) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
        this.rating = rating;
        this.user = user;
    }

    public String getCreator() {
        return creator;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public int getRating() {
        return rating;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "RateDrinkRecipeRequest{" +
                "creator='" + creator + '\'' +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", rating=" + rating +
                ", user='" + user + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static RateDrinkRecipeRequest.Builder builder() {
        return new RateDrinkRecipeRequest.Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String creator;
        private String recipeTitle;
        private int rating;
        private String user;

        public RateDrinkRecipeRequest.Builder withCreator(String creator) {
            this.creator = creator;
            return this;
        }
        public RateDrinkRecipeRequest.Builder withRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
            return this;
        }
        public RateDrinkRecipeRequest.Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }
        public RateDrinkRecipeRequest.Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public RateDrinkRecipeRequest build() {
            return new RateDrinkRecipeRequest(creator, recipeTitle, rating, user);
        }
    }
}

package TheCookBook2.activity.requests;

public class DeleteFoodRecipeRequest {
    private final String creator;
    private final String recipeTitle;

    public DeleteFoodRecipeRequest(String creator, String recipeTitle) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
    }

    public String getCreator() {
        return creator;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    @Override
    public String toString() {
        return "DeleteFoodRecipeRequest{" +
                "creator='" + creator + '\'' +
                ", recipeTitle='" + recipeTitle + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }


    public static class Builder {
        private String creator;
        private String recipeTitle;


        public Builder withCreator(String creator) {
            this.creator = creator;
            return this;
        }

        public Builder withRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
            return this;
        }

        public DeleteFoodRecipeRequest build() {
            return new DeleteFoodRecipeRequest(creator, recipeTitle);
        }
    }
}

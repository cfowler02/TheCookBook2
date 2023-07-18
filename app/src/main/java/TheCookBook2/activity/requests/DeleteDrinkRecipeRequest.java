package TheCookBook2.activity.requests;

public class DeleteDrinkRecipeRequest {
    private final String creator;
    private final String recipeTitle;

    public DeleteDrinkRecipeRequest(String creator, String recipeTitle) {
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
        return "DeleteDrinkRecipeRequest{" +
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

        public DeleteDrinkRecipeRequest build() {
            return new DeleteDrinkRecipeRequest(creator, recipeTitle);
        }
    }
}

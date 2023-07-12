package TheCookBook2.activity.requests;

public class ViewFoodRecipeRequest {

    public ViewFoodRecipeRequest() {
    }

    @Override
    public String toString() {
        return "ViewFoodRecipeRequest{}";
    }

    //CHECKSTYLE:OFF:Builder
    public static ViewFoodRecipeRequest.Builder builder() {
        return new ViewFoodRecipeRequest.Builder();
    }

    public static class Builder {
        public ViewFoodRecipeRequest build() {
            return new ViewFoodRecipeRequest();
        }
    }
}

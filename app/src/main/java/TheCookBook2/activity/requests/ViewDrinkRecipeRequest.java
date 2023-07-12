package TheCookBook2.activity.requests;

public class ViewDrinkRecipeRequest {
    public ViewDrinkRecipeRequest() {
    }

    @Override
    public String toString() {
        return "ViewDrinkRecipeRequest{}";
    }

    //CHECKSTYLE:OFF:Builder
    public static ViewDrinkRecipeRequest.Builder builder() {
        return new ViewDrinkRecipeRequest.Builder();
    }

    public static class Builder {
        public ViewDrinkRecipeRequest build() {
            return new ViewDrinkRecipeRequest();
        }
    }
}

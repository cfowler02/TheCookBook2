package TheCookBook2.activity.requests;

public class SearchFoodRecipeRequest {
    private final String criteria;
    private final String filter;

    private SearchFoodRecipeRequest(String criteria, String filter) {
        this.criteria = criteria;
        this.filter = filter;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getFilter() {
        return filter;
    }

    @Override
    public String toString() {
        return "SearchFoodRecipeRequest{" +
                "criteria='" + criteria + '\'' +
                ", filter='" + filter + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchFoodRecipeRequest.Builder builder() {
        return new SearchFoodRecipeRequest.Builder();
    }

    public static class Builder {
        private String criteria;
        private String filter;

        public SearchFoodRecipeRequest.Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchFoodRecipeRequest.Builder withFilter(String filter) {
            this.filter = filter;
            return this;
        }
        public SearchFoodRecipeRequest build() {
            return new SearchFoodRecipeRequest(criteria, filter);
        }
    }
}

package TheCookBook2.activity.requests;


public class SearchDrinkRecipeRequest {
    private final String criteria;
    private final String filter;

    private SearchDrinkRecipeRequest(String criteria, String filter) {
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
        return "SearchDrinkRecipeRequest{" +
                "criteria='" + criteria + '\'' +
                ", filter='" + filter + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchDrinkRecipeRequest.Builder builder() {
        return new SearchDrinkRecipeRequest.Builder();
    }


    public static class Builder {
        private String criteria;
        private String filter;

        public SearchDrinkRecipeRequest.Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchDrinkRecipeRequest.Builder withFilter(String filter) {
            this.filter = filter;
            return this;
        }

        public SearchDrinkRecipeRequest build() {
            return new SearchDrinkRecipeRequest(criteria, filter);
        }
    }
}

package TheCookBook2.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import TheCookBook2.activity.requests.SearchDrinkRecipeRequest;
import TheCookBook2.activity.results.SearchDrinkRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchDrinkRecipeLambda
        extends LambdaActivityRunner<SearchDrinkRecipeRequest, SearchDrinkRecipeResult>
        implements RequestHandler<LambdaRequest<SearchDrinkRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    /*
    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchDrinkRecipeRequest> input, Context context) {
        log.info("handleRequest");
        System.out.println("Search Drink Recipe LAMBDA HANDLE REQUEST");
        return super.runActivity(
                () -> input.fromQuery(query ->
                        SearchDrinkRecipeRequest.builder()
                                .withCriteria(query.get("q"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideSearchDrinkRecipeActivity().handleRequest(request)
        );
    }

     */

    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchDrinkRecipeRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        SearchDrinkRecipeRequest.builder()
                                .withFilter(path.get("filter"))
                                .withCriteria(path.get("criteria"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideSearchDrinkRecipeActivity().handleRequest(request)
        );
    }
}

package TheCookBook2.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import TheCookBook2.activity.requests.ViewDrinkRecipeRequest;
import TheCookBook2.activity.results.ViewDrinkRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewDrinkRecipeLambda
        extends LambdaActivityRunner<ViewDrinkRecipeRequest, ViewDrinkRecipeResult>
        implements RequestHandler<LambdaRequest<ViewDrinkRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<ViewDrinkRecipeRequest> input, Context context) {
        log.info("handleRequest");
        System.out.println("View Drink Recipe LAMBDA HANDLE REQUEST");
        return super.runActivity(
                () -> input.fromQuery(query ->
                        ViewDrinkRecipeRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideViewDrinkRecipeActivity().handleRequest(request)
        );
    }
}

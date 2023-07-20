package TheCookBook2.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import TheCookBook2.activity.requests.ViewFoodRecipeRequest;
import TheCookBook2.activity.results.ViewFoodRecipeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewFoodRecipeLambda
        extends LambdaActivityRunner<ViewFoodRecipeRequest, ViewFoodRecipeResult>
        implements RequestHandler<LambdaRequest<ViewFoodRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<ViewFoodRecipeRequest> input, Context context) {
        log.info("handleRequest");
        System.out.println("View Food Recipe LAMBDA HANDLE REQUEST");
        return super.runActivity(
                () -> input.fromQuery(query ->
                        ViewFoodRecipeRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideViewFoodRecipeActivity().handleRequest(request)
        );
    }
}

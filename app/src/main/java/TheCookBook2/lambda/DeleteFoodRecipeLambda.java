package TheCookBook2.lambda;


import TheCookBook2.activity.requests.DeleteFoodRecipeRequest;
import TheCookBook2.activity.results.DeleteFoodRecipeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteFoodRecipeLambda
        extends LambdaActivityRunner<DeleteFoodRecipeRequest, DeleteFoodRecipeResult>
        implements RequestHandler<LambdaRequest<DeleteFoodRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteFoodRecipeRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteFoodRecipeRequest.builder()
                                .withCreator(path.get("creator"))
                                .withRecipeTitle(path.get("recipeTitle"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteFoodRecipeActivity().handleRequest(request)
        );
    }
}

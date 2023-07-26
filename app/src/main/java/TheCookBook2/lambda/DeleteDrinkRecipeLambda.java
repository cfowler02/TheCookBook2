package TheCookBook2.lambda;


import TheCookBook2.activity.requests.DeleteDrinkRecipeRequest;
import TheCookBook2.activity.results.DeleteDrinkRecipeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteDrinkRecipeLambda
        extends LambdaActivityRunner<DeleteDrinkRecipeRequest, DeleteDrinkRecipeResult>
        implements RequestHandler<LambdaRequest<DeleteDrinkRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteDrinkRecipeRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteDrinkRecipeRequest.builder()
                                .withCreator(path.get("creator"))
                                .withRecipeTitle(path.get("recipeTitle"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteDrinkRecipeActivity().handleRequest(request)
        );
    }
}

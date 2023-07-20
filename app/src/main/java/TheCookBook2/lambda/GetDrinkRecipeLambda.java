package TheCookBook2.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import TheCookBook2.activity.requests.GetDrinkRecipeRequest;
import TheCookBook2.activity.results.GetDrinkRecipeResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetDrinkRecipeLambda
        extends LambdaActivityRunner<GetDrinkRecipeRequest, GetDrinkRecipeResult>
        implements RequestHandler<LambdaRequest<GetDrinkRecipeRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetDrinkRecipeRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetDrinkRecipeRequest.builder()
                                .withCreator(path.get("creator"))
                                .withRecipeTitle(path.get("recipeTitle"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetDrinkRecipeActivity().handleRequest(request)
        );
    }
}

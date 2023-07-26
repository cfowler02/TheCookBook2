package TheCookBook2.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import TheCookBook2.activity.requests.RateDrinkRecipeRequest;
import TheCookBook2.activity.results.RateDrinkRecipeResult;

public class RateDrinkRecipeLambda
        extends LambdaActivityRunner<RateDrinkRecipeRequest, RateDrinkRecipeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RateDrinkRecipeRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RateDrinkRecipeRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RateDrinkRecipeRequest unauthenticatedRequest = input.fromBody(RateDrinkRecipeRequest.class);
                    return input.fromUserClaims(claims ->
                            RateDrinkRecipeRequest.builder()
                                    .withCreator(unauthenticatedRequest.getCreator())
                                    .withRecipeTitle(unauthenticatedRequest.getRecipeTitle())
                                    .withRating(unauthenticatedRequest.getRating())
                                    .withUser(unauthenticatedRequest.getUser())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRateDrinkRecipeActivity().handleRequest(request)
        );
    }
}
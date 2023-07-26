package TheCookBook2.lambda;

import TheCookBook2.activity.requests.RateFoodRecipeRequest;
import TheCookBook2.activity.results.RateFoodRecipeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RateFoodRecipeLambda
        extends LambdaActivityRunner<RateFoodRecipeRequest, RateFoodRecipeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RateFoodRecipeRequest> , LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RateFoodRecipeRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RateFoodRecipeRequest unauthenticatedRequest = input.fromBody(RateFoodRecipeRequest.class);
                    return input.fromUserClaims(claims ->
                            RateFoodRecipeRequest.builder()
                                    .withCreator(unauthenticatedRequest.getCreator())
                                    .withRecipeTitle(unauthenticatedRequest.getRecipeTitle())
                                    .withRating(unauthenticatedRequest.getRating())
                                    .withUser(unauthenticatedRequest.getUser())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRateFoodRecipeActivity().handleRequest(request)
        );
    }
}
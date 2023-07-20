package TheCookBook2.lambda;


import TheCookBook2.activity.requests.CreateFoodRecipeRequest;
import TheCookBook2.activity.results.CreateFoodRecipeResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateFoodRecipeLambda
        extends LambdaActivityRunner<CreateFoodRecipeRequest, CreateFoodRecipeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateFoodRecipeRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateFoodRecipeRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateFoodRecipeRequest unauthenticatedRequest = input.fromBody(CreateFoodRecipeRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateFoodRecipeRequest.builder()
                                    .withCreator(unauthenticatedRequest.getCreator())
                                    .withRecipeTitle(unauthenticatedRequest.getRecipeTitle())
                                    .withIngredients(unauthenticatedRequest.getIngredients())
                                    .withInstructionSteps(unauthenticatedRequest.getInstructionSteps())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withDescriptionTags(unauthenticatedRequest.getDescriptionTags())
                                    .withTimeEstimate(unauthenticatedRequest.getTimeEstimate())
                                    .withFoodCategory(unauthenticatedRequest.getFoodCategory())
                                    .withFoodItem(unauthenticatedRequest.getFoodItem())
                                    .withAllergies(unauthenticatedRequest.getAllergies())
                                    .withRatings(unauthenticatedRequest.getRatings())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateFoodRecipeActivity().handleRequest(request)
        );
    }
}

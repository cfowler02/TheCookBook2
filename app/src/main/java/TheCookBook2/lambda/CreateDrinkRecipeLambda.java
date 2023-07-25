package TheCookBook2.lambda;


import TheCookBook2.activity.requests.CreateDrinkRecipeRequest;
import TheCookBook2.activity.results.CreateDrinkRecipeResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateDrinkRecipeLambda
        extends LambdaActivityRunner<CreateDrinkRecipeRequest, CreateDrinkRecipeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateDrinkRecipeRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateDrinkRecipeRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateDrinkRecipeRequest unauthenticatedRequest = input.fromBody(CreateDrinkRecipeRequest.class);
                    return CreateDrinkRecipeRequest.builder()
                                    .withCreator(unauthenticatedRequest.getCreator())
                                    .withRecipeTitle(unauthenticatedRequest.getRecipeTitle())
                                    .withIngredients(unauthenticatedRequest.getIngredients())
                                    .withInstructionSteps(unauthenticatedRequest.getInstructionSteps())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withDescriptionTags(unauthenticatedRequest.getDescriptionTags())
                                    .withDrinkCategory(unauthenticatedRequest.getDrinkCategory())
                                    .withDrinkItem(unauthenticatedRequest.getDrinkItem())
                                    .withAllergies(unauthenticatedRequest.getAllergies())
                                    .withRatings(unauthenticatedRequest.getRatings())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateDrinkRecipeActivity().handleRequest(request)
        );
    }
}

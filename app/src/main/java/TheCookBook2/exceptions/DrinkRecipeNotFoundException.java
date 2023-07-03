package TheCookBook2.exceptions;

/**
 * Exception to throw when the given keys of a drink recipe is not found in the database.
 */
public class DrinkRecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1230785223023147290L;

    /**
     * Exception with no message or cause.
     */
    public DrinkRecipeNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DrinkRecipeNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DrinkRecipeNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DrinkRecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

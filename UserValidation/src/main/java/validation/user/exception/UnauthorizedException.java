package validation.user.exception;

public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040277241578672598L;

	public UnauthorizedException(String message) {
        super(message);
    }
}

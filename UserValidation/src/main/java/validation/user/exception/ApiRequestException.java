package validation.user.exception;

public class ApiRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311451890605777060L;

	public ApiRequestException(String errorMessage) {
	    super(errorMessage);
	  }
}

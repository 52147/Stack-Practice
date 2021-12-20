package stackpractice1;

/**
 * Exception class for access in empty container such as stacks, queue, and
 * priority queues.
 */

public class UnderflowException extends RuntimeException {
	/**
	 * Construct this exception object.
	 * 
	 * @param message the error message
	 */
	public UnderflowException(String message) {
		super(message);

	}

}

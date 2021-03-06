/**
 * This is thrown when someone tries to access an element in a IDoubleMatrix that is beyond the end
 * of the indices.
 */
class OutOfBoundsException extends Exception {
	static final long serialVersionUID = 2304934980L;

	public OutOfBoundsException(String message) {
		super(message);
	}
}

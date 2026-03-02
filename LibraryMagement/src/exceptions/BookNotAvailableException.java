package exceptions;

public class BookNotAvailableException extends Exception {

	public BookNotAvailableException() {
		super();
	}

	public BookNotAvailableException(String message) {
		super(message);
	}

}

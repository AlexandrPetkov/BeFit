package command.exception;

public class IncorrectDataException extends Exception {

	private static final long serialVersionUID = -5205079409542506482L;

	public IncorrectDataException() {
		super();
	}

	public IncorrectDataException(String message, Exception e) {
		super(message, e);
	}

	public IncorrectDataException(String message) {
		super(message);
	}

	public IncorrectDataException(Exception e) {
		super(e);
	}

}

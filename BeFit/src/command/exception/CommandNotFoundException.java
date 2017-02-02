package command.exception;

public class CommandNotFoundException extends Exception{

	private static final long serialVersionUID = -3669473604358008080L;

	public CommandNotFoundException() {
		super();
	}

	public CommandNotFoundException(String message, Exception e) {
		super(message, e);
	}

	public CommandNotFoundException(String message) {
		super(message);
	}

	public CommandNotFoundException(Exception e) {
		super(e);
	}

	
}

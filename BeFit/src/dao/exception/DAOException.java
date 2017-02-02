package dao.exception;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = -6327399147373730042L;

	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	
}

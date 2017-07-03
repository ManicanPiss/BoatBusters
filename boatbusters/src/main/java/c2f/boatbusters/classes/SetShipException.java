package c2f.boatbusters.classes;

public class SetShipException extends Throwable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public SetShipException() {

	}

	public SetShipException(String message) {
		super(message);
	}

	public SetShipException(Throwable cause) {
		super(cause);
	}

	public SetShipException(String message, Throwable cause) {
		super(message, cause);
	}
}

package version1;

public class CoordinateOutOfBonds extends Exception {

	String msg;
	public CoordinateOutOfBonds() {
		// TODO Auto-generated constructor stub
	}

	public CoordinateOutOfBonds(String message) {
		super(message);
		
	}

	public CoordinateOutOfBonds(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CoordinateOutOfBonds(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CoordinateOutOfBonds(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return "CoordinateOutOfBonds Exception: "+this.msg;
	}

}

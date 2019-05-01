package version1;

/**
 * Exception class for ships.
 * Used when user wants the cell which is not empty.
 * or when the ship of the given size already exists.
 */

public class UnavailableShipException extends Exception {
	String msg;
	public UnavailableShipException(String msg) {
		super(msg);
		this.msg=msg;
	}
	public String toString() {
		return "UnavailableShipException: "+msg;
	}
}

package version1;

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

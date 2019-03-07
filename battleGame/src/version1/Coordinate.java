package version1;

public class Coordinate {
	int x, y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(String coord) {
		this.y=((int)coord.charAt(0))-((int)'A');
		this.x=Character.getNumericValue(coord.charAt(1));
	}
}

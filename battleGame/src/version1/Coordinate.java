package version1;

public class Coordinate {
	int x, y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(String coord) {
		this.x=((int)coord.charAt(0))-((int)'A');
		this.y=Character.getNumericValue(coord.charAt(1));
	}
	public static Coordinate stringToCoordinate(String coord) {
		Coordinate c=new Coordinate(coord);
		return c;
	}
	public static String coordinateToString(Coordinate c) {
		String s="";
		s+=(char)((int)'A'+c.x);
		s+=c.y;
//		System.out.println("Coordinate: "+s);
		return s;
	}
}

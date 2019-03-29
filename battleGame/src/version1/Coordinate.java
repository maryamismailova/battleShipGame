package version1;

public class Coordinate {
	int x, y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(String coord) throws CoordinateOutOfBonds{
		this.x=((int)coord.charAt(0))-((int)'A');
		this.y=Character.getNumericValue(coord.charAt(1));
		if(x>=10 || x<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=x<10");
		}
		else if(y>=10 || y<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=y<10");
		}
	}
	
	public static Coordinate stringToCoordinate(String coord) throws CoordinateOutOfBonds{
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

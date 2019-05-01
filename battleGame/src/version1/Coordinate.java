package battleGame.src.version1;

/**
 * Class which stores the x, y coordinates of the ship.
*/

public class Coordinate {
	int x, y;

	/*!
	 * Constructor
	 */
	public Coordinate(int x, int y)  throws CoordinateOutOfBonds {
		super();
		this.x = x;
		this.y = y;
		if(x>=10 || x<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=x<10");
		}
		else if(y>=10 || y<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=y<10");
		}
	}

	/*!
	 * Constructor
	 */
	public Coordinate(String coord) throws CoordinateOutOfBonds{
		if(coord.length()<2)throw new CoordinateOutOfBonds("Inappropriate coordinate!");
		this.x=((int)coord.charAt(0))-((int)'A');
		this.y=Character.getNumericValue(coord.charAt(1));
		if(x>=10 || x<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=x<10");
		}
		else if(y>=10 || y<0) {
			throw new CoordinateOutOfBonds("Coordinates: "+this.x+" "+this.y +": 0<=y<10");
		}
	}
	/*!
	 * Convert coordinate of \a String type into integers.
	 */
	public static Coordinate stringToCoordinate(String coord) throws CoordinateOutOfBonds{
		Coordinate c=new Coordinate(coord);
		return c;
	}

	/*!
	 * Convert coordinate into \a String.
	 */
	public static String coordinateToString(Coordinate c) {
		String s="";
		s+=(char)((int)'A'+c.x);
		s+=c.y;
//		System.out.println("Coordinate: "+s);
		return s;
	}

	public String toString() {
		String s="";
		s+=(char)((int)'A'+this.x);
		s+=this.y;
		return s;
	}
}

package versionGUI;

public class Coordinate {
	int x, y;

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
	
	public static  void sortCoordinates(Coordinate[] unsorted) throws UnavailableShipException{
		if(unsorted.length!=1) {
			int direction=-1;//0 - vertical, 1 - horizontal
			if(unsorted[0].x==unsorted[1].x)direction =0;
			else if(unsorted[0].y==unsorted[1].y)direction=1;
			
			for(int i=1;i<unsorted.length;i++) {
				if(direction==0) {
					if(unsorted[i].x!=unsorted[i-1].x)throw new UnavailableShipException("Direction error!");
				}
				else if(direction==1) {
					if(unsorted[i].y!=unsorted[i-1].y)throw new UnavailableShipException("Direction error!");				
				}
				else {
					throw new UnavailableShipException("Direction error!");		
				}
			}
			System.out.println("Direction "+direction);
			for(int i=1;i<unsorted.length;i++) {
				int cursor=i;
				for(int j=i-1;j>=0;j--) {
					if(direction==0) {
						if(unsorted[cursor].y<unsorted[j].y) {
							int copy=unsorted[j].y;
							unsorted[j].y=unsorted[cursor].y;
							unsorted[cursor].y=copy;
							cursor=j;
						}else {
							break;
						}
					}else if(direction==1) {
						if(unsorted[cursor].x<unsorted[j].x) {
							int copy=unsorted[j].x;
							unsorted[j].x=unsorted[cursor].x;
							unsorted[cursor].x=copy;
							cursor=j;
						}else {
							break;
						}
					}
				}
			}
			System.out.println("sorted:");
			for(int i=0;i<unsorted.length;i++) {
				System.out.println(""+unsorted[i]+" ");
			}
			
		}
//		
//		return unsorted;
	}
	
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
	
	public boolean equals(Coordinate c) {
		if(c.x ==this.x && c.y==this.y) return true;
		return false;
		
	}
}

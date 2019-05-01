package battleGame.src.version1;

import java.util.ArrayList;

/**
 * Class to store the coordinates of ships and the hits that make player.
*/
public class Ship {
	Coordinate[] coord;	//! array to hold coordinates
	ArrayList<Coordinate> hits;	//! array list to store the hits coordinates
	
	/*!
	 * Constructor
	 */
	public Ship(Coordinate[] coord) {
		this.coord=coord;
		hits=new ArrayList<Coordinate>();
	}
	
	public String toString() {
		String s="";
		for(int i=0;i<coord.length;i++) {
			s+=Coordinate.coordinateToString(coord[i])+" ";
		}
		return s;
	}
	
	/*!
	 * Check if ship contains the coordinate
	 * @param c - coordinate to check
	 */
	public boolean contains(Coordinate c) {
		for(int i=0;i<coord.length;i++) {
			if(coord[i].x == c.x && coord[i].y == c.y) return true;
		}
		return false;
	}
	
	/*!
	 * Check if ship contains the coordinate
	 * @param c - coordinate to check
	 */
	public boolean containsInN(Coordinate c) {
		for(int i=0;i<coord.length;i++) {
			if(coord[i].x == c.x && coord[i].y == c.y) return true;
		}
		return false;
	}
	
	/*!
	 * Check if ship contains the hit coordinate
	 * @param c - coordinate to check
	 */
	public boolean containsHit(Coordinate c) {
        for(int i=0;i<hits.size();i++) {
            if(hits.get(i).x == c.x && hits.get(i).y == c.y) return true;
        }
        return false;
    }
	
	/*!
	 * Check if coordinate was hit
	 * @param c - coordinate to check
	 */
	public boolean wasHit(Coordinate c) {
		for(int i=0;i<hits.size();i++) {
			if(hits.get(i).x==c.x && hits.get(i).y==c.y) return true;
		}
		return false;
	}
	
	/*!
	 * Add the hit
	 * @param c - coordinate to add
	 */
	public void addHit(Coordinate c) {
		hits.add(c);
	}
}

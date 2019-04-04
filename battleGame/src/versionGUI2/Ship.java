package versionGUI2;

import java.util.ArrayList;

public class Ship {
	Coordinate[] coord;
	ArrayList<Coordinate> hits;
	
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
	public boolean contains(Coordinate c) {
		for(int i=0;i<coord.length;i++) {
			if(coord[i].x == c.x && coord[i].y == c.y) return true;
		}
		return false;
	}
	public boolean containsInN(Coordinate c) {
		for(int i=0;i<coord.length;i++) {
			if(coord[i].x == c.x && coord[i].y == c.y) return true;
		}
		return false;
	}
	
	public boolean containsHit(Coordinate c) {
        for(int i=0;i<hits.size();i++) {
            if(hits.get(i).x == c.x && hits.get(i).y == c.y) return true;
        }
        return false;
    }
	
	public boolean wasHit(Coordinate c) {
		for(int i=0;i<hits.size();i++) {
			if(hits.get(i).x==c.x && hits.get(i).y==c.y) return true;
		}
		return false;
	}
	
	public void addHit(Coordinate c) {
		hits.add(c);
	}
}

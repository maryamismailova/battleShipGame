package version1;

import java.util.Scanner;

/**
 * Abstract class for player.
 * All players will have the same attributes and methods like in this class.
 */

public abstract class Player{
	Ship[] ships; //! array to store the ships
	String name;	//! string to store the name of player
	public abstract void initializeBoard();
	public abstract Coordinate makeMove();

	/*!
	 * Add ship to the player's board.
	 */
	public Ship addAShip(String coordinateString, int shipCount) throws CoordinateOutOfBonds , UnavailableShipException {
		String[] points=coordinateString.split(" ");
		Coordinate[] coordinates=new Coordinate[points.length];
		for(int j=0;j<coordinates.length;j++) {
			coordinates[j]=new Coordinate(points[j]);
		}
		//CHECK if coordinates are relevant
		int direction=-1;
		if(coordinates.length==1)direction=0;
		else {
			int curDir;
			for(int i=0;i<coordinates.length-1;i++) {
				curDir=directionOfCoordinates(coordinates[i], coordinates[i+1]);
				if(direction==-1)direction=curDir;
				else if(curDir!=direction) throw new CoordinateOutOfBonds("Inappropriate direction!");
			}
		}
		
		//check if ships of the size already exists
		for(int i=0;i<shipCount;i++) {
			if(ships[i].coord.length==coordinates.length) {
				throw new UnavailableShipException("Ship of size exists!");
			}
			//check if there is interception with existing ships 
			for(int j=0;j<coordinates.length;j++) {
				if(ships[i].contains(coordinates[j])==true) {
					throw new UnavailableShipException("Coordinate not empty!");
				}
			}
		}
		return new Ship(coordinates);
	}
	
	/*!
	 * Add ship to the player's board.
	 */
	public Ship addAShip(Coordinate coordinates[], int shipCount) throws CoordinateOutOfBonds , UnavailableShipException {
		//CHECK if coordinates are relevant
		int direction=-1;
		if(coordinates.length==1)direction=0;
		else {
			int curDir;
			for(int i=0;i<coordinates.length-1;i++) {
				curDir=directionOfCoordinates(coordinates[i], coordinates[i+1]);
				if(direction==-1)direction=curDir;
				else if(curDir!=direction) throw new CoordinateOutOfBonds("Inappropriate direction!");
			}
		}
		//check if ships of the size already exists
		for(int i=0;i<shipCount;i++) {
			if(ships[i].coord.length==coordinates.length) {
				throw new UnavailableShipException("Ship of size exists!");
			}
			//check if there is interception with existing ships 
			for(int j=0;j<coordinates.length;j++) {
				if(ships[i].contains(coordinates[j])==true) {
					throw new UnavailableShipException("Coordinate not empty!");
				}
			}
		}
		return new Ship(coordinates);
	}
	
	/*!
	 * Check the direction of coordinates.
	 \brief Look for the position of 2 coordinates and check if they are neighbors vertically or horizontally.
	 */
	public int directionOfCoordinates(Coordinate p1, Coordinate p2) throws CoordinateOutOfBonds{
		//horizontal == 1
		//vertical == 2
		//1 point =0
		//inappropriate coordinate  - throw CoordinateOuOfBonds
		if(p1.x==p2.x && p1.y==p2.y)return 0;
		else if(p1.x==p2.x && p1.y==p2.y-1) return 2;
		else if(p1.y==p2.y && p1.x==p2.x-1) return 1;
		else {
			throw new CoordinateOutOfBonds("Ianappropriate Coordinates!");
//			return 0;
		}
		
	}
	
	/*!
	 * Add hit if player contains the coordinate.
	 * @param c - coordinate to add
	 */
	public void addHitOnPlayer(Coordinate c) {
		for(int i=0;i<ships.length;i++) {
			if(ships[i].contains(c)==true) {
				ships[i].addHit(c);
				break;
			}
		}
	}
	

}

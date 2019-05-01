package versionGUI;

import java.util.Scanner;

/**
 * Class for player if player-player mode is chosen.
 * Extends \a Player abstract class
*/

public class ClientPlayer extends Player {

	@Override
	/*!
	 * Read from player the coordinates of the ship and add them as one ship if everything is okay.
	 * Can read 5 coordinates maximum in one line.
	 * The coordinates should be separated.
	 * For example, the input looks like: A1 A2 
	*/
	public void initializeBoard() {
		System.out.println("Player "+this.name+" enter boat coordinates\n");
		for(int i=0;i<5;i++) {
			//get Boat coordinates
			System.out.println("Boat "+i);
			Scanner in=new Scanner(System.in);
			String pointString=in.nextLine();
			try {
				ships[i]=addAShip(pointString, i);
			} catch (CoordinateOutOfBonds | UnavailableShipException e) {
//				e.printStackTrace();
				System.out.println("try once more: "+e.getMessage());
				i--;
				continue;
			}
			
			System.out.println("Boat coord: "+ships[i]);
		}
	}
	
	/*! Constructor
	 \param name - the name of player 
	 */
	public ClientPlayer(String name) {
		this.name=name;
		ships=new Ship[5]; 
	}
	
//	public void addHitOnPlayer(Coordinate c) {
//		for(int i=0;i<ships.length;i++) {
//			if(ships[i].contains(c)==true) {
//				ships[i].addHit(c);
//				break;
//			}
//		}
//	}
//	
	@Override
	/*!
	 * Read from player the coordinate
	*/
	public Coordinate makeMove(){
		System.out.println("Player "+this.name+" make a move: ");
		Scanner in=new Scanner(System.in);
		Coordinate coord;
		try {
			coord = new Coordinate(in.nextLine());
			return coord;
		} catch (CoordinateOutOfBonds e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
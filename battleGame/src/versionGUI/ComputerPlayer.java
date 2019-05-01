package versionGUI;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for computer if computer-client mode is chosen.
 * Extends \a Player abstract class
*/
public class ComputerPlayer extends Player {

	ArrayList<Coordinate> madeMoves=new ArrayList<Coordinate>();

	/*! Constructor
	 \param name - name of player 
	 */
	public ComputerPlayer(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		ships=new Ship[5]; 
	}

	@Override
	/*! Initialize the board of computer.
	 * The computer sets the coordinates randomly but taking into account the direction and position of coordinates.
	*/
	public void initializeBoard() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++) {
			//get Boat coordinates
			int curLength=i+1;
			int direction=(int)(Math.random()*2);//0 - hor, 1 - ver
			Coordinate[] newBoatCoord=new Coordinate[curLength];
			int x=(int)(Math.random()*GameBoard.BOARDIM);
			int y=(int)(Math.random()*GameBoard.BOARDIM);
			try {
				newBoatCoord[0]=new Coordinate(x,y);
			
				for(int j=0;j<ships.length;j++) {
					if(ships[i]!=null && ships[i].contains(newBoatCoord[i])) {
						i--;
						continue;
					}
				}
			
				int nextX=x, nextY=y;
				for(int j=1;j<curLength;j++) {
					if(direction==0) {
						nextX+=1;
					}else {
						nextY+=1;
					}
					newBoatCoord[j]=new Coordinate(nextX, nextY);
				}
				ships[i]=addAShip(newBoatCoord, i);
			} catch (CoordinateOutOfBonds | UnavailableShipException e) {
//				e.printStackTrace();
//				System.out.println("try once more: "+e.getMessage());
				i--;
				continue;
			}
			
			System.out.println("Boat coord: "+ships[i]);
		}
	}

	@Override
	/*!
	 * Make move by randomly choosing the coordinate.
	 */
	public Coordinate makeMove() {
		System.out.println("Player "+this.name+" makes a move: ");
		boolean moveMade=false;
		Coordinate coord=null;
		while(!moveMade) {
			try {
				int x=(int)(Math.random()*GameBoard.BOARDIM);
				int y=(int)(Math.random()*GameBoard.BOARDIM);
				coord=new Coordinate(x,y);
				if(madeMoves.contains(coord))continue;
				else {
					madeMoves.add(coord);
					moveMade=true;
				}
			} catch (CoordinateOutOfBonds e) {}	
		}
		return coord;
	}



}

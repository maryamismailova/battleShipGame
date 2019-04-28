package version1;

import java.util.Scanner;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		this.name=name;
		ships=new Ship[5]; 
	}

	@Override
	public void initializeBoard() {
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
				i--;
				continue;
			}
			
//			System.out.println("Boat coord: "+ships[i]);
		}
	}

	@Override
	public Coordinate makeMove() {
		boolean moveMade=false;
		Coordinate coord=null;
		while(!moveMade) {
			try {
				int x=(int)(Math.random()*GameBoard.BOARDIM);
				int y=(int)(Math.random()*GameBoard.BOARDIM);
				coord=new Coordinate(x,y);
				moveMade=true;
			} catch (CoordinateOutOfBonds e) {}	
		}
		System.out.println("Player "+this.name+" makes a move: "+coord);
		return coord;
	}


}

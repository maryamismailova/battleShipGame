package versionGUI2;

import java.util.ArrayList;

public class GameClass {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player[] players=new Player[2];
		players[0]=new ClientPlayer("Player1");
		players[1]=new ClientPlayer("Player2");
		
		GameBoard game=new GameBoard(players);
		
		GameBoardGUI gameGUI=new GameBoardGUI(game);
		
		/*try {
		//test for coordinates removal
		Coordinate x=new Coordinate(1,8);
		Coordinate y=new Coordinate(0,3);
		Coordinate z=new Coordinate(1,5);
		
		Coordinate array[]= {x, y,z};
		for(int i=0;i<array.length;i++) {
			System.out.print(""+array[i]+" ");
		}
		System.out.println("\n after");
		Coordinate.sortCoordinates(array);
		for(int i=0;i<array.length;i++) {
			System.out.print(""+array[i]+" ");
		}
		System.out.println("");
		}
		catch(UnavailableShipException e) {
			System.out.println(e.getMessage());
		} catch (CoordinateOutOfBonds e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
/*		Player p1=new ComputerPlayer("p1");
		p1.initializeBoard();
		GameBoard.printBoard(p1);
		for(int i=0;i<10;i++) {
			System.out.println("Make move: "+p1.makeMove());
		}
*/

//		
//		System.out.println("evvdv");
//		Coordinate c=new Coordinate("B2");
//		System.out.println("c.x="+c.x+" c.y="+c.y);
//		String s=Coordinate.coordinateToString(c);
//		System.out.println("Coord: "+s);
	}

}

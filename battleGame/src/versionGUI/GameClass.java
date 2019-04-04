package versionGUI;

public class GameClass {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player[] players=new Player[2];
		players[0]=new ComputerPlayer("Computer");
		players[1]=new ClientPlayer("Client");
		/*
		GameBoard game=new GameBoard(players);
		game.printBoard(game.players[0]);
		game.printBoard(game.players[1]);
		game.startGame();
		*/
		Design frame=new Design(players);
		
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

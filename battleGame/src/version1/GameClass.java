package version1;

public class GameClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard game=new GameBoard();
		game.printBoard(game.players[0]);
		game.printBoard(game.players[1]);
//		Coordinate c=new Coordinate("B2");
//		System.out.println("c.x="+c.x+" c.y="+c.y);
//		String s=Coordinate.coordinateToString(c);
//		System.out.println("Coord: "+s);
	}

}

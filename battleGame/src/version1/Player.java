package version1;

public abstract class Player{
	char board[][];
	char opponentBoard[][];
	int nbOfHits;
	String name;
	public abstract void initializeBoard();
	public abstract Coordinate makeMove();
}

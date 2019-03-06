package version1;

public abstract class Player{
	char board[][];
	String name;
	public abstract void initializeBoard();
	public abstract void makeMove(String move);
}

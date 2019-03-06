package version1;

public class GameBoard {
	Player[] players;
	int currentPlayer;
	public boolean checkIfHit(String move) {
		return false;
		
	}
	public boolean checkIfKill(String move) {
		return false;
	}
	public boolean checkIfWin(String move) {
		return false;
	}
	public void printBoard(Player p) {
		System.out.println("Player: "+p.name);
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(p.board[i][j]);
			}
			System.out.println();
		}
	}
	public GameBoard() {
		players=new ClientPlayer[2];
		players[0]=new ClientPlayer("player1");
		players[1]=new ClientPlayer("player 2");
		players[0].initializeBoard();
		players[1].initializeBoard();
	}
}

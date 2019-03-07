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
		char board[][]=new char[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				board[i][j]=' ';
			}
//			System.out.println();
		}
		for(int i=0;i<p.ships.length;i++) {
			for(int j=0;j<p.ships[i].coord.length;j++) {
				int x=p.ships[i].coord[j].x;
				int y=p.ships[i].coord[j].y;
				board[y][x]='+';
			}
		}
		String boardString="";
		String floor="-------------------------\n";
		boardString+=floor;
		for(int i=0;i<10;i++) {
			boardString+="|";
			for(int j=0;j<10;j++) {
				boardString+=board[i][j]+"|";
			}
			
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
	}
	
	public GameBoard() {
		players=new ClientPlayer[2];
		players[0]=new ClientPlayer("player1");
		players[1]=new ClientPlayer("player 2");
		players[0].initializeBoard();
		players[1].initializeBoard();
	}
}

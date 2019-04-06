package versionGUI;

public class GameBoard {
	final public  static int BOARDIM=10;
	Player[] players;
	int currentPlayer;
	boolean gameWon=false;

	
	public boolean checkIfHit(Coordinate move) {
		int nextPlayer=1-currentPlayer;
		for(int i=0;i<players[nextPlayer].ships.length;i++) {
				if(players[nextPlayer].ships[i].contains(move)==true && players[nextPlayer].ships[i].containsHit(move)==false) return true;
		}
		return false;
	}
	
	public boolean checkIfKill(Coordinate move) {
		int nextPlayer;
		if(currentPlayer==0)nextPlayer=1;
		else nextPlayer=0;
		for(int i=0;i<players[nextPlayer].ships.length;i++) {
			if(players[nextPlayer].ships[i].contains(move)==true) {
				if(players[nextPlayer].ships[i].hits.size()==players[nextPlayer].ships[i].coord.length) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkIfWin() {
		int nextPlayer;
		if(currentPlayer==0)nextPlayer=1;
		else nextPlayer=0;
		for(int i=0;i<players[nextPlayer].ships.length;i++) {
			if(players[nextPlayer].ships[i].hits.size()!=players[nextPlayer].ships[i].coord.length) {
				return false;
			}
		}
		return true;
	}
	
	public final static void clearConsole()//DOESN'T WORK !
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else if(os.contains("Linux"))
	        {
	            Runtime.getRuntime().exec("clear");
	        }else {
	        	for(int i=0;i<50;i++)System.out.println("\r\n");
	        }
//	        System.out.println("Next player!\n");
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}

	
	public void printHitsBoard(Player p) {
		System.out.println("Player's game board!");
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
				if(p.ships[i].containsHit(p.ships[i].coord[j])==true) board[y][x]='x';
			}
		}
		String boardString="";
		String floor="----------------------------------------------\n";
		boardString+=floor;
		for(int i=0;i<10;i++) {
			boardString+="| ";
			for(int j=0;j<10;j++) {
				boardString+=board[i][j]+" | ";
			}
			
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
	}
	
	
	public static void printBoard(Player p) {
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
				if(p.ships[i].containsHit(p.ships[i].coord[j])==true) board[y][x]='-';
				else board[y][x]='+';
			}
		}
		String boardString="";
		String floor="----------------------------------------------\n";
		boardString+=floor;
		for(int i=0;i<10;i++) {
			boardString+="| ";
			for(int j=0;j<10;j++) {
				boardString+=board[i][j]+" | ";
			}
			
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
	}
	
	public GameBoard() {
		this.currentPlayer=0;
		players=new ClientPlayer[2];
		players[0]=new ClientPlayer("player1");
		players[1]=new ClientPlayer("player 2");
//        players[0].initializeBoard();
//        players[1].initializeBoard();
	}
	public GameBoard(Player [] players) {
		this.players=players;
		this.currentPlayer=0;
//        players[0].initializeBoard();
//        players[1].initializeBoard();
	}
	
	 public void startGame() {
	        currentPlayer = 0;
	        while(gameWon==false ) {
	        	int nextPlayer;
	        	if(currentPlayer==0)nextPlayer=1;
	        	else nextPlayer=0;
	        	
	        	printHitsBoard(players[nextPlayer]);
	            Coordinate playerMove=null;
	            while(playerMove==null)playerMove=players[currentPlayer].makeMove();
            	
	            System.out.println("Player : "+players[currentPlayer].name+" move: "+Coordinate.coordinateToString(playerMove));
	            
	            while(checkIfHit(playerMove)==true /*&& gameWon==false*/) {
	            	System.out.println("Player: "+players[currentPlayer].name+" hit!");
	                players[nextPlayer].addHitOnPlayer(playerMove);
	                if(checkIfKill(playerMove)==true) {
	                    System.out.println("Player "+players[currentPlayer].name+" kills");
	                    if(checkIfWin()==true) {
	                        System.out.println("Player "+players[currentPlayer].name+" wins");
	                        gameWon=true;
	                        break;
	                    }
	                }
	                printHitsBoard(players[nextPlayer]);
	                playerMove=null;
	                while(playerMove==null)playerMove=players[currentPlayer].makeMove();	                
	            }
	            clearConsole();
	            currentPlayer=nextPlayer;
	            if(gameWon==false) {
		            System.out.println("Player: "+players[currentPlayer].name+" no hit!");
		            System.out.println("Next Player!");
	            }
	        }
	 }
}

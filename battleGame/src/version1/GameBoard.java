package version1;

import java.util.Scanner;

/**
 * Class for the board of players.
 * Class to check if player hit, kill the ships and win the game.
 * Class which have the method starting the game.
 */

public class GameBoard {
	final static int BOARDIM=10;
	Player[] players;
	int currentPlayer;
	boolean gameWon=false;
	
	/*!
	 * Check if hit 
	 */
	public boolean checkIfHit(Coordinate move) {
		int nextPlayer;
		if(currentPlayer==0)nextPlayer=1;
		else nextPlayer=0;
		for(int i=0;i<players[nextPlayer].ships.length;i++) {
				if(players[nextPlayer].ships[i].contains(move)==true && players[nextPlayer].ships[i].containsHit(move)==false) return true;
		}
		return false;
	}
	
	/*!
	 * Check if kill the ship 
	 */
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
	
	/*!
	 * Check if win the game 
	 */
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
	
	/*!
	 * Clear the console. 
	 */
	public final static void clearConsole()//DOESN'T WORK !
	{
		
		 System.out.print("\033c");
		 System.out.flush();
//	    try
//	    {
//	        final String os = System.getProperty("os.name");
//
//	        if (os.contains("Windows"))
//	        {
//	            Runtime.getRuntime().exec("cls");
//	        }
//	        else if(os.contains("Linux"))
//	        {
//	            Runtime.getRuntime().exec("clear");
//	        }else {
//	        	for(int i=0;i<50;i++)System.out.println("\r\n");
//	        }
////	        System.out.println("Next player!\n");
//	    }
//	    catch (final Exception e)
//	    {
//	        //  Handle any exceptions.
//	    }
	}

	/*!
	 * Prints the board with the player's hits. 
	 */
	public void printHitsBoard(Player p, String message) {
		System.out.println(message);
		char board[][]=new char[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				board[i][j]=' ';
			}
		}
		for(int i=0;i<p.ships.length;i++) {
			for(int j=0;j<p.ships[i].coord.length;j++) {
				int x=p.ships[i].coord[j].x;
				int y=p.ships[i].coord[j].y;
				if(p.ships[i].containsHit(p.ships[i].coord[j])==true) board[y][x]='x';
			}
		}
		String boardString="";
		String floor="-------------------------------------------------\n";
		boardString+=floor;
		for(int i=0;i<11;i++) {
			boardString+="| ";
			for(int j=0;j<11;j++) {
				if(i==0 && j>=1) boardString+=Character.toString((char)('A'+j-1))+" | ";
				else if(j==0 && i>=1)boardString+=Integer.toString(i-1)+" | ";
				else if(i>0 && j>0)boardString+=board[i-1][j-1]+" | ";
				else boardString+="  | ";
//				boardString+=board[i][j]+" | ";
			}
			
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
		
		System.out.println("\n");
	}
	
	/*!
	 * Prints the player's board.
	 */
	public static void printBoard(Player p) {
		System.out.println("Player "+p.name+"'s board (+ for ships, - for hit ships):");
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
		String floor="-------------------------------------------------\n";
		boardString+=floor;
		for(int i=0;i<11;i++) {
			boardString+="| ";
			for(int j=0;j<11;j++) {
				if(i==0 && j>=1) boardString+=Character.toString((char)('A'+j-1))+" | ";
				else if(j==0 && i>=1)boardString+=Integer.toString(i-1)+" | ";
				else if(i>0 && j>0)boardString+=board[i-1][j-1]+" | ";
				else boardString+="  | ";
//				boardString+=board[i][j]+" | ";
			}
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
		System.out.println("\n");
	}
	
	/*!
	 * Prints the board status of opponent's board and current player's board. 
	 */
	public void printGameBoardsStatus(Player opponent, Player current) {
//		System.out.println(message);
		char opBoard[][]=new char[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				opBoard[i][j]=' ';
			}
		}
		for(int i=0;i<opponent.ships.length;i++) {
			for(int j=0;j<opponent.ships[i].coord.length;j++) {
				int x=opponent.ships[i].coord[j].x;
				int y=opponent.ships[i].coord[j].y;
				if(opponent.ships[i].containsHit(opponent.ships[i].coord[j])==true) opBoard[y][x]='x';
			}
		}
		
		char board[][]=new char[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				board[i][j]=' ';
			}
//			System.out.println();
		}
		for(int i=0;i<current.ships.length;i++) {
			for(int j=0;j<current.ships[i].coord.length;j++) {
				int x=current.ships[i].coord[j].x;
				int y=current.ships[i].coord[j].y;
				if(current.ships[i].containsHit(current.ships[i].coord[j])==true) board[y][x]='-';
				else board[y][x]='+';
			}
		}
		String boardString="Opponent's board status \t\t\t\t Your Board Status\n";
		String floor="---------------------------------------------\t\t---------------------------------------------\n";
		boardString+=floor;
		for(int i=0;i<11;i++) {
			boardString+="| ";
			for(int j=0;j<11;j++) {
				if(i==0 && j>=1) boardString+=Character.toString((char)('A'+j-1))+" | ";
				else if(j==0 && i>=1)boardString+=Integer.toString(i-1)+" | ";
				else if (i>0 && j>0)boardString+=opBoard[i-1][j-1]+" | ";
				else boardString+="  | ";
			}
			boardString+="\t\t| ";
			for(int j=0;j<11;j++) {
				if(i==0 && j>=1) boardString+=Character.toString((char)('A'+j-1))+" | ";
				else if(j==0 && i>=1)boardString+=Integer.toString(i-1)+" | ";
				else if(i>0 && j>0)boardString+=board[i-1][j-1]+" | ";
				else boardString+="  | ";
//				boardString+=board[i][j]+" | ";
			}
			boardString+="\n"+floor;
		}
		System.out.println(boardString);
		
		System.out.println("\n");		
	}
	
	/*!
	 * Constructor.
	 * \brief Initialize the players and their boards. 
	 */
	public GameBoard() {
		players=new ClientPlayer[2];
		players[0]=new ClientPlayer("player1");
		players[1]=new ClientPlayer("player 2");
        players[0].initializeBoard();
        players[1].initializeBoard();
	}
	
	/*!
	 * Constructor.
	 * \brief Initialize players boards 
	 */
	public GameBoard(Player [] players) {
		this.players=players;
        players[0].initializeBoard();
        players[1].initializeBoard();
	}
	
	/*!
	 * Method that begins the game between players. 
	 */
	 public void startGame() {
	        currentPlayer = 0;
	        while(gameWon==false ) {
	        	int nextPlayer=1-currentPlayer;
	        	printGameBoardsStatus(players[nextPlayer], players[currentPlayer]);
	        	Coordinate playerMove=null;
	            while(playerMove==null)playerMove=players[currentPlayer].makeMove();
            	
	            System.out.println("Player : "+players[currentPlayer].name+" move: "+Coordinate.coordinateToString(playerMove));
	            
	            while(checkIfHit(playerMove)==true /*&& gameWon==false*/) {
	            	System.out.println("\nPlayer: "+players[currentPlayer].name+" hit!");
	                players[nextPlayer].addHitOnPlayer(playerMove);
	                if(checkIfKill(playerMove)==true) {
	                    System.out.println("Player "+players[currentPlayer].name+" kills");
	                    if(checkIfWin()==true) {
	                    	System.out.println("\n\nCongratulations!!!");
	                        System.out.println("Player "+players[currentPlayer].name+" wins!");
	                        System.out.println("Sorry, player "+players[nextPlayer]+" :(");
	                        gameWon=true;
	                        break;
	                    }
	                }
	                printHitsBoard(players[nextPlayer], "Opponent's board status");
	                playerMove=null;
	                while(playerMove==null)playerMove=players[currentPlayer].makeMove();	                
	            }
	            if(gameWon==false) {
		            System.out.println("Player: "+players[currentPlayer].name+" no hit!! :(");
		            if(players[currentPlayer] instanceof ClientPlayer) {
		            	System.out.println("Press any key to continue: ");
		            	Scanner in=new Scanner(System.in);
		            	in.nextLine();		            	
		            }
		            System.out.println("Next Player!");
	            }
	            clearConsole();
	            currentPlayer=nextPlayer;
	        }
	 }
}

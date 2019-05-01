package battleGame.src.version1;

import java.util.Scanner;

/** Main class of console version. 
*/
public class GameClass {

	public static void main(String[] args) {
		/*! Allow user to choose the mode of game.
		*/
		System.out.println("Choose game mode:\n 1 - Player VS Computer\n 2 - Player1 VS Player2\n");
		Scanner input=new Scanner(System.in);
		Player[] players=new Player[2];
		int mode=input.nextInt();
		
		/*!
		 * After user choose the mode, the game begins.
		 */
		if(mode==1) {
			players[1]=new ComputerPlayer("Computer");
			players[0]=new ClientPlayer("Client");

			GameBoard game=new GameBoard(players);
			game.startGame();
		}else if(mode ==2 ) {
			players[0]=new ClientPlayer("Computer");
			players[1]=new ClientPlayer("Client");			
		
			GameBoard game=new GameBoard(players);
			game.startGame();
		}else {
			System.out.println("Incorrect input!");
		}

		
	}

}

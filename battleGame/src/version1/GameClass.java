package version1;

import java.util.Scanner;

public class GameClass {

	public static void main(String[] args) {
		System.out.println("Choose game mode:\n 1 - Player VS Computer\n 2 - Player1 VS Player2\n");
		Scanner input=new Scanner(System.in);
		Player[] players=new Player[2];
		int mode=input.nextInt();
		if(mode==1) {
			players[1]=new ComputerPlayer("Computer");
			players[0]=new ClientPlayer("Client");

			GameBoard game=new GameBoard(players);
			game.startGame();
		}else if(mode ==2 ) {
			players[0]=new ComputerPlayer("Computer");
			players[1]=new ClientPlayer("Client");			
		
			GameBoard game=new GameBoard(players);
			game.startGame();
		}else {
			System.out.println("Incorrect input!");
		}

		
	}

}

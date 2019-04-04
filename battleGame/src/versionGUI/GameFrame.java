package versionGUI;

import javax.swing.JFrame;


public class GameFrame {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(1000, 500);
//		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Player[] players=new Player[2];
		players[0]=new ClientPlayer("Player1");
		players[1]=new ClientPlayer("Player2");
		
		GameBoard game=new GameBoard(players);
		
		BoardGUI gameBoard=new BoardGUI(game);
		frame.getContentPane().add(gameBoard);
		frame.setVisible(true);
		
	}
}

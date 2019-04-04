package versionGUI2;

import javax.swing.*;
import javax.swing.text.TableView;

import java.awt.*;

public class GameBoardGUI{
	JFrame window;
	PlayerGUI players[]=new PlayerGUI[2];
	GameBoard board;
	
	public GameBoardGUI(GameBoard board) {
		window=new JFrame("Battleship");
		window.setSize(1000, 500);
		window.setLayout(new GridLayout(1,2));
		window.setBackground(Color.WHITE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.board=board;
		
		for(int i=0;i<2;i++) {
			this.players[i]=new PlayerGUI(board.players[i]);
		}
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this.players[0]);
		window.add(this.players[1]);
		window.setVisible(true);
	}
/*	
	public static void main(String[] args) {
		Design frame=new Design();
//		JFrame window=new JFrame("Battleship");
//		window.setSize(1000, 500);
//		window.setLayout(new GridLayout(1,2));
//		window.setBackground(Color.WHITE);
//		
//		JPanel player1=new JPanel();
//		player1.setLayout(new GridLayout());
//		player1.setBackground(Color.YELLOW);
////		player1.setSize(500, 500);
//		JTable table1=new JTable(10, 10);
////		System.out.println(table1.getPreferredSize().getWidth()+" "+table1.getPreferredSize().getHeight());
//		System.out.println((int)table1.getPreferredSize().getWidth()-200+" "+(int)table1.getPreferredSize().getHeight());
//		Dimension dim=new Dimension((int)table1.getPreferredSize().getWidth()-200, (int)table1.getPreferredSize().getHeight());
////		table1.setPreferredScrollableViewportSize(dim);
//		table1.setSize(dim);
////		table1.setFillsViewportHeight(true);
//		player1.add(table1);
//		
//		JPanel player2=new JPanel();
//		player2.setBackground(Color.GREEN);
//		player2.setLayout(new GridLayout());
//		JTable table2=new JTable(10, 10);
////		player2.add(table2, BorderLayout.CENTER);
//		
//		window.add(player1);
//		window.add(player2);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setVisible(true);
	}
*/	
}

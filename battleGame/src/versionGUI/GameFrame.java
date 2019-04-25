package versionGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame {
	int gameMode=-1;// 0 - Player/Bot, 1 - Player/Player , 2 - Network (unavailable yet)
	JFrame frame;
	JPanel buttonList;
	
	public void setGameMode() {
		buttonList.setVisible(false);
		GameBoard game = null;

		if(gameMode==0) {
			//set Bot VS player game
			System.out.println("Setting Player VS Bot game");
		}else if(gameMode==1) {
			//set player VS player
			System.out.println("Setting Player VS Player");
			Player[] players=new Player[2];
			players[0]=new ClientPlayer("Player1");
			players[1]=new ClientPlayer("Player2");
			
			game=new GameBoard(players);
		}else if(gameMode==2) {
			//set network game
			System.out.println("Setting Network game ");
			
		}
		
		BoardGUI gameBoard=new BoardGUI(game);
		
		frame.getContentPane().add(gameBoard);
	}
	
	public GameFrame() {
		frame=new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		buttonList = new JPanel();
		buttonList.setPreferredSize(new Dimension(200,200));
//		buttonList.setLayout(new BoxLayout(buttonList, BoxLayout.X_AXIS));
		
		JButton playWBot=new JButton(" Player VS Bot ");
		playWBot.setBackground(Color.BLUE);
		playWBot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=0;
				setGameMode();
				
			}
			
		});
		buttonList.add(playWBot);
		
		JButton multiPlayerLocal=new JButton("Player1 VS Player2");
		multiPlayerLocal.setBackground(Color.ORANGE);
		multiPlayerLocal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=1;
				setGameMode();
				
			}
			
		});
		buttonList.add(multiPlayerLocal);
		
		JButton networkPlayers=new JButton("Player across Network");
		networkPlayers.setBackground(Color.GREEN);
		networkPlayers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=2;
				setGameMode();
				
			}
			
		});
		buttonList.add(networkPlayers);
		
		frame.add(buttonList, BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		GameFrame g=new GameFrame();
	}
}

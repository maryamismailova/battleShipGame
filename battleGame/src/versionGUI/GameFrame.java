package versionGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class GameFrame {
	int gameMode=-1;// 0 - Player/Bot, 1 - Player/Player , 2 - Network (unavailable yet)
	JFrame frame;
	JPanel buttonList;
	
	public void setGameMode() {
		buttonList.setVisible(false);
		GameBoard game = null;
		frame.setLayout(new BorderLayout());
		if(gameMode==0) {
			//set Bot VS player game
			System.out.println("Setting Player VS Bot game");
			Player[] players=new Player[2];
			players[0]=new ClientPlayer("P_0");
			players[1]=new ComputerPlayer("Bot");
			
			game=new GameBoard(players);
			
		}else if(gameMode==1) {
			//set player VS player
			System.out.println("Setting Player VS Player");
			Player[] players=new Player[2];
			players[0]=new ClientPlayer("P_1");
			players[1]=new ClientPlayer("P_2");
			
			game=new GameBoard(players);
		}else if(gameMode==2) {
			//set network game
			System.out.println("Setting Network game ");
			String msg="LAN mode is not available";
            UIManager.put("OptionPane.minimumSize",new Dimension(200,100));
            Object[] options= {"OK, exit", "New Game?"};
            int result=JOptionPane.showOptionDialog(frame.getContentPane(), msg, "Unavailable", JOptionPane.YES_NO_OPTION, 
            		JOptionPane.INFORMATION_MESSAGE, null, options, options[0] );
            if(result==JOptionPane.OK_OPTION) {
            	System.exit(0);                        	
            }
            else {
            	GameFrame gf=new GameFrame();
            }
			//TODO
		}
		
		BoardGUI gameBoard=new BoardGUI(game, gameMode);
		
		frame.getContentPane().add(gameBoard);
	}
	
	public GameFrame() {
		frame=new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		buttonList = new JPanel();
		buttonList.setPreferredSize(new Dimension(200,200));
		
		Box modeButtons=Box.createVerticalBox();
		
		JButton playWBot=new JButton(" Player VS Bot ");
		playWBot.setBackground(Color.BLUE);
		playWBot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=0;
				setGameMode();
				
			}
			
		});
//		buttonList.add(playWBot);
		modeButtons.add(playWBot);
		
		JButton multiPlayerLocal=new JButton("Player1 VS Player2");
		multiPlayerLocal.setBackground(Color.ORANGE);
		multiPlayerLocal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=1;
				setGameMode();
				
			}
			
		});
//		buttonList.add(multiPlayerLocal);
		modeButtons.add(multiPlayerLocal);
		
		JButton networkPlayers=new JButton("Player in LAN");
		networkPlayers.setBackground(Color.GREEN);
		networkPlayers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode=2;
				setGameMode();
				
			}
			
		});
//		buttonList.add(networkPlayers);
		modeButtons.add(networkPlayers);
		buttonList.add(modeButtons);
		frame.setLayout(new GridBagLayout());
//		frame.add(buttonList, BorderLayout.CENTER);
		frame.add(buttonList);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		GameFrame g=new GameFrame();
	}
}
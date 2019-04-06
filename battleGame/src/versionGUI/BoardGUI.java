package versionGUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardGUI extends JPanel{
	PlayerPanel [] player=new PlayerPanel[2];
	GameBoard game;
	int nbSetupPlayers=0;
	boolean gameModeOn=false;
	
	public BoardGUI(GameBoard board) {
		super();
		this.game=board;
		this.setLayout(new GridLayout());
		this.setBackground(Color.ORANGE);
		player[0]=new PlayerPanel(this, game.players[0]);
		player[1]=new PlayerPanel(this, game.players[1]);
		this.add(player[0]);
		this.add(player[1]);
		nextPlayer();
	}
	public void setupGameMode() {
		player[0].gameMode=new PlayerGameModeGUI(player[0]);
		player[0].c.add(player[0].gameMode);
		player[1].gameMode=new PlayerGameModeGUI(player[1]);
		player[1].c.add(player[1].gameMode);
		
		player[0].card.next(player[0].c);
		player[1].card.next(player[1].c);
		player[0].c.setVisible(true);
		player[1].c.setVisible(false);

	}
	
	public void nextPlayer() {
		if(game.currentPlayer==2) {
			System.out.println("GAME MODE!");
			game.currentPlayer=0;
			setupGameMode();
			gameModeOn=true;
			System.out.println("Current player is "+game.currentPlayer);
		}else {
			System.out.println("Current Player "+game.currentPlayer);
			player[game.currentPlayer].c.setVisible(true);
			player[1-game.currentPlayer].c.setVisible(false);
			game.currentPlayer+=1;
			System.out.println("Next Player "+game.currentPlayer);
		}
	}
	
}

package versionGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	public  CardLayout card = new CardLayout();
    public Container c;
	PlayerBoardSetupGUI boardSetup;
    PlayerGameModeGUI gameMode;
    public Player player;
    BoardGUI gameBoard;
    
    public PlayerPanel(BoardGUI gameBoard, Player p) {
		super();
		c=this;
		this.gameBoard=gameBoard;
		this.player=p;
		c.setLayout(card);
    	this.setLayout(card);
		boardSetup=new PlayerBoardSetupGUI(this);
		gameMode=new PlayerGameModeGUI(this);
		c.add(boardSetup);
		c.add(gameMode);
		this.setBackground(Color.WHITE);
		
	}
    
}

package battleGame.src.versionGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class to set player's panel.
 */
public class PlayerPanel extends JPanel {
	public  CardLayout card = new CardLayout();
    public Container c;
	PlayerBoardSetupGUI boardSetup;
    PlayerGameModeGUI gameMode;
    public Player player;
    BoardGUI gameBoard;
    JWaitPanel waitPanel;
    
    /*!
     * Nested class. Used to create the button which will allow one of the players to set his turn
     */
    public class JWaitPanel extends JPanel{
    	JButton startButton;
    	
    	/*!
    	 * Constructor
    	 */
    	public JWaitPanel() {
    		super();
    		this.setBackground(Color.GREEN);
    		startButton=new JButton("My turn!");
    		startButton.setBackground(Color.BLUE);
    		startButton.setSize(new Dimension(100,100));
    		
    		startButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					gameMode.updatePlayerShipsBoard();
					card.previous(c);
				}
    			
    		});
    		
    		this.setLayout( new GridBagLayout() );
    		this.add(startButton, new GridBagConstraints());
    		
    	}
    	
    	
    }
    
    /*!
     * Constructor
     */
    public PlayerPanel(BoardGUI gameBoard, Player p) {
		super();
		c=this;
		this.gameBoard=gameBoard;
		this.player=p;
//		c.setLayout(card);
    	this.setLayout(card);
		boardSetup=new PlayerBoardSetupGUI(this);
//		gameMode=new PlayerGameModeGUI(this);
		c.add(boardSetup);
		card.next(this);
		
		waitPanel=new JWaitPanel();
		
		this.setBackground(Color.WHITE);
		
	}
    
}

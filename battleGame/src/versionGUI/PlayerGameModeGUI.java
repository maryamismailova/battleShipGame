package versionGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;


public class PlayerGameModeGUI extends JPanel {
	PlayerPanel playerPanel;
	GridLayout gLayout=new GridLayout(2,1, 10, 100);
	JLabel gameStat;
	
	GridBagLayout gbLayout=new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	
	JPanel playerStrikeBoard;
	JPanel playerShipsBoard;
	BoardCell[][] boardCells=new BoardCell[GameBoard.BOARDIM][GameBoard.BOARDIM];
	JButton[][] playerCoordinates=new JButton[GameBoard.BOARDIM][GameBoard.BOARDIM];
	
	public PlayerGameModeGUI(PlayerPanel playerPanel) {
		super();
		this.playerPanel=playerPanel;
		
		//where player will make strikes
		GridLayout cellLayout=new GridLayout(GameBoard.BOARDIM+1, GameBoard.BOARDIM+1);
		playerStrikeBoard=new JPanel();
		playerStrikeBoard.setLayout(cellLayout);
		setStrikeBoard();
		
		System.out.println("Strike Board of "+playerPanel.player.name+" is set!");
		//where player sees his board
		playerShipsBoard=new JPanel();
		playerShipsBoard.setPreferredSize(new Dimension(250,200));
		playerShipsBoard.setLayout(new GridLayout(GameBoard.BOARDIM+1, GameBoard.BOARDIM+1));
		setPlayerShipsBoard();
		System.out.println("Ship Board of "+playerPanel.player.name+"is set!");
		
		this.setLayout(gbLayout);

        JLabel gameBoard=new JLabel("Game Board:");
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(gameBoard, gbc);
        
        gameStat=new JLabel("GameStatus:");
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(gameStat, gbc);
        
		gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(playerStrikeBoard, gbc);
        
        JLabel yourBoard=new JLabel("Your board status:");
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(yourBoard, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        this.add(playerShipsBoard, gbc);

		
	}
	
	public void setStrikeBoard() {
		//fill the table with coordinates and buttons
		for(int i=0;i<GameBoard.BOARDIM+1;i++) {
			for(int j=0;j<GameBoard.BOARDIM+1;j++) {
				if(i==0) {
					JTextPane text=new JTextPane();
					if(j==0) text.setText(" ");
					else text.setText(Character.toString((char)('A'+j-1)));
					playerStrikeBoard.add(text);
				}else if(j==0 && i>0) {
					JTextPane text=new JTextPane();
					text.setText(" "+(i-1));//CHANGED
					playerStrikeBoard.add(text);
				}else {
					boardCells[i-1][j-1]=new BoardCell();
					boardCells[i-1][j-1].setBackground(Color.WHITE);
					playerStrikeBoard.add(boardCells[i-1][j-1]/*, i*GameBoard.BOARDIM+j*/);					
				}
			}
		}
	}
	
	public void setPlayerShipsBoard() {
		for(int i=0;i<GameBoard.BOARDIM+1;i++) {
			for(int j=0;j<GameBoard.BOARDIM+1;j++) {
				if(i==0) {
					JTextPane text=new JTextPane();
					if(j==0) text.setText(" ");
					else text.setText(Character.toString((char)('A'+j-1)));
					playerShipsBoard.add(text);
				}else if(j==0 && i>0) {
					JTextPane text=new JTextPane();
					text.setText(" "+(i-1));
					playerShipsBoard.add(text);
				}else {
					JButton button=new JButton();
					button.setEnabled(false);
					button.setBackground(Color.WHITE);
					playerCoordinates[i-1][j-1]=button;
					playerShipsBoard.add(playerCoordinates[i-1][j-1]/*, i*GameBoard.BOARDIM+j*/);					
				}
			}
		}
		updatePlayerShipsBoard();
	}
	
	public void updatePlayerShipsBoard() {
		System.out.println("Update Player Ships Board");
		for(int i=0;i<GameBoard.BOARDIM;i++) {
			for(int j=0;j<GameBoard.BOARDIM;j++) {
				Coordinate coord;
				try {
					coord = new Coordinate(j,i);
				} catch (CoordinateOutOfBonds e) {
					e.printStackTrace();
					coord=null;
				}
				int currentPlayer=playerPanel.gameBoard.game.currentPlayer;
				//CHANGED
				for(int k=0;k<playerPanel.player.ships.length;k++) {
					if(playerPanel.player.ships[k].containsHit(coord)) {
//						System.out.println("was hit!");
						playerCoordinates[i][j].setBackground(Color.RED);
					}else if(playerPanel.player.ships[k].contains(coord)) {
						playerCoordinates[i][j].setBackground(Color.BLACK);
					}
				}
			}
		}
	}
	
	public class BoardCell extends JButton implements ActionListener {
		Color striked=Color.BLUE;
		Color hit = Color.RED;
		boolean wasStriked=false;
		boolean wasHit=false;
		
		public BoardCell() {
			this.addActionListener(this);
		}

		void setStriked() {
			wasStriked=true;
			this.setBackground(striked);
		}
		
		void setNotStriked() {
			wasStriked=false;
			this.setBackground(Color.WHITE);
		}
		void setHit() {
			wasHit=true;
			this.setBackground(hit);
		}
		
		void setNotHit() {
			wasHit=false;
			this.setBackground(Color.WHITE);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//get clicked cell
			JButton clicked=(JButton)e.getSource();
			int strikeX=0,strikeY = 0;
			boolean found=false;
			for(int i=0;i<GameBoard.BOARDIM;i++) {
				if(found==false) {
					for(int j=0;j<GameBoard.BOARDIM;j++) {
						if(clicked.equals((JButton)boardCells[i][j])) {
							strikeX=j;
							strikeY=i;
							System.out.println("Player makes strike on "+strikeX+ ",  "+strikeY);
							found=true;
							break;
						}
					}					
				}else break;
			}
			if(boardCells[strikeY][strikeX].wasHit==true || boardCells[strikeY][strikeX].wasStriked==true)return;
			boardCells[strikeY][strikeX].wasStriked=true;
			boardCells[strikeY][strikeX].setBackground(boardCells[strikeY][strikeX].striked);
			
			Coordinate strikeCoord;
			try {
				strikeCoord = new Coordinate(strikeX,strikeY);
			} catch (CoordinateOutOfBonds e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				strikeCoord=null;
			}
			
			//check for hit
			GameBoard game=playerPanel.gameBoard.game;
			int nextPlayer=1-game.currentPlayer;
			
			if(game.checkIfHit(strikeCoord)) {
				gameStat.setText("Game Status: at "+strikeCoord+" hit!");
				System.out.println();
				System.out.println("Player: "+game.players[game.currentPlayer].name+" hit!");
                
				//add a hit on second player
				game.players[nextPlayer].addHitOnPlayer(strikeCoord);
                boardCells[strikeY][strikeX].setBackground(boardCells[strikeY][strikeX].hit);
                boardCells[strikeY][strikeX].wasHit=true;
                
                if(game.checkIfKill(strikeCoord)==true) {
                	gameStat.setText("Game Status: at "+strikeCoord+" kill!");
                    System.out.println("Player "+game.players[game.currentPlayer].name+" kills");
                    if(game.checkIfWin()==true) {
                        System.out.println("Player "+playerPanel.player.name+" wins");
                        game.gameWon=true;
                        String msg="Player "+playerPanel.player.name+" wins";
                        UIManager.put("OptionPane.minimumSize",new Dimension(200,100));
                        Object[] options= {"OK, exit", "New Game?"};
                        int result=JOptionPane.showOptionDialog(this, msg, "Winner", JOptionPane.YES_NO_OPTION, 
                        		JOptionPane.INFORMATION_MESSAGE, null, options, options[0] );
                        if(result==JOptionPane.OK_OPTION) {
                        	System.exit(0);                        	
                        }
                        else {
                        	String msg2="Are you sure you want to start new game?";
                        	int res=JOptionPane.showConfirmDialog(this, msg2, "New Game", JOptionPane.YES_NO_OPTION);
                        	if(res==JOptionPane.YES_OPTION) {
                        		GameFrame gf=new GameFrame();
                        	}else {
                        		System.exit(0);
                        	}
                        }
                    }
                }
    			
			}else {
				//close this player, give the turn to the opponent
                System.out.println("No hit!");
                gameStat.setText("Game Status: at "+strikeCoord+" no hit!");
                
                if(playerPanel.gameBoard.mode==0) {
					boardCells[strikeY][strikeX].setBackground(boardCells[strikeY][strikeX].striked);
					boardCells[strikeY][strikeX].wasStriked=true;
					game.currentPlayer=1-game.currentPlayer;
					
					playerPanel.gameBoard.nextPlayer();
				}else {
					boardCells[strikeY][strikeX].setBackground(boardCells[strikeY][strikeX].striked);
					boardCells[strikeY][strikeX].wasStriked=true;
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					playerPanel.card.next(playerPanel);
					playerPanel.waitPanel.startButton.setVisible(false);
					
					// switch to other player
					System.out.println("Switch to second player");
					
					game.currentPlayer=1-game.currentPlayer;
					playerPanel.gameBoard.player[game.currentPlayer].waitPanel.startButton.setVisible(true);
				}
				
				System.out.println("Next Player "+game.currentPlayer+ "("+game.players[game.currentPlayer].name);
			}
		}
		
		
//			System.out.println("not found");
	}

}
package versionGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class PlayerBoardSetupGUI extends JPanel {
	PlayerPanel playerPanel;
	PlayerBoardSetupGUI playerSetup=this;
	GridBagLayout gbLayout=new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JTextPane text;
	BoardCell[][] boardCells=new BoardCell[GameBoard.BOARDIM][GameBoard.BOARDIM];
	int readyShips;
	ArrayList<Coordinate> shipCoordinates=new ArrayList();
	Player player;
	
	public void setVisibilityOff() {
		this.setVisible(false);
	}
	
	public PlayerBoardSetupGUI(PlayerPanel playerPanel) {
		super();
		this.player=playerPanel.player;
		this.playerPanel=playerPanel;
		
		text=new JTextPane();
		text.setText("Player set the board");
	
		GridLayout cellLayout=new GridLayout(GameBoard.BOARDIM+1, GameBoard.BOARDIM+1);
		
		JPanel cells=new JPanel();
		cells.setLayout(cellLayout);		
		int x,y;
		x=GameBoard.BOARDIM+1;
		y=x;
		//fill the table with coordinates and buttons
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				if(i==0) {
					JTextPane text=new JTextPane();
					if(j==0) text.setText(" ");
					else text.setText(Character.toString((char)('A'+j-1)));
					cells.add(text);
				}else if(j==0 && i>0) {
					JTextPane text=new JTextPane();
					text.setText(" "+(i-1));
					cells.add(text);
				}else {
					boardCells[i-1][j-1]=new BoardCell();
					boardCells[i-1][j-1].setBackground(Color.WHITE);
					cells.add(boardCells[i-1][j-1], i*x+j);
				}
			}
		}

		JButton addButton=new JButton();//button to add a new ship to ships of the player
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("here!");
				Coordinate[] coords=new Coordinate[shipCoordinates.size()];
				System.out.println("Coordinates: ");
				for(int i=0;i<shipCoordinates.size();i++) {
					coords[i]=shipCoordinates.get(i);
					System.out.println(coords[i]);
				}
				
				try {
					System.out.println("Add "+coords.length+" coordinates length ship!");
					player.ships[readyShips]=player.addAShip(coords, readyShips);
					readyShips++;
					System.out.println("Added a new ship");
					for(int i=0;i<shipCoordinates.size();i++) {
						boardCells[shipCoordinates.get(i).y][shipCoordinates.get(i).x].setNotSelected();
						boardCells[shipCoordinates.get(i).y][shipCoordinates.get(i).x].setBackground(Color.BLACK);
					}
				} catch (CoordinateOutOfBonds | UnavailableShipException e1) {
//					readyShips--;
					text.setText(e1.getMessage());
					System.out.println(e1.getMessage());
					for(int i=0;i<shipCoordinates.size();i++) {
						boardCells[shipCoordinates.get(i).y][shipCoordinates.get(i).x].setNotSelected();
					}
				}
				if(readyShips==5) {
					System.out.println("Player added 5 boats!");
					playerPanel.gameBoard.nextPlayer();

				}
				shipCoordinates=null;
			}
			
		});
//		
//		this.add(cells);
//		this.add(addButton);
		
		
		this.playerPanel=playerPanel;
		this.setLayout(gbLayout);
		
		gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(text, gbc);
 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        this.add(cells, gbc);
 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = 2;
        this.add(addButton, gbc);
	}
	
	
	public class BoardCell extends JButton implements ActionListener {
		Color selection=Color.BLUE;
		Color taken = Color.BLACK;
		boolean selected=false;
		
		
		public BoardCell() {
			this.addActionListener(this);
		}

		void setSelected() {
			selected=true;
			this.setBackground(selection);
		}
		
		void setNotSelected() {
			selected=false;
			this.setBackground(Color.WHITE);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked=(JButton)e.getSource();
			for(int i=0;i<GameBoard.BOARDIM;i++) {
				for(int j=0;j<GameBoard.BOARDIM;j++) {
					if(clicked.equals((JButton)boardCells[i][j])) {//find coordinates
						try {
							Coordinate clickedCell=new Coordinate(j,i);
							if(boardCells[i][j].selected==false) {
								System.out.println("x: "+j+", y: "+i);
								boardCells[i][j].setSelected();
								if(shipCoordinates==null)shipCoordinates=new ArrayList<Coordinate>();
								shipCoordinates.add(clickedCell);
							
							}else {
								//TO REMOVE COORDINATE FROM list
								for(int i1=0;i1<shipCoordinates.size();i1++) {
									if(shipCoordinates.get(i1).equals(clickedCell))
										shipCoordinates.remove(i1);
								}
								boardCells[i][j].setNotSelected();
								
							}
						}catch(CoordinateOutOfBonds e1) {
							System.out.println("Coord error!");
							e1.printStackTrace();
							
						}
						
//						System.out.println();
					}
				}
			}
//			System.out.println("not found");
		}

	}

}

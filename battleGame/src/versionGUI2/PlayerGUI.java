package versionGUI2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import versionGUI.PlayerGUI.BoardCell;

public class PlayerGUI extends JPanel{
	int readyShips;
	ArrayList<Coordinate> shipCoordinates=new ArrayList();
	Player player;
	BoardCell[][] boardCells=new BoardCell[GameBoard.BOARDIM][GameBoard.BOARDIM];
	
	public PlayerGUI(Player player) {
		this.readyShips=0;
		this.player=player;
		GridLayout cellLayout=new GridLayout(GameBoard.BOARDIM+1, GameBoard.BOARDIM+1);
		
		JPanel cells=new JPanel();
		cells.setLayout(cellLayout);		
		
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
					System.out.println(e1.getMessage());
					for(int i=0;i<shipCoordinates.size();i++) {
						boardCells[shipCoordinates.get(i).y][shipCoordinates.get(i).x].setNotSelected();
					}
				}
				if(readyShips==5) {
					cells.setVisible(false);
				}
				shipCoordinates=null;
			}
			
		});
		
		this.add(cells);
		this.add(addButton);
		
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
					text.setText(" "+i);
					cells.add(text);
				}else {
					boardCells[i-1][j-1]=new BoardCell();
					boardCells[i-1][j-1].setBackground(Color.WHITE);
					cells.add(boardCells[i-1][j-1], i*x+j);
				}
			}
		}
		
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

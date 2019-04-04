package versionGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import versionGUI.PlayerGUI.BoardCell;

public class PlayerGUI extends JPanel{
	
	int readyShips=0;
	ArrayList<Coordinate> shipCoord=new ArrayList();
	
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
					if(clicked.equals((JButton)shipCells[i][j])) {
						System.out.println("x: "+j+", y: "+i);
						shipCells[i][j].setSelected();
						if(shipCoord==null)shipCoord=new ArrayList<Coordinate>();
						try {
							shipCoord.add(new Coordinate(j,i));
						} catch (CoordinateOutOfBonds e1) {
							// TODO Auto-generated catch block
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
	Player player;
	BoardCell[][] shipCells=new BoardCell[GameBoard.BOARDIM][GameBoard.BOARDIM];
	
	public PlayerGUI(Player player) {
		this.player=player;
		GridLayout cellLayout=new GridLayout(GameBoard.BOARDIM+1, GameBoard.BOARDIM+1);
		
		JPanel cells=new JPanel();
		cells.setLayout(cellLayout);
		
		
		JButton addButton=new JButton();
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Coordinate[] coords=new Coordinate[shipCoord.size()];
				for(int i=0;i<shipCoord.size();i++) {
					coords[i]=shipCoord.get(i);
				}
				
				try {
					System.out.println("Add "+coords.length+" coordinates!");
					readyShips++;
					player.addAShip(coords, readyShips);
					System.out.println("Add a new ships");
				} catch (CoordinateOutOfBonds | UnavailableShipException e1) {
					readyShips--;
					System.out.println(e1.getMessage());
				}
				for(int i=0;i<shipCoord.size();i++) {
					shipCells[shipCoord.get(i).y][shipCoord.get(i).x].setNotSelected();
				}
				shipCoord=new ArrayList<>();
			}
			
		});
		
		this.add(cells);
		this.add(addButton);
		
		int x,y;
		x=GameBoard.BOARDIM+1;
		y=x;
		
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
					shipCells[i-1][j-1]=new BoardCell();
					shipCells[i-1][j-1].setBackground(Color.WHITE);
					cells.add(shipCells[i-1][j-1], i*x+j);
				}
			}
		}
		
	}

}

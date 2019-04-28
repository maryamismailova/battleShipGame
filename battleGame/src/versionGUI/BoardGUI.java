package versionGUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardGUI extends JPanel{
	PlayerPanel [] player;//=new PlayerPanel[2];
	GameBoard game;
	
	int nbSetupPlayers=0;
	boolean gameModeOn=false;
	int mode;
	
	public BoardGUI(GameBoard board, int mode) {
		super();
		this.game=board;
		this.mode=mode;
		this.setLayout(new GridLayout());
		this.setBackground(Color.ORANGE);
		if(mode==0) {
			System.out.println("Setting up 1 player GUI");
			player=new PlayerPanel[1];
			player[0]=new PlayerPanel(this, game.players[0]);
			this.add(player[0]);
		}
		else if(mode==1){
			player=new PlayerPanel[2];
			player[0]=new PlayerPanel(this, game.players[0]);
			player[0].waitPanel.setBackground(Color.YELLOW);

			player[1]=new PlayerPanel(this, game.players[1]);
			player[1].waitPanel.setBackground(Color.GREEN);
			
			this.add(player[0]);
			this.add(player[1]);
		}
		
		nextPlayer();
	}
	
	public void setupGameMode() {
		if(mode==0) {
			player[0].gameMode=new PlayerGameModeGUI(player[0]);
			player[0].c.add(player[0].gameMode);
			player[0].card.next(player[0].c);
			player[0].c.setVisible(true);
			
			game.players[1].initializeBoard();
		}
		else if(mode==1){
			player[0].gameMode=new PlayerGameModeGUI(player[0]);
			player[0].add(player[0].gameMode);
			
			player[1].gameMode=new PlayerGameModeGUI(player[1]);
			player[1].add(player[1].gameMode);
			
			player[0].add(player[0].waitPanel);
			player[1].add(player[1].waitPanel);
			
			player[0].card.next(player[0]);
			player[1].card.next(player[1]);
			player[1].card.next(player[1]);
			player[1].waitPanel.startButton.setVisible(false);
//			player[0].c.setVisible(true);
//			player[1].c.setVisible(false);
			
			
		}
	}
	
	public void nextPlayer() {	
		int currentPlayer=game.currentPlayer;
		int nextPlayer=1-currentPlayer;
		
		if(gameModeOn==false) {
			if(nbSetupPlayers==mode+1) {
				System.out.println("start game Mode!");
				gameModeOn=true;
				game.currentPlayer=0;
				player[game.currentPlayer].c.setVisible(true);
				player[1-game.currentPlayer].c.setVisible(true);
				setupGameMode();
			}
			else{
				if(mode!=0) {
					System.out.println("Current Player "+game.currentPlayer);
					player[game.currentPlayer].c.setVisible(true);
					player[1-game.currentPlayer].c.setVisible(false);
					game.currentPlayer=nextPlayer;
					System.out.println("Next Player "+game.currentPlayer);
				} else if(mode==0) {
					
					player[game.currentPlayer].c.setVisible(true);
				}
			}
		}
		if(gameModeOn==true) {
			if(mode==0) {
				if(currentPlayer==1) {				
					player[0].setEnabled(false);
					Coordinate playerMove=null;
		            while(playerMove==null)playerMove=game.players[currentPlayer].makeMove();
	            	
		            System.out.println("Player: "+game.players[currentPlayer].name+" move: "+Coordinate.coordinateToString(playerMove));
		            
		            while(game.checkIfHit(playerMove)==true /*&& gameWon==false*/) {
		            	System.out.println("Player: "+game.players[currentPlayer].name+" hit!");
		            	
		                game.players[0].addHitOnPlayer(playerMove);
		                
		                if(game.checkIfKill(playerMove)==true) {
		                    System.out.println("Player: "+game.players[currentPlayer].name+" kills");
		                    if(game.checkIfWin()==true) {
		                        System.out.println("Player: "+game.players[currentPlayer].name+" wins");
		                        game.gameWon=true;
		                        break;
		                    }
		                }
//		                printHitsBoard(gameplayers[nextPlayer]);
		                playerMove=null;
		                while(playerMove==null)playerMove=game.players[currentPlayer].makeMove();	                
		            }
		            player[0].setEnabled(true);
		            game.currentPlayer=0;
		            player[0].gameMode.updatePlayerShipsBoard();
		            
		            System.out.println("Player: "+game.players[1].name+" no hit");
		            System.out.println("Next: "+game.players[0].name+"\n");
				}
				
			}else {
				/*currentPlayer=game.currentPlayer;
				nextPlayer=1-currentPlayer;
				
				System.out.println("Current Player "+game.currentPlayer);
				player[currentPlayer].c.setVisible(true);
				player[nextPlayer].c.setVisible(false);
//				game.currentPlayer=nextPlayer;
				System.out.println("Next Player "+game.currentPlayer);*/
			}
		}
	}
	
}

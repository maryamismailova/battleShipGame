package versionGUI;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PlayerGameModeGUI extends JPanel {
	PlayerPanel playerPanel;
	GridLayout gLayout=new GridLayout();
	public PlayerGameModeGUI(PlayerPanel playerPanel) {
		super();
		this.playerPanel=playerPanel;
		this.setLayout(gLayout);
	}
}

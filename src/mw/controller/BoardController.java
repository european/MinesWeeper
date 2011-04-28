package mw.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import mw.model.BoardModel;
import mw.model.ButtonStatus;
import mw.model.IBoardModel;
import mw.view.BoardPanel;
import mw.view.FeldButton;
import mw.view.IBoardPanel;

public class BoardController {

	private IBoardModel boardModel;
	private IBoardPanel boardPanel;

	/**
	 * Initiates the board variable with the appropriate difficulty setting
	 * 
	 * @param boardModel
	 * @param boardPanel
	 */
	public BoardController(BoardModel boardModel, BoardPanel boardPanel) {
		this.boardModel = boardModel;
		this.boardPanel = boardPanel;

		this.boardModel.newGame();
		this.boardPanel.build();
		this.boardPanel.repaint();

		// Fügt den Buttons auf dem BoardGrid den Listener hinzu
		this.boardPanel.addClickListener(new ToggleButtonListner());
	}

	/**
	 * Zeigt die aktuelle Zelle
	 * 
	 * @param e - <code>MouseEvent</code>
	 */
	private void showCell(MouseEvent e) {
		FeldButton feldButton = (FeldButton) e.getSource();
		int[] coords = new int[2];

		coords = feldButton.getCoords();

		if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG) {
			if (feldButton.isMine()) {
				
				feldButton.setButtonStatus(ButtonStatus.MINE_EXPLODED);
				feldButton.getMineExplodeIcon();
				
				boardModel.endGame();
				boardPanel.redraw();
				
				int timePlayed = boardModel.getTimePlayed();
				JOptionPane.showOptionDialog(
                		null, 
                		"Glückwunsch, Du hast Verloren! Und nur "+timePlayed+" Sekunden dafür benötigt. \n", 
                		"Du hast Verloren!", 
                		JOptionPane.CLOSED_OPTION, 
                		JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
			} else {
				boardModel.openField(coords[1], coords[0]);
				boardPanel.redraw();
				
				if(boardModel.checkWin()){
					boardModel.endGame();
					boardPanel.redraw();
					
					int timePlayed = boardModel.getTimePlayed();
                	Object[] options = {"Yeah", "Nöö!"};

                    JOptionPane.showOptionDialog(
                    		null, 
                    		"Glückwunsch, Du hast Gewonnen! Du brauchtest "+timePlayed+" Sekunden um das Spiel zu beenden. \n " +
                				"Möchtest du deinen Highscore eintragen?", 
                    		"Gewonnen!", 
                    		JOptionPane.YES_NO_OPTION, 
                    		JOptionPane.INFORMATION_MESSAGE, 
                    		null, 
                    		options,
                    	    options[0]);
				}
			}
		}
		
	}

	/**
	 * MouseListener für die Felder (rechts klick/ links klick)
	 */
	private class ToggleButtonListner extends MouseAdapter {		
		// Wenn die Maus gedrück ist, dann
		public void mousePressed(MouseEvent e) {
			FeldButton feldButton = (FeldButton) e.getSource();

			if (e.getButton() == MouseEvent.BUTTON3) {
				// Entweder noch offen oder schon eine Flagge
				if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT) {
					// Toggel der Flagge
					// Und des Status des Enum Wertes
					feldButton.toggleFlagButton();
					boardModel.setRestMinen(boardModel.getRestMinen() -1 );
				}else if(feldButton.isFlagged()){
					feldButton.toggleFlagButton();
					boardModel.setRestMinen(boardModel.getRestMinen() +1 );
				}

			} else if (e.getButton() == MouseEvent.BUTTON1) {
				if (feldButton.isFlagged()) {
					// Wenn das eine Flagge ist Entferne die Flagge
					feldButton.toggleFlagButton();	
					boardModel.setRestMinen(boardModel.getRestMinen() +1 );
				} else {
					// Sonst zeige die Zelle
					showCell(e);				
				}
			}
		}
	}

}

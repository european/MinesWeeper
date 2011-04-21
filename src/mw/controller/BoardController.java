package mw.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import mw.model.BoardModel;
import mw.model.ButtonStatus;
import mw.view.BoardPanel;
import mw.view.FeldButton;

public class BoardController {

	private BoardModel boardModel;
	private BoardPanel boardPanel;

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
	@SuppressWarnings("unused")
	private void showCell(MouseEvent e) {
		FeldButton feldButton = (FeldButton) e.getSource();
		int[] coords = new int[2];

		coords = feldButton.getCoords();

		if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG) {
			if (feldButton.isMine()) {
				feldButton.getMineExplodeIcon();
				
				boardModel.endGame();
				boardPanel.redraw();
				
				int timePlayed = boardModel.getTimePlayed();
				int loose = JOptionPane.showOptionDialog(
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

                    int submitScores = JOptionPane.showOptionDialog(
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
	private class ToggleButtonListner implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {/* tue nichts */}
		public void mouseEntered(MouseEvent e) {/* tue nichts */}
		public void mouseExited(MouseEvent e) {/* tue nichts */}

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

		public void mouseReleased(MouseEvent e) {/* tue nichts */}
	}

}

package mw.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

		// listen for mouse clicks on the boardgrid
		this.boardPanel.addClickListener(new ToggleButtonListner());
	}

	/**
	 * Zeigt die aktuelle Zelle
	 * 
	 * @param e
	 *            - <code>MouseEvent</code>
	 */
	private void showCell(MouseEvent e) {
		FeldButton feldButton = (FeldButton) e.getSource();
		int[] coords = new int[2];

		coords = feldButton.getCoords();

		if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG) {
			if (feldButton.isMine()) {
				feldButton.getMineExplodeIcon();
				boardPanel.showField();
			} else {
				feldButton.setText(String.valueOf(boardModel.getBoard()[coords[0]][coords[1]]));
			}
		}
	}

	// Hier werden alle Aktionen gesetzt, die vom Spiel Feld kommen

	// MouseListener für die Felder (rechts klick/ links klick)
	private class ToggleButtonListner implements MouseListener {
		// tue nichts
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		// Wenn die Maus gedrück ist, dann
		public void mousePressed(MouseEvent e) {
			FeldButton feldButton = (FeldButton) e.getSource();

			if (e.getButton() == MouseEvent.BUTTON3) {
				// Entweder noch offen oder schon eine Flagge
				if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG) {
					// Toggel der Flagge
					// Und des Status des Enum Wertes
					feldButton.toggleFlagButton();
				}

			} else if (e.getButton() == MouseEvent.BUTTON1) {
				if (feldButton.isFlagged()) {
					// Wenn das eine Flagge ist
				}
				showCell(e);
				// Sonst zeige die Zelle
			}
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

}

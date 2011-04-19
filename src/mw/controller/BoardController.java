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
	
	// Hier werden alle Aktionen gesetzt, die vom Spiel Feld kommen
	
	// MouseListener für die Felder (rechts klick/ links klick)
	
	
	
	
	
	class ToggleButtonListner implements MouseListener {
		// tue nichts
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        
        // Wenn die Maus gedrück ist, dann
        public void mousePressed(MouseEvent e) {
        	FeldButton feldButton = (FeldButton) e.getSource();

            if(e.getButton() == MouseEvent.BUTTON3) {
            	// Entweder noch offen oder schon eine Flagge
                if(feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG){
                	// Toggel der Flagge 
                	// Und des Status des Enum Wertes
                }
                    
            }
            else if(e.getButton() == MouseEvent.BUTTON1) {
                if(feldButton.isFlagged()) {
                    // Wenn das eine Flagge ist
                }
                // Sonst zeige die Zelle
            }
        }
        public void mouseReleased(MouseEvent e) {}
    }

}

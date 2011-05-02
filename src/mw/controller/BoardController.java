package mw.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mw.backstage.GameLogic;
import mw.model.BoardModel;
import mw.model.ButtonStatus;
import mw.model.Difficulty;
import mw.view.BoardPanel;
import mw.view.FeldButton;
import mw.view.GameFrame;
import mw.view.IBoardPanel;

public class BoardController {

	private BoardModel boardModel;
	private IBoardPanel boardPanel;
	private GameLogic gameLogic;
	

	/**
	 * Initiates the board variable with the appropriate difficulty setting
	 * @param gameFrame 
	 * @param boardPanel 
	 * 
	 * @param boardModel
	 * @param boardPanel
	 */
	public BoardController(GameFrame gameFrame, BoardPanel boardPanel) {
		this.boardModel = new BoardModel();
		this.boardPanel = boardPanel;
		this.gameLogic = new GameLogic(boardModel);
		
		boardPanel.setGameLogic(gameLogic);
		
		boardModel.addObserver(gameFrame);
//
//		// Fügt den Buttons auf dem BoardGrid den Listener hinzu
		this.boardPanel.addClickListener(new ToggleButtonListner());
	}
	
	
	public void init() {
		Difficulty diff = Difficulty.EINFACH;
		boardModel.setDifficulty(diff);
		newGame();		
	}
	/**
	 * Zeigt die aktuelle Zelle
	 * 
	 * @param e - <code>MouseEvent</code>
	 */
//	private void showCell(MouseEvent e) {
//		FeldButton feldButton = (FeldButton) e.getSource();
//		int[] coords = new int[2];
//
//		coords = feldButton.getCoords();
//
//		if (feldButton.getButtonStatus() == ButtonStatus.DEFAULT || feldButton.getButtonStatus() == ButtonStatus.FLAG) {
//			// Öffne Feld
//			gameLogic.openField(coords[0], coords[1]);
//			
//			
//			if (feldButton.isMine()) {
//				
//				feldButton.setButtonStatus(ButtonStatus.MINE_EXPLODED);
//				feldButton.getMineExplodeIcon();
//				
//				gameLogic.endGame();
//				boardPanel.redraw();
//				
//				int timePlayed = boardModel.getTimePlayed();
//				JOptionPane.showOptionDialog(
//                		null, 
//                		"Glückwunsch, Du hast Verloren! Und nur "+timePlayed+" Sekunden dafür benötigt. \n", 
//                		"Du hast Verloren!", 
//                		JOptionPane.CLOSED_OPTION, 
//                		JOptionPane.INFORMATION_MESSAGE, null, null, null);
//				
//			} else {
//				gameLogic.openField(coords[1], coords[0]);
//				boardPanel.redraw();
//				
//				if(boardModel.checkWin()){
//					gameLogic.endGame();
//					boardPanel.redraw();
//					
//					int timePlayed = boardModel.getTimePlayed();
//                	Object[] options = {"Yeah", "Nöö!"};
//
//                    JOptionPane.showOptionDialog(
//                    		null, 
//                    		"Glückwunsch, Du hast Gewonnen! Du brauchtest "+timePlayed+" Sekunden um das Spiel zu beenden. \n " +
//                				"Möchtest du deinen Highscore eintragen?", 
//                    		"Gewonnen!", 
//                    		JOptionPane.YES_NO_OPTION, 
//                    		JOptionPane.INFORMATION_MESSAGE, 
//                    		null, 
//                    		options,
//                    	    options[0]);
//				}
//			}
//		}
//		
//	}

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
					gameLogic.setRestMinen(-1);
				}else if(feldButton.isFlagged()){
					feldButton.toggleFlagButton();
					gameLogic.setRestMinen(+1);
				}

			} else if (e.getButton() == MouseEvent.BUTTON1) {
				if (feldButton.isFlagged()) {
					// Wenn das eine Flagge ist Entferne die Flagge
					feldButton.toggleFlagButton();	
					gameLogic.setRestMinen( +1 );
				} else {
					// Sonst zeige die Zelle
					showCell(e);	
					boardPanel.redraw();
				}
			}
		}
	}
	public void showCell(MouseEvent e) {
		FeldButton feldButton = (FeldButton) e.getSource();
		int[] coords = new int[2];

		coords = feldButton.getCoords();
		// Sonst öffne Feld
		gameLogic.openField(coords[0], coords[1]);		
	}
	
	
	public void newGame() {
		gameLogic.newGame();
		// rebuild the gamefield with MineButtons
		boardPanel.setRebuild(true);
		boardPanel.setCols(getCols());
		boardPanel.setRows(getRows());
		
        boardPanel.build();
		
	}
	public void setDifficulty(Difficulty difficulty) {
		boardModel.setDifficulty(difficulty);				
	}
	public void setDifficultyUser(int rows, int cols, int anzahlMinen) {
		boardModel.setDifficultyUser(rows, cols, anzahlMinen);
	}
	public int getRows() {
		return boardModel.getRows();
	}
	public int getCols() {
		return boardModel.getCols();
	}
	public int getAnzahlMinen() {
		return boardModel.getAnzahlMinen();
	}
	
	
	

}

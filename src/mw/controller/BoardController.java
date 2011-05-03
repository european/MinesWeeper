package mw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import mw.backstage.GameLogic;
import mw.model.BoardModel;
import mw.model.ButtonStatus;
import mw.model.Difficulty;
import mw.view.BoardPanel;
import mw.view.FeldButton;
import mw.view.GameFrame;
import mw.view.IBoardPanel;

public class BoardController extends Observable {

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
		
		addObserver(gameFrame);

//		// Fügt den Buttons auf dem BoardGrid den Listener hinzu
		this.boardPanel.addClickListener(new ToggleButtonListner());
		
		gameLogic.setTimer(new Timer(1000, new TimerAction()));
	}
	
	/**
	 * Zähler
	 * 
	 * @author niwe
	 * 
	 */
	class TimerAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			gameLogic.setTimePlayed(gameLogic.getTimePlayed() + 1);
			
			setChanged();
			notifyObservers(gameLogic);
		}
	}
	
	public void init() {
		Difficulty diff = Difficulty.EINFACH;
		gameLogic.setDifficulty(diff);
		newGame();		
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
					gameLogic.setRestMinen(-1);
				}else if(feldButton.isFlagged()){					
					gameLogic.setRestMinen(+1);
				}
				feldButton.toggleFlagButton();

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
			setChanged();
			notifyObservers(gameLogic);
		}
	}
	public void showCell(MouseEvent e) {
		FeldButton feldButton = (FeldButton) e.getSource();
		int[] coords = new int[2];
		

		coords = feldButton.getCoords();
		int y = coords[0];
		int x = coords[1];
		// Sonst öffne Feld
		gameLogic.openField(y, x);	
		
		if(gameLogic.checkLoose(y, x)){			
			feldButton.setButtonStatus(ButtonStatus.MINE_EXPLODED);
			
			int timePlayed = boardModel.getTimePlayed();
			Object message = "Glückwunsch, Du hast Verloren! Und nur " + timePlayed + " Sekunden dafür benötigt. \n";
			String title = "Du hast Verloren!";
			showMessage(message, title);
		}
		
		if(gameLogic.checkWin()){
			int timePlayed = boardModel.getTimePlayed();
			Object message = "Glückwunsch, Du hast Gewonnen! \n Du brauchtest "+timePlayed+" Sekunden um das Spiel zu beenden. \n";
			String title = "Du hast Gewonnen!";
			showMessage(message, title);
		}	
		
	}
	
	private void showMessage(Object message, String title){		
		JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.CLOSED_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
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
		gameLogic.setDifficulty(difficulty);	
		setChanged();
		notifyObservers(gameLogic);
	}
	public void setDifficultyUser(int rows, int cols, int anzahlMinen) {
		gameLogic.setDifficultyUser(rows, cols, anzahlMinen);
		setChanged();
		notifyObservers(gameLogic);
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

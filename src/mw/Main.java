package mw;

import mw.controller.BoardController;
import mw.controller.GameController;
import mw.model.BoardModel;
import mw.model.Difficulty;
import mw.view.BoardPanel;
import mw.view.GameFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Difficulty diff = Difficulty.EASY;
		// setzt die Spiel einstellungen
		BoardModel boardModel = new BoardModel(diff);
		BoardPanel boardPanel = new BoardPanel(boardModel);
		GameFrame gameFrame = new GameFrame(boardPanel, boardModel);

		boardModel.addObserver(gameFrame);
		// Erstellt das Spiel
		new BoardController(boardModel, boardPanel);
		new GameController(boardModel, gameFrame, boardPanel);

		gameFrame.pack();
		gameFrame.repaint();
		gameFrame.validate();

	}

}

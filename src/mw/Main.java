package mw;

import mw.controller.BoardController;
import mw.controller.GameController;
import mw.model.BoardModel;
import mw.view.BoardPanel;
import mw.view.GameFrame;

public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		BoardModel boardModel = new BoardModel();
		BoardPanel boardPanel = new BoardPanel(boardModel);
		GameFrame gameFrame = new GameFrame(boardPanel, boardModel);

		boardModel.addObserver(gameFrame);
		boardModel.addObserver(gameFrame);
		
		// Erstellt das SpielFeld
		new BoardController(boardModel, boardPanel);
		new GameController(boardModel, gameFrame, boardPanel);

		gameFrame.pack();
		gameFrame.repaint();
		gameFrame.validate();

	}

}

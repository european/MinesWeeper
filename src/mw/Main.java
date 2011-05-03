package mw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mw.controller.GameController;
import mw.model.BoardModel;
import mw.view.BoardPanel;
import mw.view.GameFrame;

public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {
	  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    GameController gameController = (GameController) context.getBean("gameController");
		
/*		BoardModel boardModel = new BoardModel();
		BoardPanel boardPanel = new BoardPanel();
		GameFrame gameFrame = new GameFrame(boardPanel, boardModel); 
//		BoardController boardController = null;

//		boardModel.addObserver(boardPanel);
//		boardModel.addObserver(gameFrame);
		
		// Erstellt das SpielFeld
//		new BoardController(boardModel, boardPanel);
		
		new GameController(gameFrame, boardPanel); */
		
//
//		gameFrame.pack();
//		gameFrame.repaint();
//		gameFrame.validate();

	}

}

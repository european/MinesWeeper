package mw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		GameController gameController = (GameController) context.getBean("gameController");
		
//		Das muss in die Main noch irgenwie mit rein ;) mit rein
		
//		BoardModel boardModel = new BoardModel();
//		BoardPanel boardPanel = new BoardPanel();
//		GameFrame gameFrame = new GameFrame(boardPanel, boardModel);
//
//		// Erstellt das SpielFeld
//
//		new GameController(gameFrame, boardPanel);

	}

}

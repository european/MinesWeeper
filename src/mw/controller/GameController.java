package mw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import mw.model.BoardModel;
import mw.model.Difficulty;
import mw.view.BoardPanel;
import mw.view.GameFrame;

public class GameController {

	private BoardModel boardModel;
	private BoardPanel boardPanel;
	private GameFrame gameFrame;
	
	public GameController(BoardModel boardModel, GameFrame gameFrame, BoardPanel boardPanel) {
        this.boardModel = boardModel;
        this.gameFrame = gameFrame;
        this.boardPanel = boardPanel;

        // listen for mouse clicks
        this.gameFrame.addClickListener(new NewSpielButtonListner());
        this.gameFrame.addDifficultyListener(new DiffChoiceListener());
        this.gameFrame.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
    }
    
    public GameController() throws Exception {
    	throw new Exception("GameController needs instances of: BoardModel, GameFrame and BoardPanel to function");
    }

    class NewSpielButtonListner implements MouseListener {
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
        	if(e.getSource() instanceof JMenuItem) {
        		JMenuItem source = (JMenuItem) e.getSource();
        		
        		if(source == gameFrame.getMnuSpielNeu()) {                    
                    boardModel.newGame();
                    boardPanel.setRebuild(true);
                    boardPanel.build();
                    gameFrame.resetTimePlayed();
                    gameFrame.resetProgressBar();
                    gameFrame.repaint();
            		gameFrame.validate();
        		}
        		else if(source == gameFrame.getMnuHelpInfo()) {
        			gameFrame.showAbout();
        		}
        		// Highscore
        	}

        }
        public void mouseReleased(MouseEvent e) {}
    }
    
    class DiffChoiceListener implements ActionListener, ItemListener {
		public void itemStateChanged(ItemEvent e) {}
		public void actionPerformed(ActionEvent e) {
			String diff = e.getActionCommand();
			Difficulty difficulty = Difficulty.fromString(diff);
			System.out.println(diff);
			System.out.println(difficulty);
			if(difficulty == Difficulty.BENUTZERDEFINIERT){
				//int rows = 0, cols = 0, anzahlMinen = 0;
				
				showBenutzerdefiniert();
				//boardModel.setDifficultyUser(rows, cols, anzahlMinen);
			}else{
				// 	change the difficulty in boardModel and create a new game
				boardModel.setDifficulty(difficulty);
			}
            boardModel.newGame();
			// rebuild the gamefield with MineButtons
			boardPanel.setRebuild(true);
            boardPanel.build();
            
            // update the parent gameFrame with new dimensionx
            gameFrame.resetTimePlayed();
            gameFrame.resetProgressBar();
            gameFrame.setFrameLocation();
            gameFrame.pack();
    		gameFrame.repaint();
    		gameFrame.validate();
		}
    }
    
    public void showBenutzerdefiniert(){
		// Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten    
    	
    	//NumberFormat format = NumberFormat.getInstance();  
    	
    	// Es können nur Zahlen eingegeben werden
    	JFormattedTextField hoehe = new JFormattedTextField(boardModel.getRows());
        ((NumberFormatter)hoehe.getFormatter()).setAllowsInvalid(false);
        
        JFormattedTextField breite = new JFormattedTextField(boardModel.getCols());
        ((NumberFormatter)breite.getFormatter()).setAllowsInvalid(false);
        
        JFormattedTextField minen = new JFormattedTextField(boardModel.getAnzahlMinen());
        ((NumberFormatter)minen.getFormatter()).setAllowsInvalid(false);
                  
        
        Object[] message = {"Höhe", hoehe, 
                "Breite", breite,
                "Minen", minen};

        JOptionPane pane = new JOptionPane( message, 
                                        JOptionPane.PLAIN_MESSAGE, 
                                        JOptionPane.OK_CANCEL_OPTION);        
        
       pane.createDialog(null, "Einstellungen").setVisible(true);
        
               
        int value = ((Integer)pane.getValue()).intValue();
        
        if(value == JOptionPane.OK_OPTION) {
        	int rows = Integer.valueOf(hoehe.getText()).intValue();
        	int cols = Integer.valueOf(breite.getText()).intValue();
        	int anzahlMinen = Integer.valueOf(minen.getText()).intValue();
        	boardModel.setDifficultyUser(rows, cols, anzahlMinen);
        }        
	}
    
	
	

}

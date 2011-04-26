package mw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
			if(difficulty == Difficulty.BENUTZERDEFINIERT){				
				showBenutzerdefiniert();
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
    
    /**
     * Erzeugt ein <code>JOptionPane</code> für die Benutzerdefinierten Einstellungen im Spiel
     * 
     * In die Felder können nur Zahlen eingegeben werden.
     */
    public void showBenutzerdefiniert(){	
    	// Es können nur Zahlen eingegeben werden
    	final JFormattedTextField hoehe = new JFormattedTextField(boardModel.getRows());
        ((NumberFormatter)hoehe.getFormatter()).setAllowsInvalid(false);
        
        final JFormattedTextField breite = new JFormattedTextField(boardModel.getCols());
        ((NumberFormatter)breite.getFormatter()).setAllowsInvalid(false);
        
        JFormattedTextField minen = new JFormattedTextField(boardModel.getAnzahlMinen());
        ((NumberFormatter)minen.getFormatter()).setAllowsInvalid(false);
                  
        final JTextField anzahl = new JTextField();
        
        Object[] message = {"Höhe", hoehe, 
                "Breite", breite,
                "Minen", minen,
                "Anzahl", anzahl};
        
        JOptionPane pane = new JOptionPane( message, 
                                        JOptionPane.PLAIN_MESSAGE, 
                                        JOptionPane.OK_CANCEL_OPTION);        
        
       pane.createDialog(null, "Einstellungen").setVisible(true);
        
       
               
        int value = ((Integer)pane.getValue()).intValue();
        
        if(value == JOptionPane.OK_OPTION) {
        	int rows = Integer.valueOf(hoehe.getText()).intValue();
        	int cols = Integer.valueOf(breite.getText()).intValue();
        	int anzahlMinen = Integer.valueOf(minen.getText()).intValue();
        	
        	boolean error = false;
        	
        	// Wenn die Minen mehr oder gleich der Feldgröße sind
        	if(anzahlMinen > (rows - 1) * (cols - 1)){
        		error = true;
        		// es sind zuviele Minen im Feld       		
        		JOptionPane warningPane = new JOptionPane("Es sind mehr Minen Gewählt, als das Feld groß ist" ,
        				JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
        		
        		warningPane.createDialog(null, "Warnung").setVisible(true);
        		int value2 = ((Integer)warningPane.getValue()).intValue();
                
                if(value2 == JOptionPane.OK_OPTION) {
                	showBenutzerdefiniert();
                }        		
        	}
        	// Wenn die Feldgröße zu klein ist
        	if(cols * cols <= 4){
        		error = true;
        		JOptionPane warningPane = new JOptionPane("Die Feldgröße ist zu klein" ,
        				JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
        		
        		warningPane.createDialog(null, "Warnung").setVisible(true);
        		int value2 = ((Integer)warningPane.getValue()).intValue();
                
                if(value2 == JOptionPane.OK_OPTION) {
                	showBenutzerdefiniert();
                }
        	}
        	// Wenn die Feldgröße zu groß ist
        	if(cols * rows > 30*24){
        		error = true;
        		// Das Feld ist zu Groß max 30*24
        		JOptionPane warningPane = new JOptionPane("Die Feldgröße ist zu groß max 50 * 50" ,
        				JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
        		
        		warningPane.createDialog(null, "Warnung").setVisible(true);
        		int value2 = ((Integer)warningPane.getValue()).intValue();
                
                if(value2 == JOptionPane.OK_OPTION) {
                	showBenutzerdefiniert();
                }
        	}
        	if(error != true){
        		boardModel.setDifficultyUser(rows, cols, anzahlMinen);
        	}
        }        
	}
    
	
	

}

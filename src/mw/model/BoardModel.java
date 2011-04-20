package mw.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class BoardModel extends Observable {

	private int rows;
	private int cols;
	private int anzahlMinen;
	private int restMinen;

	public static final int MINEVALUE = -1;
	public static final int EMPTYVALUE = 0;

	private Timer timer;
	private int timePlayed;	

	private int[][] board;
	private ButtonStatus buttonStatus;
	
	private Difficulty difficulty;

	public BoardModel(Difficulty difficulty) {
		setDifficulty(difficulty);		
		timer = new Timer(1000, new timerAction());		
	}

	
	/**
	 * Erzeugt ein neues Spiel mit den Anzahl der Minen 
	 *  sowie der Höhe und Breite des Spielfeldes
	 * 
	 * @param rows - Anzahl der Zeilen
	 * @param cols - Anzahl der Spalten
	 * @param anzahlMinen
	 */	
	public void newGame() {
		board = new int[getRows()][getCols()];

		int counter = 1;
		int x = 0, y = 0;

		while (counter <= getAnzahlMinen()) {
			x = (int) (Math.random() * getCols());
			y = (int) (Math.random() * getRows());

			if (board[x][y] != MINEVALUE) {
				// Add Mines
				board[x][y] = MINEVALUE;				
				
				// Add Numbers
                addHintNumbers(x, y);
                
				counter++;
			}

		}
		debug();
		timePlayed = 0;
		timer.stop();
	}
	
	public void debug(){
		for(int y = 0; y < getRows(); y++){
			for(int x = 0; x < getCols(); x++){
				if(board[y][x] == -1)
					System.out.print("* ");
				else
					System.out.print(board[y][x] + " ");
			}
			System.out.println("");
		}
		
	}
	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) { this.rows = rows; }

	/**
	 * @return the rows
	 */
	public int getRows() { return rows; }

	/**
	 * @param cols
	 *            the cols to set
	 */
	public void setCols(int cols) { this.cols = cols; }

	/**
	 * @return the cols
	 */
	public int getCols() { return cols; }

	/**
	 * @param anzahlMinen
	 *            the anzahlMinen to set
	 */
	public void setAnzahlMinen(int anzahlMinen) { this.anzahlMinen = anzahlMinen; }
	
	/**
	 * @return the anzahlMinen
	 */
	public int getAnzahlMinen() { return anzahlMinen; }
	
	/**
     * add 8 numbers around the given coordinates
     * 
     * @param x
     * @param y
     */
    private void addHintNumbers(int x, int y) {
        for (int i2 = y - 1; i2 <= y + 1; i2++) {
            for (int i1 = x - 1; i1 <= x + 1; i1++) {
                if(i1 < 0 || i1 >= getCols() || i2 < 0 || i2 >= getRows() || board[i1][i2] == MINEVALUE)
                    continue;
                board[i1][i2]++;
            }
        }
    }	

	class timerAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timePlayed++;

			setChanged();
			notifyObservers();
		}
	}
	
	/**
    * Prüft anhand der Koordinaten ob hier eine Mine liegt
    * @param x
    * @param y
    * @return boolean
    */
   public boolean isMine(int x, int y) {
       if(board[x][y] == MINEVALUE)
           return true;       
       return false;
   }
   
   /**
    * Entscheidet Anhand des Schwierigkeitsgrades die Anzahl der Minen, Spalten und Zeilen
    * @param difficulty
    */
   public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;

       switch(difficulty) {
           case MEDIUM:
        	   setRows(16);
        	   setCols(16);
               setAnzahlMinen(40);
               setRestMinen(40);
               break;
           case HARD:
        	   setRows(25);
        	   setCols(20);
               setAnzahlMinen(99);
               setRestMinen(99);
               break;
           case EASY:
               setRows(9);
               setCols(9);
               setAnzahlMinen(10);
               setRestMinen(10);
               break;
           default:
               System.out.println("Wrong difficultysetting");
               break;
       }
	}

	public Difficulty getDifficulty() { return difficulty; }

	/**
	 * @param buttonStatus the buttonStatus to set
	 */
	public void setButtonStatus(ButtonStatus buttonStatus) { this.buttonStatus = buttonStatus; }

	/**
	 * @return the buttonStatus
	 */
	public ButtonStatus getButtonStatus() { return buttonStatus; }
	
	/**
	 * @return the timePlayed
	 */
	public int getTimePlayed() { return timePlayed; }


	/**
	 * @param timePlayed the timePlayed to set
	 */
	public void setTimePlayed(int timePlayed) { this.timePlayed = timePlayed; }


	/**
	 * @param restMinen the restMinen to set
	 */
	public void setRestMinen(int restMinen) { this.restMinen = restMinen; }


	/**
	 * @return the restMinen
	 */
	public int getRestMinen() { return restMinen; }


	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

}

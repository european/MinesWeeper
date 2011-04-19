package mw.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class BoardModel extends Observable {

	private int rows;
	private int cols;
	private int anzahlMinen;

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
	//public void newGame(int rows, int cols, int anzahlMinen) {
	public void newGame() {
//		setRows(rows);
//		setCols(cols);
//		setAnzahlMinen(anzahlMinen);
		board = new int[getRows()][getCols()];

		int counter = 1;
		int x = 0, y = 0;

		while (counter <= getAnzahlMinen()) {
			x = (int) (Math.random() * getCols());
			y = (int) (Math.random() * getRows());

			if (board[x][y] != MINEVALUE) {
				board[x][y] = MINEVALUE;
				counter++;
			}

		}

		timePlayed = 0;
		timer.stop();

	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param cols
	 *            the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param anzahlMinen
	 *            the anzahlMinen to set
	 */
	public void setAnzahlMinen(int anzahlMinen) {
		this.anzahlMinen = anzahlMinen;
	}

	/**
	 * @return the anzahlMinen
	 */
	public int getAnzahlMinen() {
		return anzahlMinen;
	}

	class timerAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timePlayed++;

			setChanged();
			notifyObservers();
		}
	}
	
	/**
    *
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
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @param buttonStatus the buttonStatus to set
	 */
	public void setButtonStatus(ButtonStatus buttonStatus) {
		this.buttonStatus = buttonStatus;
	}

	/**
	 * @return the buttonStatus
	 */
	public ButtonStatus getButtonStatus() {
		return buttonStatus;
	}

}

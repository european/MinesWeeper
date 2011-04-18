package mw.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class MWModel extends Observable {

	private int rows;
	private int cols;
	private int anzahlMinen;

	public static final int MINEVALUE = -1;
	public static final int EMPTYVALUE = 0;

	private Timer timer;
	private int timePlayed;

	private int[][] board;

	public MWModel() {
		newGame(8, 8, 10);
	}

	/**
	 * Erzeugt ein neues Spiel
	 * 
	 * @param rows - Anzahl der Zeilen
	 * @param cols - Anzahl der Spalten
	 * @param anzahlMinen
	 */
	public void newGame(int rows, int cols, int anzahlMinen) {
		setRows(rows);
		setCols(cols);
		setAnzahlMinen(anzahlMinen);
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

			// notify gameFrame that time has changed
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

}

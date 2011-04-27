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
	private int feldZaehler;

	private static final int MINEVALUE = -1;
	private static final int EMPTYVALUE = 0;

	private Timer timer;
	private int timePlayed;

	private int[][] board;
	private boolean[][] checked;

	private Difficulty difficulty;

	public BoardModel(Difficulty difficulty) {
		setDifficulty(difficulty);
		timer = new Timer(1000, new TimerAction());
	}

	/**
	 * Erzeugt ein neues Spiel mit den Anzahl der Minen sowie der Höhe und
	 * Breite des Spielfeldes
	 * 
	 * @param rows - Anzahl der Zeilen
	 * @param cols - Anzahl der Spalten
	 * @param anzahlMinen
	 */
	public void newGame() {
		board = new int[getRows()][getCols()];
		checked = new boolean[getRows()][getCols()];

		feldZaehler = 0;

		int counter = 1;
		int x = 0, y = 0;

		while (counter <= getAnzahlMinen()) {
			y = (int) (Math.random() * getRows());
			x = (int) (Math.random() * getCols());

			if (board[y][x] != MINEVALUE) {
				// Add Mines
				board[y][x] = MINEVALUE;

				// Add Numbers
				addHintNumbers(y, x);

				counter++;
			}

		}
		debug();
		timePlayed = 0;
		timer.stop();
	}

	/**
	 * Entwickler Modus (Zeigt in der Console das Spielfeld
	 */
	private void debug() {
		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getCols(); x++) {
				if (board[y][x] == -1)
					System.out.print("* ");
				else
					System.out.print(board[y][x] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Öffnet das Aktuell geklickte Feld
	 * 
	 * @param y
	 * @param x
	 */
	public void openField(int y, int x) {
		if (!timer.isRunning())
			timer.start();
		feldZaehler++;

		if (board[y][x] != EMPTYVALUE) {
			checked[y][x] = true;
			return;
		}

		openFieldMore(y, x);
	}

	/**
	 * Öffnet weitere Spielfelder, wenn das aktuelle 0 war
	 * 
	 * @param startY
	 * @param startX
	 */
	private void openFieldMore(int startY, int startX) {
		for (int y = startY - 1; y <= startY + 1; y++) {
			for (int x = startX - 1; x <= startX + 1; x++) {
				// x oder y = start coords?
				if (y == startY && x == startX)
					continue;

				// Außerhalb des Bereiches?
				if (x < 0 || x > getCols() - 1 || y < 0 || y > getRows() - 1)
					continue;

				// Feld schon gechecked?
				if (checked[y][x])
					continue;

				checked[y][x] = true;

				// Wenn das Feld leer ist rufe die Funktion nocheinmal auf
				if (board[y][x] == EMPTYVALUE)
					openFieldMore(y, x);

				feldZaehler++;
			}
		}
	}

	/**
	 * Setzt alle Felder auf checked = true (eigentlich nur bei Spiel
	 * Beendigung)
	 */
	public void openAll() {
		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getCols(); x++) {
				checked[y][x] = true;
			}
		}
	}

	/**
	 * Zählt die Bomben der 8 Felder um die gegebene Koordinate zusammen
	 * 
	 * @param x
	 * @param y
	 */
	private void addHintNumbers(int y, int x) {
		for (int y2 = y - 1; y2 <= y + 1; y2++) {
			for (int x2 = x - 1; x2 <= x + 1; x2++) {
				if (x2 < 0 || x2 >= getCols() || y2 < 0 || y2 >= getRows() || board[y2][x2] == MINEVALUE)
					continue;
				board[y2][x2]++;
			}
		}
	}

	/**
	 * Zähler
	 * 
	 * @author niwe
	 * 
	 */
	class TimerAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timePlayed++;

			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Prüft anhand der Koordinaten ob hier eine Mine liegt
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isMine(int x, int y) {
		return board[x][y] == MINEVALUE;
	}

	/**
	 * Prüft ob das Spiel vorbei ist
	 * 
	 * @return boolean
	 */
	public boolean checkWin() {
		return getFeldZaehler() + getAnzahlMinen() == getRows() * getCols();
	}

	/**
	 * Setzt alle Felder auf checked = true und stopt die Zeit
	 */
	public void endGame() {
		openAll();
		timer.stop();
	}

	/**
	 * Entscheidet Anhand des Schwierigkeitsgrades die Anzahl der Minen, Spalten
	 * und Zeilen
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;

		setCols(difficulty.getCols());
		setRows(difficulty.getRows());
		setAnzahlMinen(difficulty.getAnzahlMinen());
		setRestMinen(difficulty.getAnzahlRestMinen());
	}

	public void setDifficultyUser(int rows, int cols, int anzahlMinen) {
		setRows(rows);
		setCols(cols);
		setAnzahlMinen(anzahlMinen);
		setRestMinen(anzahlMinen);
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @return the timePlayed
	 */
	public int getTimePlayed() {
		return timePlayed;
	}

	/**
	 * @param restMinen
	 *            the restMinen to set
	 */
	public void setRestMinen(int restMinen) {
		this.restMinen = restMinen;
	}

	/**
	 * @return the restMinen
	 */
	public int getRestMinen() {
		return restMinen;
	}

	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @return the checked
	 */
	public boolean[][] getChecked() {
		return checked;
	}

	/**
	 * @return the feldZaehler
	 */
	public int getFeldZaehler() {
		return feldZaehler;
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

}

package mw.model;

import java.util.Observable;

public class BoardModel extends Observable {

	private int rows;
	private int cols;
	private int anzahlMinen;
	private int restMinen;
	

	private static final int MINEVALUE = -1;
	private static final int EMPTYVALUE = 0;

//	private Timer timer;
//	private int timePlayed;

//	private int[][] board;
//	private boolean[][] checked;

	private Difficulty difficulty;

//	public BoardModel() {
//		Difficulty diff = null;
//		if(diff == null){
//			diff = Difficulty.EINFACH;
//		}
//		setDifficulty(diff);
//		timer = new Timer(1000, new TimerAction());
//	}

	/**
	 * Erzeugt ein neues Spiel mit den Anzahl der Minen sowie der Höhe und
	 * Breite des Spielfeldes
	 * 
	 * @param rows - Anzahl der Zeilen
	 * @param cols - Anzahl der Spalten
	 * @param anzahlMinen
	 */
	

//	/**
//	 * Entwickler Modus (Zeigt in der Console das Spielfeld
//	 */
//	private void debug() {
//		for (int y = 0; y < getRows(); y++) {
//			for (int x = 0; x < getCols(); x++) {
//				if (board[y][x] == -1)
//					System.out.print("* ");
//				else
//					System.out.print(board[y][x] + " ");
//			}
//			System.out.println("");
//		}
//	}

	

//	/**
//	 * Öffnet weitere Spielfelder, wenn das aktuelle 0 war
//	 * 
//	 * @param startY
//	 * @param startX
//	 */
//	private void openFieldMore(int startY, int startX) {
//		for (int y = startY - 1; y <= startY + 1; y++) {
//			for (int x = startX - 1; x <= startX + 1; x++) {
//				// x oder y = start coords?
//				if (y == startY && x == startX)
//					continue;
//
//				// Außerhalb des Bereiches?
//				if (x < 0 || x > getCols() - 1 || y < 0 || y > getRows() - 1)
//					continue;
//
//				// Feld schon gechecked?
//				if (checked[y][x])
//					continue;
//
//				checked[y][x] = true;
//
//				// Wenn das Feld leer ist rufe die Funktion nocheinmal auf
//				if (board[y][x] == EMPTYVALUE)
//					openFieldMore(y, x);
//
//				setFeldZaehler(getFeldZaehler() + 1);
//			}
//		}
//	}

//	/**
//	 * Setzt alle Felder auf checked = true (eigentlich nur bei Spiel
//	 * Beendigung)
//	 */
//	public void openAll() {
//		for (int y = 0; y < getRows(); y++) {
//			for (int x = 0; x < getCols(); x++) {
//				checked[y][x] = true;
//			}
//		}
//	}

//	/**
//	 * Zählt die Bomben der 8 Felder um die gegebene Koordinate zusammen
//	 * 
//	 * @param x
//	 * @param y
//	 */
//	private void addHintNumbers(int y, int x) {
//		for (int y2 = y - 1; y2 <= y + 1; y2++) {
//			for (int x2 = x - 1; x2 <= x + 1; x2++) {
//				if (x2 < 0 || x2 >= getCols() || y2 < 0 || y2 >= getRows() || board[y2][x2] == MINEVALUE)
//					continue;
//				board[y2][x2]++;
//			}
//		}
//	}

//	/**
//	 * Zähler
//	 * 
//	 * @author niwe
//	 * 
//	 */
//	class TimerAction implements ActionListener {
//
//		public void actionPerformed(ActionEvent e) {
//			timePlayed++;			
//
//			setChanged();
//			notifyObservers();
//		}
//	}

//	/**
//	 * Prüft anhand der Koordinaten ob hier eine Mine liegt
//	 * 
//	 * @param x
//	 * @param y
//	 * @return boolean
//	 */
//	public boolean isMine(int x, int y) {
//		return board[x][y] == MINEVALUE;
//	}

//	/**
//	 * Prüft ob das Spiel vorbei ist
//	 * 
//	 * @return boolean
//	 */
//	public boolean checkWin() {
//		return getFeldZaehler() + getAnzahlMinen() == getRows() * getCols();
//	}
//
//	/**
//	 * Setzt alle Felder auf checked = true und stopt die Zeit
//	 */
//	public void endGame() {
//		openAll();
//		timer.stop();
//	}

	

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
		setChanged();
        notifyObservers(this);
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {		
		this.cols = cols;
		setChanged();
        notifyObservers(this.cols);		
	}

	/**
	 * @return the anzahlMinen
	 */
	public int getAnzahlMinen() {
		return anzahlMinen;
	}

	/**
	 * @param anzahlMinen the anzahlMinen to set
	 */
	public void setAnzahlMinen(int anzahlMinen) {
		this.anzahlMinen = anzahlMinen;
		setChanged();
        notifyObservers(this.anzahlMinen);
	}

	/**
	 * @return the restMinen
	 */
	public int getRestMinen() {
		return restMinen;
	}

	/**
	 * @param restMinen the restMinen to set
	 */
	public void setRestMinen(int restMinen) {
		this.restMinen = restMinen;
		setChanged();
        notifyObservers(this.restMinen);
	}

//	/**
//	 * @return the feldZaehler
//	 */
//	public int getFeldZaehler() {
//		return feldZaehler;
//	}
//
//	/**
//	 * @param feldZaehler the feldZaehler to set
//	 */
//	public void setFeldZaehler(int feldZaehler) {
//		this.feldZaehler = feldZaehler;
//		setChanged();
//        notifyObservers(this);
//	}
//
//	/**
//	 * @return the timer
//	 */
//	public Timer getTimer() {
//		return timer;
//	}
//
//	/**
//	 * @param timer the timer to set
//	 */
//	public void setTimer(Timer timer) {
//		this.timer = timer;
//		setChanged();
//        notifyObservers(this);
//	}
//
//	/**
//	 * @return the timePlayed
//	 */
//	public int getTimePlayed() {
//		return timePlayed;
//	}
//
//	/**
//	 * @param timePlayed the timePlayed to set
//	 */
//	public void setTimePlayed(int timePlayed) {
//		this.timePlayed = timePlayed;
//		setChanged();
//        notifyObservers(this);
//	}
//
//	/**
//	 * @return the board
//	 */
//	public int[][] getBoard() {
//		return board;
//	}
//
//	/**
//	 * @param board the board to set
//	 */
//	public void setBoard(int[][] board) {
//		this.board = board;
//		setChanged();
//        notifyObservers(this);
//	}
//
//	/**
//	 * @return the checked
//	 */
//	public boolean[][] getChecked() {
//		return checked;
//	}
//
//	/**
//	 * @param checked the checked to set
//	 */
//	public void setChecked(boolean[][] checked) {
//		this.checked = checked;
//		setChanged();
//        notifyObservers(this);
//	}

	/**
	 * @return the minevalue
	 */
	public int getMinevalue() {
		return MINEVALUE;
	}

	/**
	 * @return the emptyvalue
	 */
	public int getEmptyvalue() {
		return EMPTYVALUE;
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Entscheidet Anhand des Schwierigkeitsgrades die Anzahl der Minen, Spalten
	 * und Zeilen
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(Difficulty difficulty) {
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

	

}

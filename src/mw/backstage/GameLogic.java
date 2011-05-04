package mw.backstage;

import javax.swing.Timer;

import mw.model.BoardModel;
import mw.model.Difficulty;

public class GameLogic {

	private BoardModel boardModel;

	private int restMinen;

	private int[][] board;
	private boolean[][] checked;

	private Timer timer;
	private int timePlayed;

	private int feldZaehler;

	public GameLogic(BoardModel boardModel) {
		this.boardModel = boardModel;

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
		checked[y][x] = true;
		
		if (board[y][x] != boardModel.getEmptyvalue())
			return;
		
		if (checkWin())
			return;

		if (checkLoose(y, x))
			return;

		openMoreFields(y, x);
	}

	private void openMoreFields(int startY, int startX) {
		for (int y = startY - 1; y <= startY + 1; y++) {
			for (int x = startX - 1; x <= startX + 1; x++) {
				// x oder y = start coords?
				if (y == startY && x == startX){
					System.out.println("hier ich return");
					if(checked[y][x])
						System.out.println("wurde aber eigentlich gechecket");
					continue;
				}

				// Außerhalb des Bereiches?
				if (x < 0 || x > boardModel.getCols() - 1 || y < 0 || y > boardModel.getRows() - 1)
					continue;

				// Feld schon gechecked?
				if (checked[y][x])
					continue;

				if (checkWin())
					continue;

				checked[y][x] = true;

				// Wenn das Feld leer ist rufe die Funktion nocheinmal auf
				if (board[y][x] == boardModel.getEmptyvalue())
					openMoreFields(y, x);

				feldZaehler++;
			}
		}
	}

	public void newGame() {
		setBoard(new int[boardModel.getRows()][boardModel.getCols()]);
		setChecked(new boolean[boardModel.getRows()][boardModel.getCols()]);

		setFeldZaehler(0);

		int counter = 1;
<<<<<<< HEAD
		int x = 0; 
		int y = 0;
=======
		
		int y = 0;
		int x = 0;
>>>>>>> refs/remotes/origin/DevMitSpring

		while (counter <= boardModel.getAnzahlMinen()) {
			y = (int) (Math.random() * boardModel.getRows());
			x = (int) (Math.random() * boardModel.getCols());

			if (board[y][x] != boardModel.getMinevalue()) {
				// Add Mines
				board[y][x] = boardModel.getMinevalue();

				// Add Numbers
				addHintNumbers(y, x);

				counter++;
			}

		}
		debug();
		setTimePlayed(0);
		timer.stop();
	}

	/**
	 * Setzt alle Felder auf checked = true (eigentlich nur bei Spiel
	 * Beendigung)
	 */
	public void openAll() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
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
				if (x2 < 0 || x2 >= boardModel.getCols() || y2 < 0 || y2 >= boardModel.getRows()
						|| board[y2][x2] == boardModel.getMinevalue())
					continue;
				board[y2][x2]++;
			}
		}
	}

	/**
	 * Prüft ob das Spiel vorbei ist
	 * 
	 * @return boolean
	 */
	public boolean checkWin() {		
		return coutFeldZaehler() + boardModel.getAnzahlMinen() == boardModel.getRows() * boardModel.getCols();
	}

	public boolean checkLoose(int y, int x) {
		return isMine(y, x);
	}

	/**
	 * Setzt alle Felder auf checked = true und stopt die Zeit
	 */
	public void endGame() {
		openAll();
		timer.stop();
	}

	/**
	 * Entwickler Modus (Zeigt in der Console das Spielfeld
	 */
	private void debug() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				if (board[y][x] == -1)
					System.out.print("* ");
				else
					System.out.print(board[y][x] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * Entscheidet Anhand des Schwierigkeitsgrades die Anzahl der Minen, Spalten
	 * und Zeilen
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(Difficulty difficulty) {
		boardModel.setCols(difficulty.getCols());
		boardModel.setRows(difficulty.getRows());
		boardModel.setAnzahlMinen(difficulty.getAnzahlMinen());
		setRestMinen(difficulty.getAnzahlRestMinen());
	}

	public void setDifficultyUser(int rows, int cols, int anzahlMinen) {
		boardModel.setRows(rows);
		boardModel.setCols(cols);
		boardModel.setAnzahlMinen(anzahlMinen);
		setRestMinen(anzahlMinen);
	}

	/**
	 * Prüft anhand der Koordinaten ob hier eine Mine liegt
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isMine(int y, int x) {
		return board[y][x] == boardModel.getMinevalue();
	}

	/**
	 * @return the feldZaehler
	 */
	public int getFeldZaehler() {
		return feldZaehler;
	}

	/**
	 * @param feldZaehler
	 *            the feldZaehler to set
	 */
	public void setFeldZaehler(int feldZaehler) {
		this.feldZaehler = feldZaehler;
	}
	
	public int coutFeldZaehler(){
		int count = 0;
		
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				if (checked[y][x])
					count++;			
			}
		}
		
		return count;
		
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer
	 *            the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the timePlayed
	 */
	public int getTimePlayed() {
		return timePlayed;
	}

	/**
	 * @param timePlayed
	 *            the timePlayed to set
	 */
	public void setTimePlayed(int timePlayed) {
		this.timePlayed = timePlayed;
	}

	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @param board
	 *            the board to set
	 */
	private void setBoard(int[][] board) {
		this.board = board;
	}

	/**
	 * @return the checked
	 */
	public boolean[][] getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(boolean[][] checked) {
		this.checked = checked;
	}

	public void setRestMinen(int restMinen) {
		if (restMinen == -1 || restMinen == +1) {
			this.restMinen = getRestMinen() + restMinen;
		} else {
			this.restMinen = restMinen;
		}

	}

	public int getRestMinen() {
		return this.restMinen;
	}

}

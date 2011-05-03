package mw.model;


public class BoardModel {

	private int rows;
	private int cols;
	private int anzahlMinen;	

	private int timePlayed;
	
	private int feldZaehler;
	

	private static final int MINEVALUE = -1;
	private static final int EMPTYVALUE = 0;

	private Difficulty difficulty;

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
	}

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
	 * @return the timePlayed
	 */
	public int getTimePlayed() {
		return timePlayed;
	}

	/**
	 * @param timePlayed the timePlayed to set
	 */
	public void setTimePlayed(int timePlayed) {
		this.timePlayed = timePlayed;
	}

	/**
	 * @return the feldZaehler
	 */
	public int getFeldZaehler() {
		return feldZaehler;
	}

	/**
	 * @param feldZaehler the feldZaehler to set
	 */
	public void setFeldZaehler(int feldZaehler) {
		this.feldZaehler = feldZaehler;		
	}

	

}

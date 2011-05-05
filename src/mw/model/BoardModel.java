package mw.model;

public class BoardModel {

	private int rows;
	private int cols;
	private int anzahlMinen;

	private static final int MINEVALUE = -1;
	private static final int EMPTYVALUE = 0;
	private static final int FLAGVALUE = -2;

	private Difficulty difficulty;

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
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
	 * @param cols
	 *            the cols to set
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
	 * @param anzahlMinen
	 *            the anzahlMinen to set
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
	 * @return the flagvalue
	 */
	public int getFlagvalue() {
		return FLAGVALUE;
	}

}

package mw.model;

public class BoardModel {

	private static final int MINEVALUE = -1;
	private static final int EMPTYVALUE = 0;
	private static final int FLAGVALUE = -2;

	private Difficulty difficulty;

	/**
	 * @return the rows
	 */
	public int getRows() {
		return getDifficulty().getRows();
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return getDifficulty().getCols();
	}
	/**
	 * @return the anzahlMinen
	 */
	public int getAnzahlMinen() {
		return getDifficulty().getAnzahlMinen();
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
	 * @return the flagvalue
	 */
	public int getFlagvalue() {
		return FLAGVALUE;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

}

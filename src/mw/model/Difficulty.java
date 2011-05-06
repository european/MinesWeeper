package mw.model;

public enum Difficulty {
	EINFACH(9, 9, 10), MITTEL(16, 16, 40), SCHWER(30, 16, 99), BENUTZERDEFINIERT() {
		@Override
		public void setRows(int rows) {
			this.rows = rows;
		}

		@Override
		public void setCols(int cols) {
			this.cols = cols;
		}

		@Override
		public void setAnzahlMinen(int anzahlMinen) {
			this.anzahlMinen = anzahlMinen;
		}
	};

	int rows;

	int cols;

	int anzahlMinen;

	/**
	 * @param rows
	 * @param cols
	 * @param anzahlMinen
	 */
	private Difficulty(int rows, int cols, int anzahlMinen) {
		this.rows = rows;
		this.cols = cols;
		this.anzahlMinen = anzahlMinen;
	}
	/**
	 * 
	 */
	private Difficulty() {

	}

	public void setRows(int rows) {
		throw new UnsupportedOperationException("Setter für Standart Difficultys darf nicht aufgerufen werden");
	}

	public void setCols(int cols) {
		throw new UnsupportedOperationException("Setter für Standart Difficultys darf nicht aufgerufen werden");
	}

	public void setAnzahlMinen(int anzahlMinen) {
		throw new UnsupportedOperationException("Setter für Standart Difficultys darf nicht aufgerufen werden");
	}

	/**
	 * Anhand des String wird der Enum Wert ermittelt und zurück gegeben
	 * 
	 * @param name
	 * @return Enum
	 */
	public static Difficulty fromString(String name) {
		if (Difficulty.class != null && name != null) {
			try {
				return Enum.valueOf(Difficulty.class, name.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
			}
		}

		return null;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return the anzahlMinen
	 */
	public int getAnzahlMinen() {
		return anzahlMinen;
	}
}

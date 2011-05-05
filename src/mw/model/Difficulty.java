package mw.model;

public enum Difficulty {
  EINFACH(9, 9, 10), MITTEL(16, 16, 40), SCHWER(30, 16, 99), BENUTZERDEFINIERT(9, 9, 10);

  int rows;

  int cols;

  int anzahlMinen;

  int anzahlRestMinen;

  String diff;

  /**
   * @param rows
   * @param cols
   * @param anzahlMinen
   */
  private Difficulty(int rows, int cols, int anzahlMinen) {
    this.rows = rows;
    this.cols = cols;
    this.anzahlMinen = anzahlMinen;
    this.anzahlRestMinen = anzahlMinen;
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
      } catch (IllegalArgumentException ex) {}
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

  /**
   * @return the anzahlRestMinen
   */
  public int getAnzahlRestMinen() {
    return anzahlRestMinen;
  }

  public String getDiff() {
    switch (this) {
      case EINFACH:
        diff = "Einfach";
        break;
      case MITTEL:
        diff = "Mittel";
        break;
      case SCHWER:
        diff = "Schwer";
        break;
      default:
        diff = null;
    }
    return diff;
  }

}

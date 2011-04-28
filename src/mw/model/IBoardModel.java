package mw.model;

public interface IBoardModel {

	void newGame();

	void endGame();

	int getTimePlayed();

	void openField(int i, int j);

	boolean checkWin();

	int getRestMinen();

	void setRestMinen(int i);

	void setDifficulty(Difficulty difficulty);

	int getRows();

	int getCols();

	int getAnzahlMinen();

	void setDifficultyUser(int rows, int cols, int anzahlMinen);
	
}

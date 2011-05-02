package mw.view;

import java.awt.event.MouseListener;

import mw.backstage.GameLogic;


public interface IBoardPanel {
	
	/**
	 * Erstellt das Spielfeld
	 */
	void build();
	/**
	 * Fügt die MouseListener hinzu
	 * 
	 * @param e
	 *            MousListener
	 */
	void addClickListener(MouseListener e);
	/**
	 * Öffnet alle Felder, bei denen getChecked() = true ist
	 */
	void redraw();
	/**
	 * @param rebuild
	 *            the rebuild to set
	 */
	void setRebuild(boolean rebuild);
	void repaint();
	
	void setRows(int rows);
	
	void setCols(int cols);
	int getCols();
	int getRows();
	
	void setGameLogic(GameLogic gameLogic);
	
	GameLogic getGameLogic();
}

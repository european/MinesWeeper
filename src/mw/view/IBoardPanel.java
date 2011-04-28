package mw.view;

import java.awt.event.MouseListener;


public interface IBoardPanel {
	
	/**
	 * Erstellt das Spielfeld
	 */
	public void build();
	/**
	 * Fügt die MouseListener hinzu
	 * 
	 * @param e
	 *            MousListener
	 */
	public void addClickListener(MouseListener e);
	/**
	 * Öffnet alle Felder, bei denen getChecked() = true ist
	 */
	public void redraw();
	/**
	 * @param rebuild
	 *            the rebuild to set
	 */
	public void setRebuild(boolean rebuild);
	public void repaint();
	
}

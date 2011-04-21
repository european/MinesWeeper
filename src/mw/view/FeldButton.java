package mw.view;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import mw.model.ButtonStatus;

@SuppressWarnings("serial")
public class FeldButton extends JToggleButton {
	private Icon mineIcon = new ImageIcon(FeldButton.class.getResource("/mw/images/mine.png"));
	private Icon mineExplodeIcon = new ImageIcon(FeldButton.class.getResource("/mw/images/mineExplode.png"));

	private Icon falg = new ImageIcon(FeldButton.class.getResource("/mw/images/flag.png"));

	private ButtonStatus buttonStatus;

	private int[] coords;

	private boolean mine;

	/**
	 * Erzeugt einen neuen FeldButton
	 * 
	 * @param row
	 * @param column
	 */
	public FeldButton() {
		coords = new int[2];
		setButtonStatus(getDefault());
		setFont(new Font(getText(), Font.BOLD, 2));
		setVisible(true);
	}
	/**
	 * Zum Resseten des Buttons
	 */
	public void reset() {
		setEnabled(true);
		setSelected(false);
		setButtonStatus(getDefault());		
	}

	/**
	 * Setzt auf das Aktuelle Feld den Enum Wert Flag
	 * 
	 * @return (enum) FeldEigenschaft = Flag
	 */
	private ButtonStatus getFlagged() {
		return ButtonStatus.FLAG;
	}

//	private ButtonStatus getClicked() {
//		return ButtonStatus.CLICKED;
//	}
//
//	private ButtonStatus getMine() {
//		return ButtonStatus.MINE;
//	}
//	
//	private ButtonStatus getMineExplode() {
//		return ButtonStatus.MINE_EXPLODED;
//	}

	private ButtonStatus getDefault() {
		return ButtonStatus.DEFAULT;
	}

	
	public boolean isFlagged() {
		return getButtonStatus() == getFlagged();
	}

	/**
	 * @param buttonStatus
	 *            the buttonStatus to set
	 */
	public void setButtonStatus(ButtonStatus buttonStatus) {
		this.buttonStatus = buttonStatus;
	}

	/**
	 * @return the buttonStatus
	 */
	public ButtonStatus getButtonStatus() {
		return buttonStatus;
	}

	public void toggleFlagButton() {
		if (isFlagged()) {
			setButtonStatus(getDefault());
			this.setIcon(null);
			this.setEnabled(true);			
		} else {
			setButtonStatus(getFlagged());
			this.setIcon(falg);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setCoords(int x, int y) {
		coords[0] = x;
		coords[1] = y;
	}

	public int[] getCoords() {
		return coords;
	}

	/**
	 * 
	 * @param isMine
	 */
	public void setIsMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isMine() {
		return mine;
	}

	/**
	 * @return the mineIcon
	 */
	public void setMineIcon() {
		this.setIcon(mineIcon);
	}

	/**
	 * @return the mineExplodeIcon
	 */
	public void getMineExplodeIcon() {
		this.setIcon(mineExplodeIcon);
	}	
}
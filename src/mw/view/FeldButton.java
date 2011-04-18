package mw.view;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import mw.model.ButtonStatus;

@SuppressWarnings("serial")
public class FeldButton extends JToggleButton {
	private Icon mine = new ImageIcon(FeldButton.class.getResource("/mw/images/mine.png"));
	private Icon falg = new ImageIcon(FeldButton.class.getResource("/mw/images/flag.png"));
	
	private ButtonStatus buttonStatus;	
	
	private int row;
	private int column;

	/**
	 * Erzeugt einen neuen FeldButton
	 * @param row
	 * @param column
	 */
	public FeldButton(int row, int column)	{
		this.row = row;
		this.column = column;
		
		setButtonStatus(setDefault());
	}	
	
	public void reset(){
		setEnabled(true);
		setSelected(false);
		setButtonStatus(setDefault());
	}
	/**
	 * 
	 * @return row - Anzahl der Zeilen
	 */
	public int getRow() { return row; }
	
	/**
	 * 
	 * @return column - Anzahl der Spalten
	 */
	public int getColumn() { return column; }
	
	/**
	 * Setzt auf das Aktuelle Feld den Enum Wert Flag
	 * @return (enum) FeldEigenschaft = Flag
	 */
	public ButtonStatus setFlagged(){
		return ButtonStatus.FLAG;
	}
	public ButtonStatus setClicked(){
		return ButtonStatus.CLICKED;
	}
	public ButtonStatus setMined(){
		return ButtonStatus.BOMBE;
	}
	public ButtonStatus setDefault(){
		return ButtonStatus.DEFAULT;
	}	
	/**
	 * @param buttonStatus the buttonStatus to set
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
	
	public void flagButton(){
		if(getButtonStatus() == ButtonStatus.FLAG){
			setButtonStatus(setDefault());
		}else{
			setButtonStatus(setFlagged());
		}
	}

}
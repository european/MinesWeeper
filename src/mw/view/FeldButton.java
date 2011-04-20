package mw.view;

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
	
//	private int row;
//	private int column;
	
	private int[] coords;
	
	private boolean mine;

	/**
	 * Erzeugt einen neuen FeldButton
	 * @param row
	 * @param column
	 */
	public FeldButton()	{
		coords = new int[2];
		
		
		
		//this.row = row;
		//this.column = column;		
		setButtonStatus(getDefault());
	}	
	
	public void reset(){
		setEnabled(true);
		setSelected(false);
		setButtonStatus(getDefault());
	}
	/**
	 * 
	 * @return row - Anzahl der Zeilen
	 */
//	public int getRow() { return row; }
	
	/**
	 * 
	 * @return column - Anzahl der Spalten
	 */
//	public int getColumn() { return column; }
	
	/**
	 * Setzt auf das Aktuelle Feld den Enum Wert Flag
	 * @return (enum) FeldEigenschaft = Flag
	 */
	public ButtonStatus getFlagged(){
		return ButtonStatus.FLAG;
	}
	public ButtonStatus getClicked(){
		return ButtonStatus.CLICKED;
	}
	public ButtonStatus getMined(){
		return ButtonStatus.MINE;
	}
	public ButtonStatus getDefault(){
		return ButtonStatus.DEFAULT;
	}	
	
	public boolean isFlagged(){
		if(getButtonStatus() == getFlagged()){
			return true;
		}		
		return false;		
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
	
	
	public void toggleFlagButton(){
		if(isFlagged()){
			setButtonStatus(getDefault());
			this.setIcon(null);
		}else{
			setButtonStatus(getFlagged());
			this.setIcon(falg);
		}
	}

	/**
    *
    * @param x
    * @param y
    */
   public void setCoords(int x, int y){
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
   public void setIsMine(boolean mine) { this.mine = mine; }

  public boolean isMine() {
  	   return mine;
  }
  
  /**
	 * @return the mine
	 */
	public void getMineIcon() {
		this.setIcon(mineIcon);
	}

	/**
	 * @return the mineExplode
	 */
	public void getMineExplodeIcon() {
		this.setIcon(mineExplodeIcon);
	}


}
package mw.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mw.model.BoardModel;

public class BoardPanel extends JPanel {	
	
	private BoardModel boardModel;
	
	private FeldButton board[];	
	
	private MouseListener mouseListener;
	
	public BoardPanel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	/**
	 * Erstellt das Spielfeld
	 */
	public void build() {
		// TODO Auto-generated method stub
		
	}
	
//	private void buildGameBoard(){
//		gamePanel.removeAll();
//		
//		board = new FeldButton[getRows()][getCols()];
//		
//		gamePanel.setLayout(new GridLayout(getRows(), getCols()));			
//		
//		for(int y = 0; y < getRows(); y++){
//			for(int x = 0; x < getCols(); x++){
//				board[y][x] = new FeldButton(y,x); 
//				board[y][x].setPreferredSize(new Dimension(40, 28));				
//				gamePanel.add(board[y][x]);				
//			}
//		}	
//		
//		gamePanel.setEnabled(true);
//		this.repaint();
//		this.validate();
//	}
	
	/**
     * 
     * @param e MousListener
     */
    public void addClickListener(MouseListener e) {
    	mouseListener = e;
    	
    	addListeners();
    }

	private void addListeners() {
        for(FeldButton but:board)
            but.addMouseListener(mouseListener);
	}


	
	
}
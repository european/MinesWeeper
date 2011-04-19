package mw.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mw.model.BoardModel;

public class BoardPanel extends JPanel {	
	
	private BoardModel boardModel;
	
	private FeldButton[][] board;	
	
	private MouseListener mouseListener;
	
	public BoardPanel(BoardModel boardModel) {
		this.boardModel = boardModel;		
		
	}
	
	/**
	 * Erstellt das Spielfeld
	 */
//	public void build() {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void build(){
//		this.removeAll();
		System.out.println("ich baue");
		
		board = new FeldButton[boardModel.getRows()][boardModel.getCols()];
		
		this.setLayout(new GridLayout(boardModel.getRows(), boardModel.getCols()));			
		
		for(int y = 0; y < boardModel.getRows(); y++){
			for(int x = 0; x < boardModel.getCols(); x++){
				board[y][x] = new FeldButton(y,x); 
				board[y][x].setPreferredSize(new Dimension(40, 28));				
				this.add(board[y][x]);				
			}
		}	
		
//		this.setEnabled(true);
//		this.repaint();
//		this.validate();
	}
	
	/**
     * 
     * @param e MousListener
     */
    public void addClickListener(MouseListener e) {
    	mouseListener = e;
    	
    	addListeners();
    }

	private void addListeners() {
//        for(FeldButton[] but:board)
//            but.addMouseListener(mouseListener);
	}


	
	
}
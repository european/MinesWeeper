package mw.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mw.model.BoardModel;
import mw.model.ButtonStatus;

@SuppressWarnings("serial")
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
	public void build() {
		board = new FeldButton[boardModel.getRows()][boardModel.getCols()];

		this.setLayout(new GridLayout(boardModel.getRows(), boardModel.getCols()));

		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				board[y][x] = new FeldButton();
				board[y][x].setCoords(x, y);
				board[y][x].setPreferredSize(new Dimension(40, 28));
				board[y][x].setIsMine(boardModel.isMine(y, x));
				this.add(board[y][x]);
			}
		}
	}

	/**
	 * Fügt die MouseListener hinzu
	 * 
	 * @param e
	 *            MousListener
	 */
	public void addClickListener(MouseListener e) {
		mouseListener = e;

		addListeners();
	}

	private void addListeners() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				board[y][x].addMouseListener(mouseListener);
			}
		}
	}

	public void showField() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				if (board[y][x].getButtonStatus() == ButtonStatus.DEFAULT) {
					if (board[y][x].isMine()) {
						board[y][x].getMineExplodeIcon();
					} else {
						board[y][x].setText(String.valueOf(boardModel
								.getBoard()[y][x]));
					}
				}

			}
		}
	}

}
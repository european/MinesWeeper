package mw.view;

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
	private boolean rebuild = false, initBuild = true;;

	public BoardPanel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	/**
	 * Erstellt das Spielfeld
	 */
	public void build() {
		if (initBuild || rebuild) {
			this.removeAll();
		}
		board = new FeldButton[boardModel.getRows()][boardModel.getCols()];

		this.setLayout(new GridLayout(boardModel.getRows(), boardModel.getCols()));

		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				if (initBuild || rebuild) {
					board[y][x] = new FeldButton();
					board[y][x].setCoords(x, y);
					this.add(board[y][x]);
				}
				board[y][x].setIsMine(boardModel.isMine(y, x));
				board[y][x].reset();
			}
		}
		this.setEnabled(true);

		if (rebuild) {
			rebuild = false;
			addListeners();
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

	/**
	 * Setzt auf die Buttons den Listener
	 */
	private void addListeners() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				board[y][x].addMouseListener(mouseListener);
			}
		}
	}

	/**
	 * Öffnet alle Felder, bei denen getChecked() = true ist
	 */
	public void redraw() {
		for (int y = 0; y < boardModel.getRows(); y++) {
			for (int x = 0; x < boardModel.getCols(); x++) {
				if (boardModel.getChecked()[y][x]) {
					if (board[y][x].isMine() && board[y][x].getButtonStatus() != ButtonStatus.MINE_EXPLODED) {
						board[y][x].getMineIcon();

					}
					} else if (board[y][x].getButtonStatus() == ButtonStatus.MINE_EXPLODED) {

						board[y][x].getMineExplodeIcon();

					} else {
						// Damit 0 nicht angezeigt wird
						if (boardModel.getBoard()[y][x] != 0) {
							board[y][x].setText(String.valueOf(boardModel.getBoard()[y][x]));
						}

						board[y][x].getFieldDisabledIcon();
					}
					board[y][x].setEnabled(false);
					board[y][x].setButtonStatus(ButtonStatus.CLICKED);
				
			}
		}
	}

	/**
	 * @param rebuild
	 *            the rebuild to set
	 */
	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
	}

}

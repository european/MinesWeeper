package mw.view;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mw.model.BoardModel;
import mw.model.ButtonStatus;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements IBoardPanel, Observer {

	private BoardModel boardModel;

	private FeldButton[][] board;

	private MouseListener mouseListener;
	private boolean rebuild = false, initBuild = true;;

	public BoardPanel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	private int rows, cols;

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
	}

	/**
	 * @param rebuild
	 *            the rebuild to set
	 */
	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs == boardModel) {
			setRows(boardModel.getRows());
			setCols(boardModel.getCols());
		}		
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

}
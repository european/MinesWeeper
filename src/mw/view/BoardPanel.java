package mw.view;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mw.backstage.GameLogic;
import mw.model.ButtonStatus;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements IBoardPanel {

	private GameLogic gameLogic;

	private FeldButton[][] board;

	private MouseListener mouseListener;
	private boolean rebuild = false, initBuild = true;

	private int rows;
	private int cols;

	/**
	 * Erstellt das Spielfeld
	 */
	public void build() {
		if (initBuild || rebuild) {
			this.removeAll();
		}
		board = new FeldButton[getRows()][getCols()];

		this.setLayout(new GridLayout(getRows(), getCols()));

		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				if (initBuild || rebuild) {
					board[y][x] = new FeldButton();
					board[y][x].setCoords(y, x);
					this.add(board[y][x]);
				}
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
		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getCols(); x++) {
				board[y][x].addMouseListener(mouseListener);
			}
		}
	}

	/**
	 * Öffnet alle Felder, bei denen getChecked() = true ist
	 */
	public void redraw() {
		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getCols(); x++) {
				if (getGameLogic().getChecked()[y][x]) {
					if (board[y][x].getButtonStatus() == ButtonStatus.MINE || getGameLogic().isMine(y, x)) {
						board[y][x].getMineIcon();
					} else if (board[y][x].getButtonStatus() == ButtonStatus.MINE_EXPLODED) {
						board[y][x].getMineExplodeIcon();
					} else {
						// Damit 0 nicht angezeigt wird
						if (getGameLogic().getBoard()[y][x] != 0) {
							board[y][x].setText(String.valueOf(getGameLogic().getBoard()[y][x]));
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

	/**
	 * @param rows
	 *            the rows to set
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
	 * @param cols
	 *            the cols to set
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

	@Override
	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;

	}

	@Override
	public GameLogic getGameLogic() {
		return this.gameLogic;
	}

}
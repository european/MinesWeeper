package mw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;

import mw.controller.MWController;

import mw.model.MWModel;


@SuppressWarnings("serial")
public class MWViewer extends JFrame implements Observer {

	private MWController controller;
	//private MWModel m_model;
	
	private JLabel lZeit;	
	private JLabel lRestMinen;
	
	private JProgressBar progressBar;
	
	private JPanel mainPanel;
	private JPanel gamePanel;
	
	private FeldButton[][] board;
	
	private int rows = 8;
	private int cols = 8;
		
	

	public MWViewer(MWController controller){
		super("MinesWeeper");
		this.controller = controller;
		//this.m_model = model;
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		init();
		pack();
		setVisible(true);
		
		
		/* Center the frame */
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frameDim = getBounds();
		setLocation((screenDim.width - frameDim.width) / 2,	(screenDim.height - frameDim.height) / 2);
		
	}
	
	private void init(){
		
		mainPanel = new JPanel();		
		gamePanel = new JPanel();			
		lZeit = new JLabel();
		buildGameBoard();
		
		mainPanel.add(lZeit);
		mainPanel.add(gamePanel);	
		this.add(getJMenuBar(), BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		//this.add(getJProgressBar(), BorderLayout.SOUTH);
	}
	
	/**
	 * Diese Methode erstellt die JMenuBar
	 * 
	 * @return JMenuBar
	 */
	public JMenuBar getJMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu mDatei, mExtra, mHelp;
		JMenuItem dateiNeu, dateiOptions, dateiBeenden, extraHelp, helpInfo;

		// Datei
		mDatei = new JMenu("Datei");
		mDatei.setMnemonic(KeyEvent.VK_D);
		dateiNeu = new JMenuItem("Neues Spiel");
		

		dateiOptions = new JMenuItem("Einstellungen");
		dateiOptions.setMnemonic(KeyEvent.VK_E);

		dateiBeenden = new JMenuItem("Beenden");
		dateiBeenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));

		mDatei.add(dateiNeu);
		mDatei.add(dateiOptions);
		mDatei.add(dateiBeenden);
		mb.add(mDatei);

		// Extras
		mExtra = new JMenu("Extras");

		extraHelp = new JMenuItem("Hilfezug");
		mExtra.add(extraHelp);
		// mb.add(mExtra);

		// Hilfe
		mHelp = new JMenu("?");
		helpInfo = new JMenuItem("Info");
		mHelp.add(helpInfo);
		mb.add(mHelp);

		return mb;
	}
	
	private void buildGameBoard(){
		gamePanel.removeAll();
		
		board = new FeldButton[getRows()][getCols()];
		
		gamePanel.setLayout(new GridLayout(getRows(), getCols()));			
		
		for(int y = 0; y < getRows(); y++){
			for(int x = 0; x < getCols(); x++){
				board[y][x] = new FeldButton(y,x); 
				board[y][x].setPreferredSize(new Dimension(40, 28));				
				gamePanel.add(board[y][x]);				
			}
		}	
		
		gamePanel.setEnabled(true);
		this.repaint();
		this.validate();
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		MWModel model = (MWModel) arg1;
		setRows(model.getRows());
		setCols(model.getCols());		
	}

	/**
	 * @param lZeit the lZeit to set
	 */
	public void setlZeit(JLabel lZeit) {
		this.lZeit = lZeit;
	}

	/**
	 * @return the lZeit
	 */
	public JLabel getlZeit() {
		return lZeit;
	}

}

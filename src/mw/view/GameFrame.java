package mw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import mw.model.BoardModel;

public class GameFrame extends JFrame implements Observer {
	
	//private MWModel m_model;
	
	private JLabel lZeit;	
	private JLabel lRestMinen;
	
	private JProgressBar progressBar;
	
	private JPanel contentPanel;
	private JPanel mainPanel;
	private JPanel gamePanel;
	
	private FeldButton[][] board;
	
	private int rows = 8;
	private int cols = 8;
	
	private BoardPanel boardPanel;
    private BoardModel boardModel;

	public GameFrame(BoardPanel boardPanel, BoardModel boardModel) {
		this.boardPanel = boardPanel;
        this.boardModel = boardModel;        
        
        this.setLayout(new BorderLayout());
        
        setFrameLocation();
        
        this.setTitle("MinesWeeper v3");
        this.setVisible(true);
        this.setResizable(false);
        
        build();
        
        pack();
	}
	
	
	private void setFrameLocation() {
		/* Center the frame */
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frameDim = getBounds();
		setLocation((screenDim.width - frameDim.width) / 2,	(screenDim.height - frameDim.height) / 2);
		
	}


	private void build(){
		
		mainPanel = new JPanel();		
		gamePanel = new JPanel();			
		lZeit = new JLabel();
		
		
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

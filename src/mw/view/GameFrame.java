package mw.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import mw.backstage.GameLogic;
import mw.model.BoardModel;
import mw.model.Difficulty;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Observer {

	private JLabel lZeit;
	private JLabel lRestMinen;
	public int restMinen;

//	private JProgressBar progressBar;

	private JPanel mainPanel;

	private IBoardPanel boardPanel;
	// private GameLogic gameLogic;
	private BoardModel boardModel;

	private JMenuItem mnuSpielNeu, mnuSpielDiffUser, mnuSpielBeenden, mnuExtraHelp, mnuHelpInfo,mnuHighScore;

	private ButtonGroup diffGroup;
	private JRadioButtonMenuItem mnuSpeilDiffEasy;
	private JRadioButtonMenuItem mnuSpielDiffMedium;
	private JRadioButtonMenuItem mnuSpielDiffHard;
	
	public String wintitle,authors,version;

	public GameFrame(BoardPanel boardPanel, BoardModel boardModel) {
		this.boardPanel = boardPanel;
		this.boardModel = boardModel;

		this.setLayout(new BorderLayout());

		setFrameLocation();
	}
	
	public void gameFrameInitialize()
  {
    this.setTitle(wintitle);
    this.setVisible(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
  }
	
	public void setAuthors(String authors) {
    this.authors = authors;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setWinTitle (String wintitle) {
	  this.wintitle = wintitle;
	}

	public void setFrameLocation() {
		/* Center the frame */
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frameDim = getBounds();
		setLocation((screenDim.width - frameDim.width) / 2, (screenDim.height - frameDim.height) / 2);

	}

	public void build() {

		// Hier befindet sich die Zeit Info und die Anzahl der RestMinen
		JPanel infoPanel = new JPanel();

		JLabel lTime = new JLabel("Zeit:");
		lZeit = new JLabel("0");

		JLabel lMines = new JLabel("Minen");
		lRestMinen = new JLabel(getRestMinen());

		infoPanel.add(lTime);
		infoPanel.add(lZeit);
		infoPanel.add(lMines);
		infoPanel.add(lRestMinen);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add((Component) boardPanel);
		this.add(getJMenuBar(), BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
//		this.add(getJProgressBar(), BorderLayout.SOUTH);
	}
	/**
	 * Diese Methode erstellt die JMenuBar
	 * 
	 * @return JMenuBar
	 */
	public JMenuBar getJMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu mSpiel, mExtra, mHelp;

		// Datei
		mSpiel = new JMenu("Spiel");
		mSpiel.setMnemonic(KeyEvent.VK_D);
		mnuSpielNeu = new JMenuItem("Neues Spiel");

		mnuSpielDiffUser = new JMenuItem("Benutzerdefiniert");
		mnuSpielDiffUser.setMnemonic(KeyEvent.VK_E);

		mnuSpielBeenden = new JMenuItem("Beenden");
		mnuSpielBeenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		
		mnuHighScore = new JMenuItem("HighScore");

		mSpiel.add(mnuSpielNeu);

		mSpiel.addSeparator();

		// diff menu
		diffGroup = new ButtonGroup();

		mnuSpeilDiffEasy = new JRadioButtonMenuItem("Einfach");
		mnuSpielDiffMedium = new JRadioButtonMenuItem("Mittel");
		mnuSpielDiffHard = new JRadioButtonMenuItem("Schwer");

		diffGroup.add(mnuSpeilDiffEasy);
		diffGroup.add(mnuSpielDiffMedium);
		diffGroup.add(mnuSpielDiffHard);

		// Wahl der Schwierigkeit durch Radio
		Difficulty diff = boardModel.getDifficulty();

		if (diff == Difficulty.EINFACH)
			mnuSpeilDiffEasy.setSelected(true);
		else if (diff == Difficulty.MITTEL)
			mnuSpielDiffMedium.setSelected(true);
		else if (diff == Difficulty.SCHWER)
			mnuSpielDiffHard.setSelected(true);

		mSpiel.add(mnuSpeilDiffEasy);
		mSpiel.add(mnuSpielDiffMedium);
		mSpiel.add(mnuSpielDiffHard);

		mSpiel.add(mnuSpielDiffUser);

		mSpiel.addSeparator();
		mSpiel.add(mnuHighScore);
		mSpiel.addSeparator();
		mSpiel.add(mnuSpielBeenden);

		mb.add(mSpiel);

		// Extras
		mExtra = new JMenu("Extras");

		mnuExtraHelp = new JMenuItem("Hilfezug");
		mExtra.add(mnuExtraHelp);
		// mb.add(mExtra);

		// Hilfe
		mHelp = new JMenu("?");
		mnuHelpInfo = new JMenuItem("Info");
		mHelp.add(mnuHelpInfo);
		mb.add(mHelp);

		return mb;
	}

	/**
	 * This method initializes jProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
//	public JProgressBar getJProgressBar() {
//		if (progressBar == null) {
//			progressBar = new JProgressBar();
//			progressBar.setMaximum(boardPanel.getCols() * boardPanel.getRows());
//			progressBar.setStringPainted(true);
//		}
//
//		return progressBar;
//	}

	public void reset() {
	  lRestMinen.setText(getRestMinen());
		resetTimePlayed();
//		resetProgressBar();
	}

	private void resetTimePlayed() {
		lZeit.setText("0");
	}

//	private void resetProgressBar() {
//		progressBar.setValue(0);
//		progressBar.setMaximum(boardPanel.getCols() * boardPanel.getRows());
//	}

	/**
	 * 
	 * @param e
	 *            MouseListener
	 */
	public void addClickListener(MouseListener e) {
		mnuSpielNeu.addMouseListener(e);
		mnuHelpInfo.addMouseListener(e);
		mnuSpielBeenden.addMouseListener(e);
		mnuHighScore.addMouseListener(e);
	}

	/**
	 * 
	 * @param diffChoiceListener
	 */
	public void addDifficultyListener(ActionListener diffChoiceListener) {
		mnuSpeilDiffEasy.addActionListener(diffChoiceListener);
		mnuSpielDiffMedium.addActionListener(diffChoiceListener);
		mnuSpielDiffHard.addActionListener(diffChoiceListener);
		mnuSpielDiffUser.addActionListener(diffChoiceListener);
	}

	public JMenuItem getMnuSpielNeu() {
		return mnuSpielNeu;
	}
	
	public JMenuItem getMnuSpielBeenden() {
	  return mnuSpielBeenden;
	}
	
	public JMenuItem getMnuHighScore() {
	  return mnuHighScore;
	}

	public JMenuItem getMnuHelpInfo() {
		return mnuHelpInfo;
	}

	public void showAbout() {
		JOptionPane.showMessageDialog(null, authors, version, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getRestMinen(){
	  return restMinen + "";
	}
	
	public void setRestMinen (int restMinen){
	  this.restMinen = restMinen;
	}

	@Override
	public void update(Observable obs, Object obj) {
		GameLogic gameLogic = (GameLogic) obj;
//		progressBar.setValue(gameLogic.coutFeldZaehler());
		lZeit.setText(String.valueOf(gameLogic.getTimePlayed()));
		lRestMinen.setText(String.valueOf(gameLogic.getRestMinen()));

	}

}

package mw.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HighScoreFrame extends JFrame {

	// Frame muss noch fertiggebaut werden!

	public String score;
	public JLabel[] highScore;

	public HighScoreFrame(int height, int width) {
		this.setSize(width, height);
	}

	public void initialize() {
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HighScoreFrame.EXIT_ON_CLOSE);
		this.setTitle("HighScore");
	}

	public void setFrameLocation() {
		/* Center the frame */
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frameDim = getBounds();
		setLocation((screenDim.width - frameDim.width) / 2, (screenDim.height - frameDim.height) / 2);
	}

	public void showHighScore() {

	}

	public void addNewHighScoreToList() {

	}

}

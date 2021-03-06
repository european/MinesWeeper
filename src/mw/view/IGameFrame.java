package mw.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;

public interface IGameFrame {
	
	public void setFrameLocation();

	public void addClickListener(MouseListener e);

	public void addDifficultyListener(ActionListener diffChoiceListener);

	public void showAbout();

	public void reset();

	public void repaint();

	public void validate();

	public void pack();

	public JMenuItem getMnuSpielNeu();

	public JMenuItem getMnuHelpInfo();

	public void build();
}

package mw.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScoreFrame extends JFrame {

  //Frame muss noch fertiggebaut werden!

  public String score;

  public JLabel[] highScore = new JLabel[10];

  public Container content;

  public JPanel panel = new JPanel();

  public GridLayout layout = new GridLayout();

  public int index;

  public HighScoreFrame(int height, int width) {
    this.setSize(width, height);
  }

  public void getHighScoreFromFile() {
//    BufferedReader reader;
//
//    try {
//      reader = new BufferedReader(new FileReader("highScore.txt"));
//      String zeile;
//      int index = 1;
//   //   ArrayList<String> values = new ArrayList<String>();
//      while ((zeile = reader.readLine()) != null) {
//        /*
//         * while (zeile != null) { values.add(zeile.split(";").toString());
//         * System.out.println(zeile); }
//         */
//   //     System.out.println(values.size());
//        System.out.println(zeile);
//        addHighScoreToPanel(index);
//        index = index + 1;
//      }
//
//    } catch (IOException e) {
//      System.err.println("Fehler beim Laden des High Scores.");
//    }
  }

  public void initialize() {
    setFrameLocation();
    this.setVisible(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(HighScoreFrame.HIDE_ON_CLOSE);
    this.setTitle("HighScore");
    this.add(panel);
    configLayOut();
    getHighScoreFromFile();
  }

  public void configLayOut() {
    layout.setRows(10);
    layout.setColumns(1);
    panel.setLayout(layout);
  }

  public void setFrameLocation() {
    /* Center the frame */
    Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle frameDim = getBounds();
    setLocation((screenDim.width - frameDim.width) / 2, (screenDim.height - frameDim.height) / 2);
  }

  public void addHighScoreToPanel(int index) {
    highScore[index] = new JLabel(score + " Punkte");
    panel.add(highScore[index]);
  }

}

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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

public class HighScoreFrame extends JFrame {

  //Frame muss noch fertiggebaut werden!

  public String score;

  public JLabel[] highScore = new JLabel[20];

  public Container content;

  public JPanel panel = new JPanel();

  public GridLayout layout = new GridLayout();

  public int test;
  
  private String zeile;

  public HighScoreFrame(int height, int width) {
    this.setSize(width, height);
    initialize();
  }
  
  //Die highScore.txt wird eingeladen und verarbeitet.

  public void getHighScoreFromFile() {
    BufferedReader reader;

    try {
      reader = new BufferedReader(new FileReader("highScore.txt"));
      int index = 0;
      String[] values;
      while ((zeile = reader.readLine()) != null) {
        values = zeile.split(";");
        System.out.println("Datei Erfolgreich geladen: " + zeile);
        stringArrayParser(24,values);
        index = index + 1;
      }

    } catch (IOException e) {
      System.err.println("Fehler beim Laden des High Scores.");
    }
  }
  
  //Die Initialisierungsmethode des HighScoreFrames.
  
  public void initialize() {
    setFrameLocation();
    this.setVisible(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(HighScoreFrame.DISPOSE_ON_CLOSE);
    this.setTitle("HighScore");
    this.add(panel);
    configLayOut();
    getHighScoreFromFile();
  }

  public void configLayOut() {
    layout.setRows(10);
    layout.setColumns(2);
    panel.setLayout(layout);
  }
  
  //Diese Methode läuft das Array ab und übergibt die einzelnen Zellen des Arrays dem addHighScoreToPanel
  
  private String stringArrayParser(int arrayIndex, String[] stringArray) {
   // int temp = 0;
    String score;
    score = null;
    if (arrayIndex >= 0) {
      try {
    //  try {
   //   temp = Integer.parseInt(stringArray[arrayIndex]);
   //   } catch (NumberFormatException e){}
          score = stringArray[arrayIndex];
          addHighScoreToPanel(arrayIndex,score);
          System.out.println(stringArray[arrayIndex]);
          arrayIndex = arrayIndex - 1;
          stringArrayParser(arrayIndex, stringArray);
      }
        catch (ArrayIndexOutOfBoundsException e){
          arrayIndex = arrayIndex - 1;
          stringArrayParser(arrayIndex,stringArray);
        }
    }    
    return score;
  }

  public void setFrameLocation() {
    /* Center the frame */
    Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle frameDim = getBounds();
    setLocation((screenDim.width - frameDim.width) / 2, (screenDim.height - frameDim.height) / 2);
  }
  
  //Diese Methode fügt die einzelnen HighScores dem Panel zu.

  public void addHighScoreToPanel(int index, String score) {
    highScore[index] = new JLabel(score);
    panel.add(highScore[index]);
  }

}

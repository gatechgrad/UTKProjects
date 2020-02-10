import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionPanel extends JPanel implements ActionListener {
//  public static final String BUT_GAME_BOARD_EDITOR = "Game Board Editor";

  sokoban theSokoban;
  
  JRadioButton jrbSmall;
  JRadioButton jrbMedium;
  JRadioButton jrbLarge;

  public OptionPanel(sokoban s) {
    theSokoban = s; 
    setupPanel();
  }


  private void setupPanel() {
    ButtonGroup bg;
    JPanel pnlSize;
//    JPanel pnlGameBoardEditor;
    JButton butGameBoardEditor;
   

    pnlSize = new JPanel();
    pnlSize.setLayout(new BoxLayout(pnlSize, BoxLayout.Y_AXIS));

    bg = new ButtonGroup();

    jrbSmall = new JRadioButton("Small", false);
    bg.add(jrbSmall);
    jrbSmall.addActionListener(this);
    pnlSize.add(jrbSmall);

    jrbMedium = new JRadioButton("Medium", true);
    bg.add(jrbMedium);
    jrbMedium.addActionListener(this);
    pnlSize.add(jrbMedium);

    jrbLarge = new JRadioButton("Large", false);
    bg.add(jrbLarge);
    jrbLarge.addActionListener(this);
    pnlSize.add(jrbLarge);

/*

    pnlGameBoardEditor = new JPanel();
    pnlGameBoardEditor.setLayout(new BoxLayout(pnlGameBoardEditor, BoxLayout.Y_AXIS));

    butGameBoardEditor = new JButton(BUT_GAME_BOARD_EDITOR);
    butGameBoardEditor.addActionListener(theSokoban.getControl());
    pnlGameBoardEditor.add(butGameBoardEditor);
*/    

    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(pnlSize);
    this.add(Box.createHorizontalStrut(32));
//    this.add(pnlGameBoardEditor);

  }
 

  public void actionPerformed(ActionEvent e) {
    if (jrbSmall.isSelected()) {
      theSokoban.setViewsSmallSize();
    } else if (jrbMedium.isSelected()) {
      theSokoban.setViewsMediumSize();
    } else if (jrbLarge.isSelected()) {
      theSokoban.setViewsLargeSize();
    }
     
    theSokoban.setGameFocus();  //move the key focus back to the game 
  }
}

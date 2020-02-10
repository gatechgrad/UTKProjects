/**
 * OptionPanel.java -  holds game option components 
 *                     currently lets the user select the size
 *                     of the tiles
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionPanel extends JPanel implements ActionListener {
//  public static final String BUT_GAME_BOARD_EDITOR = "Game Board Editor";

  /*** INSTANCE VARIABLES ***/
  sokoban theSokoban;
  
  JRadioButton jrbSmall;
  JRadioButton jrbMedium;
  JRadioButton jrbLarge;

  /**
   * OptionPanel - constructor
   */
  public OptionPanel(sokoban s) {
    theSokoban = s; 
    setupPanel();
  }


  /**
   * setupPanel - sets up the components in the panel
   */
  private void setupPanel() {
    ButtonGroup bg;
    JPanel pnlSize;
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


    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(pnlSize);
    this.add(Box.createHorizontalStrut(32));

  }
 

  /**
   * actionPerformed - handles button presses
   */
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

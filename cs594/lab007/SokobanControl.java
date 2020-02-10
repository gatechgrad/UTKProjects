/**
 * SokobanControl.java - implements controls for the game 
 *                       ("the Control") 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;

public class SokobanControl implements KeyListener, ActionListener {

  /*** INSTANCE VARIABLES ***/
  private sokoban theSB;

  /**
   * SokobanControl - the constructor
   */
  public SokobanControl(sokoban sb) {
    theSB = sb;
  }

  /**
   * keyPressed, keyReleased, keyTyped
   * actionPerformed                   - event handlers
   */
  public void keyPressed(KeyEvent e) {
    //System.out.println("key pressed");

/*
    if ((e.isControlDown()) && (!e.isAltDown()) && (!e.isMetaDown())) {
      if (e.getKeyCode() == KeyEvent.VK_A) {
      } else if (e.getKeyCode() == KeyEvent.VK_E) {
      }
    }
 
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      theSB.getBoardData().getPlayer().moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      theSB.getBoardData().getPlayer().moveRight();
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      theSB.getBoardData().getPlayer().moveUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      theSB.getBoardData().getPlayer().moveDown();

    } else if (e.getKeyCode() == KeyEvent.VK_H) {
      theSB.getBoardData().getPlayer().moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_J) {
      theSB.getBoardData().getPlayer().moveDown();
    } else if (e.getKeyCode() == KeyEvent.VK_K) {
      theSB.getBoardData().getPlayer().moveUp();
    } else if (e.getKeyCode() == KeyEvent.VK_L) {
      theSB.getBoardData().getPlayer().moveRight();
    }
*/ 
  }
 
  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) { }

  public void actionPerformed(ActionEvent e) {
    String strCommand;
                                                                                
    strCommand = e.getActionCommand();
                                                                                
    if (strCommand.equals(sokoban.MI_LOAD)) {
      //System.out.println("*** LOAD GAME ***");
      theSB.loadBoard();
    } else if (strCommand.equals(sokoban.MI_RESET)) {
      //System.out.println("*** RESET GAME ***");
      theSB.resetGame();
    } else if (strCommand.equals(sokoban.MI_QUIT)) {
      theSB.quitGame();
    } else if (strCommand.equals(sokoban.MI_COLOR_WALL)) {
      theSB.setViewsWallColor();
    } else if (strCommand.equals(sokoban.MI_COLOR_GOAL)) {
      theSB.setViewsGoalColor();
    } else if (strCommand.equals(sokoban.MI_COLOR_BLANK)) {
      theSB.setViewsBlankColor();
    } else if (strCommand.equals(sokoban.MI_EDITOR)) {
      new GameBoardEditor(theSB);
      //theSB.setGameFocus();
    }
  }


}

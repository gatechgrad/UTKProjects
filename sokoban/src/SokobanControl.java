import java.awt.*;
import java.awt.event.*;

public class SokobanControl implements KeyListener, ActionListener {

  sokoban theSB;

  public SokobanControl(sokoban sb) {
    theSB = sb;
  }

  public void keyPressed(KeyEvent e) {
    System.out.println("key pressed");

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
 
  }
 
  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) { }

  public void actionPerformed(ActionEvent e) {
    String strCommand;
                                                                                
    strCommand = e.getActionCommand();
                                                                                
    if (strCommand.equals(sokoban.MI_LOAD)) {
      System.out.println("*** LOAD GAME ***");
      theSB.loadBoard();
    } else if (strCommand.equals(sokoban.MI_RESET)) {
      System.out.println("*** RESET GAME ***");
      theSB.resetGame();
    } else if (strCommand.equals(sokoban.MI_QUIT)) {
      theSB.quitGame();
    }
  }


}

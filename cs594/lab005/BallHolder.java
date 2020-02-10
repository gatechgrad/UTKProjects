/**
 * BallHolder.java - a ball holder (goal) area 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import javax.swing.*;

public class BallHolder {

  /*** CONSTANTS ***/
/*
  public static final Color HOLDER_COLOR = new Color(64, 128, 128);
*/

  /*** INSTANCE VARIABLES ***/
  private int iPosX;
  private int iPosY;


  /**
   * BallHolder - constructor
   */
  public BallHolder() {

  }

  /**
   * setLocation - sets the location of the ball holder
   */
  public void setLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
  }

  /**
   * getPosX - returns the  ball holder's X position
   */
  public int getPosX() {
    return iPosX;
  }

  /**
   * getPosY - returns the ball holder's Y position
   */
  public int getPosY() {
    return iPosY;
  }

  /**
   * drawBallHolder - tells the ball holder to draw itself
   */
  public void drawBallHolder(Graphics g, int iOffsetX, int iOffsetY,
                             BoardView bv) {
    g.setColor(bv.getGoalColor());
    g.fillRect((iPosX * bv.getTileSize().width) + iOffsetX,
               (iPosY * bv.getTileSize().height) + iOffsetY,
               bv.getTileSize().width,
               bv.getTileSize().height
              );

  }

  /**
   * drawTextBallHolder - tells the ball holder to draw itself
   */
  public void drawTextBallHolder(Graphics g, int iOffsetX, int iOffsetY,
                             TextBoardView tbv) {
      g.setColor(Color.black);
      g.drawString(".", ((iPosX + 0) * tbv.getTileSize().width) + iOffsetX,
                        ((iPosY + 1) * tbv.getTileSize().height) + iOffsetY);

  }
}

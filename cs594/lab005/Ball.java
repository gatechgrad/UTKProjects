/**
 * Ball.java - a ball object 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import javax.swing.*;

public class Ball {

  /*** INSTANCE VARIABLES ***/
  int iPosX;
  int iPosY;

  /**
   * Ball - constructor
   */
  public Ball() {
    iPosX = 0;
    iPosY = 0; 
  }

  /**
   * setLocation - sets the location of the ball
   */
  public void setLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
  }

  /**
   * getPosX - returns the ball's X position
   */
  public int getPosX() {
    return iPosX;
  }

  /**
   * getPosY - returns the ball's Y position
   */
  public int getPosY() {
    return iPosY;
  }

  /**
   * drawBall - tells the ball to draw itself
   */
  public void drawBall(Graphics g, Image img, int iOffsetX, int iOffsetY,
                       BoardView bv) {
    g.setColor(Color.green);
    g.fillRect((iPosX * bv.getTileSize().width) + iOffsetX,
               (iPosY * bv.getTileSize().height) + iOffsetY,
               bv.getTileSize().width,
               bv.getTileSize().height
              );
    if (img != null) {
      g.drawImage(img,
                  (iPosX * bv.getTileSize().width) + iOffsetX,
                   (iPosY * bv.getTileSize().height) + iOffsetY,
                   bv.getTileSize().width,
                   bv.getTileSize().height,
                   null
                 ); 

    }
  }

  /**
   * drawTextBall - tells the ball to draw itself
   */
  public void drawTextBall(Graphics g, int iOffsetX, int iOffsetY,
                       TextBoardView tbv) {

      g.setColor(Color.black);
      g.drawString("$", ((iPosX + 0) * tbv.getTileSize().width) + iOffsetX,
                        ((iPosY + 1) * tbv.getTileSize().height) + iOffsetY);

  }
}

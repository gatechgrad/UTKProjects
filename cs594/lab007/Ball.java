/**
 * Ball.java - a ball object 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import javax.swing.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;


public class Ball {

  private ImageShape theShape;
  private TextShape theTextShape;
  sokoban theSB;

  /*** INSTANCE VARIABLES ***/
  int iPosX;
  int iPosY;

  /**
   * Ball - constructor
   */
  public Ball(sokoban s) {
    iPosX = 0;
    iPosY = 0; 
    theSB = s;
    theShape = new ImageShape(0, 0, "ball.gif");
    theTextShape = new TextShape(0, 0, "$");
  }

  public ImageShape getShape() {
    updateShape();
    return theShape;
  }

  public TextShape getTextShape() {
    updateShape();
    return theTextShape;
  }

  private void updateShape() {
    if (theSB.getBoardData() != null) {
      theShape.setLeft(theSB.getBoardData().getTile(iPosX, iPosY).getShape().getLeft());
      theShape.setTop(theSB.getBoardData().getTile(iPosX, iPosY).getShape().getTop());

      theTextShape.setLeft(iPosX * 12);
      theTextShape.setTop(iPosY * 12);


    }



  }

  /**
   * setLocation - sets the location of the ball
   */
  public void setLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
    updateShape();
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

}

/**
 * BallHolder.java - a ball holder (goal) area 
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


public class BallHolder {

  /*** CONSTANTS ***/
/*
  public static final Color HOLDER_COLOR = new Color(64, 128, 128);
*/

  /*** INSTANCE VARIABLES ***/
  private int iPosX;
  private int iPosY;

  private RectangleShape theShape;
  private TextShape theTextShape;
  private sokoban theSB;

  /**
   * BallHolder - constructor
   */
  public BallHolder(sokoban s) {
    theSB = s;
    theShape = new RectangleShape();
    theTextShape  = new TextShape(0, 0, ".");
  }

  private void updateShape() {
     if (theSB.getBoardData() != null) {
      theShape.setLeft(theSB.getBoardData().getTile(iPosX, iPosY).getShape().getLeft());
      theShape.setTop(theSB.getBoardData().getTile(iPosX, iPosY).getShape().getTop());

      theTextShape.setLeft(iPosX * 12);
      theTextShape.setTop(iPosY * 12);
     }
  }
  public void setGoalColor(Color c) {
    theShape.setFillColor(c);
    theShape.setLineColor(c);
  }

  public RectangleShape getShape() {
    updateShape();
    return theShape;
  }

  public TextShape getTextShape() {
    updateShape();
    return theTextShape;
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

}

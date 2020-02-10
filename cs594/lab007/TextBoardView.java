/**
 * TextBoardView.java - textual view of the game board 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import silhouette.shapes.*;
import silhouette.constraints.*;

public class TextBoardView extends CanvasShape implements ViewInterface {

  /*** CONSTANTS ***/
  public static final Font SMALL_FONT = new Font("Courier New", Font.PLAIN, 8);
  public static final Font MEDIUM_FONT = new Font("Courier New", Font.PLAIN, 12);
  public static final Font LARGE_FONT = new Font("Courier New", Font.PLAIN, 16);

  /*** INSTANCE VARIABLES ***/
  private Font theFont;
  private BoardData theModel;
  private Dimension dimTileSize = new Dimension(12, 12);

  /**
   * TextBoardView - constructor
   */
  public TextBoardView(BoardData bd) {
    theModel = bd;
    setPreferredSize(new Dimension(300, 128));
  }


  /**
   * getTileSize - returns the tile size
   */
  public Dimension getTileSize() {
    return (new Dimension(16, 16));
  }
  

  /**
   * getViewFont - returns the font used
   */
  public Font getViewFont() {
    return theFont;
  }

  /**
   * set*Color, get*Color - get and set methods for colors
   *                        I decided to leave the colors as
   *                        black and white
   */
  public void setGoalColor(Color c) {

  }
  public void setWallColor(Color c) {

  }

  public void setBlankColor(Color c) {

  }

  public Color getGoalColor() {
    return Color.black;
  }
  public Color getWallColor() {
    return Color.black; 
  }

  public Color getBlankColor() {
    return Color.black;
  }

  /**
   * setView*Size - sets the size of the font (not currently used)
   */
  public void setViewSmallSize() {
    theFont = SMALL_FONT;
  }

  public void setViewMediumSize() {
    theFont = MEDIUM_FONT;
  }

  public void setViewLargeSize() {
    theFont = LARGE_FONT;
  }

  /**
   * cantMove, showWin - required by ViewInterface (not currently 
   *                     used)
   */
  public void cantMove() {
    //No graphical representation needed
  }

  public void showWin() {
    //No graphical representation needed
  }

  /**
   * updateModel - sets a new model for the view
   */
  public void updateModel(BoardData b) {
    theModel = b;
    int i, j;
    theModel = b;

    RectangleShape rs = new RectangleShape(0, 0, theModel.getMaxX() * dimTileSize.width, theModel.getMaxY() * dimTileSize.height); 
    rs.setFillColor(Color.white);
    this.add(rs);
                                                                                
    for (i = 0; i < theModel.getMaxX(); i++) {
      for (j = 0; j < theModel.getMaxY(); j++) {
        theModel.getTile(i, j).addRectangleTextTile(this,
                                (int) (i * dimTileSize.width),
                                (int) (j * dimTileSize.height),
                                dimTileSize.width,
                                dimTileSize.height
                                );
      }
    }
                                                                                
    for (i = 0; i < theModel.getBallHolders().size(); i++) {
      this.add( ((BallHolder) theModel.getBallHolders().elementAt(i)).getTextShape());
    }

    for (i = 0; i < theModel.getBalls().size(); i++) {
      this.add( ((Ball) theModel.getBalls().elementAt(i)).getTextShape());
    }

    this.add(theModel.getPlayer().getTextShape());

  }

}

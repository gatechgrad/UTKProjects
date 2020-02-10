/**
 * ViewInterface.java - methods that all views for the game must
 *                      implement 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;

public interface ViewInterface {

  public abstract void showWin();  //what to do display for a win
  public abstract void cantMove(); //what to do for an invalid move

  public abstract void setViewSmallSize(); 
  public abstract void setViewMediumSize(); 
  public abstract void setViewLargeSize(); 

  public abstract void setWallColor(Color c);
  public abstract void setBlankColor(Color c);
  public abstract void setGoalColor(Color c);

  public abstract Color getWallColor();
  public abstract Color getBlankColor();
  public abstract Color getGoalColor();

  public abstract Dimension getTileSize();
  public void updateModel(BoardData bd);

}

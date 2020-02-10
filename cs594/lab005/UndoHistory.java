/**
 * UndoHistory.java - holds "undo" information 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
public class UndoHistory {

  /*** INSTANCE VARIABLES ***/
  private int iDirectionX;
  private int iDirectionY;
  private int iTurns;

  private Ball ballPushed;

  /**
   * UndoHistory - constructors
   */
  public UndoHistory(int x, int y, int i) {
    iDirectionX = x;
    iDirectionY = y;
    ballPushed = null;
    iTurns = i;
  }

  public UndoHistory(int x, int y, Ball b, int i) {
    iDirectionX = x;
    iDirectionY = y;
    ballPushed = b;
    iTurns = i;
  }


  /**
   * get - accessor methods
   */
  public Ball getBallPushed() {
    return ballPushed;
  }

  public int getDirectionX() {
    return iDirectionX;
  }

  public int getDirectionY() {
    return iDirectionY;
  }

  public int getTurns() {
    return iTurns;
  }
}

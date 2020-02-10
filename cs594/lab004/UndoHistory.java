public class UndoHistory {

  int iDirectionX;
  int iDirectionY;
  int iTurns;

  Ball ballPushed;

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

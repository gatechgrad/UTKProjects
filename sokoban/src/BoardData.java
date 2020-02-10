import java.util.Vector;

public class BoardData {

  sokoban theSB;

  Tile theBoard[][];
  Player thePlayer;
  Vector vectBalls;
  Vector vectBallHolders;

  int iMaxX;
  int iMaxY;

  int iMoves;
  int iPushes;

  public BoardData(sokoban sb) {
    theSB = sb;
    iMoves = 0;
    iPushes = 0;
  }

  public void setBoardSize(int x, int y) {
    int i, j;

    iMaxX = x;
    iMaxY = y;

    theBoard = new Tile[iMaxY][iMaxX];
    vectBalls = new Vector();
    vectBallHolders = new Vector();
    thePlayer = new Player(theSB);

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {
        theBoard[i][j] = new Tile();
      }
    }
  
  }

  public Player getPlayer() {
    return thePlayer;
  }

  public Tile getTile(int x, int y) {
    return theBoard[y][x];
  }

  public Ball getBallAt(int x, int y) {
    int i;
    Ball ballReturn;

    ballReturn = null;

    for (i = 0; i < vectBalls.size(); i++) {
      if (  ( ((Ball) vectBalls.elementAt(i)).getPosX() == x ) &&
            ( ((Ball) vectBalls.elementAt(i)).getPosY() == y )
         ) {
        ballReturn = (Ball) vectBalls.elementAt(i);
      }
    }

    return ballReturn;
  }

  public void checkWin() {
    int i;
    boolean hasWon;

    hasWon = true;
    for (i = 0; i < vectBallHolders.size(); i++) {
      if ( getBallAt( ((BallHolder) vectBallHolders.elementAt(i)).getPosX(),
                      ((BallHolder) vectBallHolders.elementAt(i)).getPosY()
                    ) == null
         ) {

        hasWon = false;

      } 
    }
 
    if (hasWon) {
      theSB.showWin();
    }
  }

  public int getMaxX() {
    return iMaxX;
  }

  public int getMaxY() {
    return iMaxY;
  }

  public Vector getBalls() {
    return vectBalls;
  }
 
  public Vector getBallHolders() {
    return vectBallHolders;
  }

  public void incrementMove() {
    iMoves++;
  }

  public void decrementMove() {
    iMoves--;
  }

  public void incrementPush() {
    iPushes++;
  }

  public void decrementPush() {
    iPushes--;
  }

  public int getMoves() {
    return iMoves;
  }

  public int getPushes() {
    return iPushes;
  }
}

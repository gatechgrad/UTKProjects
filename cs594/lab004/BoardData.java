import java.util.Vector;

public class BoardData {

  sokoban theSB;

  Tile theBoard[][];
  Player thePlayer;
  Vector vectBalls;
  Vector vectBallHolders;
  Vector vectUndoHistory;

  int iMaxX;
  int iMaxY;

  int iMoves;
  int iPushes;


  private boolean searchArray[][];
  private int iSearchTurns;

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
    vectUndoHistory = new Vector();

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {
        theBoard[i][j] = new Tile(this);
      }
    }
  
  }

  public Player getPlayer() {
    return thePlayer;
  }

  public Tile getTile(int x, int y) {
    Tile tReturn;
    tReturn = null;

    if ((x >= 0) &&
        (y >= 0) &&
        (x < iMaxX) &&
        (y < iMaxY)
       ) {
      tReturn = theBoard[y][x];
    }
    return tReturn;
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

  public void addTurns(int i) {
    iMoves += i;
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

  public void undo() {
    UndoHistory uh;
    if (vectUndoHistory.size() > 0) {
      System.out.println("Undo Move");
      uh = (UndoHistory) vectUndoHistory.elementAt(vectUndoHistory.size() - 1);
      thePlayer.setPlayerLocation(thePlayer.getPlayerLocX() + 
                                  uh.getDirectionX(),
                                  thePlayer.getPlayerLocY() +
                                  uh.getDirectionY()
                                 );
      iMoves -= uh.getTurns(); 
      if (uh.getBallPushed() != null) {
        System.out.println("Move back ball");
        uh.getBallPushed().setLocation(uh.getBallPushed().getPosX() + 
                                       uh.getDirectionX(),
                                       uh.getBallPushed().getPosY() + 
                                       uh.getDirectionY()
                                      );
        decrementPush();
      }
      vectUndoHistory.removeElementAt(vectUndoHistory.size() - 1);
      theSB.repaintViews();
    }
  }

  public void addUndoHistory(UndoHistory u) {
    vectUndoHistory.addElement(u);
  }

  public int getTurnsToMove(int x1, int y1, int x2, int y2) {
    int iReturn;
    int i, j;

    iSearchTurns = -1;

    searchArray = new boolean[iMaxY][iMaxX];
    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {
        searchArray[i][j] = false;
        if (!getTile(j, i).getPassable()) {
          searchArray[i][j] = true;
        } 
        if (getBallAt(j, i) != null) {
          searchArray[i][j] = true;
        }
      }
    }

    findTurnsToMove(x1, y1, x2, y2, 0, 0, 0);
    iReturn = iSearchTurns;
//    System.out.println("iReturn: " + iReturn); 


    return iReturn;
  }

  private void findTurnsToMove(int x1, int y1, int x2, int y2, int iFromX, int iFromY, int iTurns) {
    //System.out.println("Trying " + x1 + " " + y1 + " " + x2 + " " + y2);    

    theSB.repaintViews();

    if ( (x1 >= 0) &&
         (y1 >= 0) &&
         (x1 < iMaxX) &&
         (y1 < iMaxY) &&
         (!searchArray[y1][x1])
       ) {
      searchArray[y1][x1] = true;
    
      if ((x1 == x2) &&
          (y1 == y2)) {
        System.out.println("Found tile" + iTurns );
        iSearchTurns = iTurns;
      } else {
        if (!((iFromX == -1) && (iFromY == 0))) {
          findTurnsToMove(x1 + 1, y1, x2, y2, 1, 0, iTurns + 1);
        }

        if (!((iFromX == 1) && (iFromY == 0))) {
          findTurnsToMove(x1 - 1, y1, x2, y2, -1, 0, iTurns + 1);
        }

        if (!((iFromX == 0) && (iFromY == -1))) {
          findTurnsToMove(x1, y1 + 1, x2, y2, 0, 1, iTurns + 1);
        }

        if (!((iFromX == 0) && (iFromY == 1))) {
          findTurnsToMove(x1, y1 - 1, x2, y2, 0, -1, iTurns + 1);
        }
      }
    }

  }

}

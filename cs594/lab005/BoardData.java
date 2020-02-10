/**
 * BoardData.java - holds all of the tiles, player, balls, ball 
 *                  holder objects ("the model")
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.util.Vector;

public class BoardData {

  /*** INSTANCE VARIABLES **/
  private sokoban theSB;

  protected Tile theBoard[][];
  protected Player thePlayer;
  protected Vector vectBalls;
  protected Vector vectBallHolders;
  protected Vector vectUndoHistory;
  protected Vector vectChangedTiles;

  protected int iMaxX;
  protected int iMaxY;

  protected int iMoves;
  protected int iPushes;

  //used for searching a path
  private boolean searchArray[][];
  private int iSearchTurns;

  /**
   * BoardData - constructor
   */
  public BoardData(sokoban sb) {
    theSB = sb;
    iMoves = 0;
    iPushes = 0;
  }

  /**
   * setBoardSize - sets up the board representation
   */
  public void setBoardSize(int x, int y) {
    int i, j;

    iMaxX = x;
    iMaxY = y;

    theBoard = new Tile[iMaxY][iMaxX];
    vectBalls = new Vector();
    vectBallHolders = new Vector();
    thePlayer = new Player(theSB);
    vectUndoHistory = new Vector();
    vectChangedTiles = new Vector();

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {
        theBoard[i][j] = new Tile(this);
      }
    }
  
  }

  public boolean cellIsEmpty(int x, int y) {
    boolean b;

    b = true;

    if ((thePlayer.getPlayerLocX() == x) &&
        (thePlayer.getPlayerLocY() == y)) {
      b = false;
    } else if (getBallAt(x, y) != null) {
      b = false;
    } else if (getBallHolderAt(x, y) != null) {
      b = false;
    }

    return b;
  }

  public void addRow(int iRow, boolean above) {
    int i, j;
    Tile oldBoard[][];
    Ball tmpBall;
    BallHolder tempBallHolder;
  
     System.out.println("Add row");
 
    oldBoard = theBoard;

    iMaxY++;
    theBoard = new Tile[iMaxY][iMaxX];

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {
        if (i < iRow) { 
          theBoard[i][j] = oldBoard[i][j];
        } else if (i == iRow) {
          theBoard[i][j] = new Tile(this);
          theBoard[i][j].setPassable(oldBoard[i][j].getPassable());
        } else {
          theBoard[i][j] = oldBoard[i - 1][j];
        }
      }
    } 

    movePiecesUp(iRow, 1);
  }

  public void deleteRow(int iRow) {
    int i, j;
    Tile oldBoard[][];
    Ball tmpBall;
    BallHolder tempBallHolder;

    //rearrange pieces
    for (i = 0; i < iMaxX; i++) {
      deleteGamePiece(i, iRow); 
    }
    movePiecesUp(iRow, -1);

    //copy the board
    oldBoard = theBoard;

    iMaxY--;
    theBoard = new Tile[iMaxY][iMaxX];

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {

        if (i < iRow) { 
          theBoard[i][j] = oldBoard[i][j];
        } else if (i >= iRow) {
          theBoard[i][j] = oldBoard[i + 1][j];
        }
      }
    } 
  }

  private void movePiecesUp(int iRowAfter, int iRows) {
    int j;
    Ball tmpBall;
    BallHolder tmpBallHolder;

    if (thePlayer.getPlayerLocY() >= iRowAfter) {
        thePlayer.setPlayerLocation(thePlayer.getPlayerLocX(), 
                              thePlayer.getPlayerLocY() + iRows);
    }

    for (j = 0; j < vectBalls.size(); j++) {
      tmpBall = (Ball) vectBalls.elementAt(j);
      if ( tmpBall.getPosY() >= iRowAfter) {
        tmpBall.setLocation(tmpBall.getPosX(), tmpBall.getPosY() + iRows);
      }
    } 

    for (j = 0; j < vectBallHolders.size(); j++) {
      tmpBallHolder = (BallHolder) vectBallHolders.elementAt(j);
      if ( tmpBallHolder.getPosY() >= iRowAfter) {
        tmpBallHolder.setLocation(tmpBallHolder.getPosX(), tmpBallHolder.getPosY() + iRows);
      }
    } 


  }

  private void movePiecesRight(int iColAfter, int iCols) {
    int j;
    Ball tmpBall;
    BallHolder tmpBallHolder;

    if (thePlayer.getPlayerLocX() >= iColAfter) {
        thePlayer.setPlayerLocation(thePlayer.getPlayerLocX() + iCols, 
                              thePlayer.getPlayerLocY());
    }

    for (j = 0; j < vectBalls.size(); j++) {
      tmpBall = (Ball) vectBalls.elementAt(j);
      if ( tmpBall.getPosX() >= iColAfter) {
        tmpBall.setLocation(tmpBall.getPosX() + iCols, tmpBall.getPosY());
      }
    } 

    for (j = 0; j < vectBallHolders.size(); j++) {
      tmpBallHolder = (BallHolder) vectBallHolders.elementAt(j);
      if ( tmpBallHolder.getPosX() >= iColAfter) {
        tmpBallHolder.setLocation(tmpBallHolder.getPosX() + iCols, tmpBallHolder.getPosY());
      }
    } 


  }


  public void addCol(int iCol, boolean above) {
    int i, j;
    Tile oldBoard[][];
    Ball tmpBall;
    BallHolder tempBallHolder;
  
 
    oldBoard = theBoard;

    iMaxX++;
    theBoard = new Tile[iMaxY][iMaxX];

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {

        if (j < iCol) { 
          theBoard[i][j] = oldBoard[i][j];
        } else if (j == iCol) {
          theBoard[i][j] = new Tile(this);
          theBoard[i][j].setPassable(oldBoard[i][j].getPassable());
        } else {
          theBoard[i][j] = oldBoard[i][j - 1];
        }
      }
    } 

    movePiecesRight(iCol, 1);
  }

  public void deleteCol(int iCol) {
    int i, j;
    Tile oldBoard[][];
    Ball tmpBall;
    BallHolder tempBallHolder;

    for (i = 0; i < iMaxY; i++) {
      deleteGamePiece(iCol, i); 
    }
    movePiecesRight(iCol, -1);

    oldBoard = theBoard;

    iMaxX--;
    theBoard = new Tile[iMaxY][iMaxX];

    for (i = 0; i < iMaxY; i++) {
      for (j = 0; j < iMaxX; j++) {

        if (j < iCol) { 
          theBoard[i][j] = oldBoard[i][j];
        } else if (j >= iCol) {
          theBoard[i][j] = oldBoard[i][j + 1];
        }
      }
    } 

  }

  public void deleteGamePiece(int x, int y) {
    int i;
    Ball tmpBall;
    BallHolder tmpBallHolder;
   
    theBoard[y][x].setPassable(true);
   
    if ((thePlayer.getPlayerLocX() == x) &&
        (thePlayer.getPlayerLocY() == y)
       ) {
      thePlayer.setPlayerLocation(-1, -1);
    }

    for (i = 0; i < vectBalls.size(); i++) {
      tmpBall = (Ball) vectBalls.elementAt(i);
      if ((tmpBall.getPosX() == x) &&
          (tmpBall.getPosY() == y)
         ) {
        vectBalls.removeElementAt(i);
      }
    }

    for (i = 0; i < vectBallHolders.size(); i++) {
      tmpBallHolder = (BallHolder) vectBallHolders.elementAt(i);
      if ((tmpBallHolder.getPosX() == x) &&
          (tmpBallHolder.getPosY() == y)
         ) {
        vectBallHolders.removeElementAt(i);
      }
    }

  }

  public Object getPieceAt(int x, int y) {
    Object o;
 
    o = getBallAt(x, y);

    if (o == null) {
      o = getBallHolderAt(x, y);
    }
    
    if (o == null) {
      if ((thePlayer.getPlayerLocX() == x) &&
          (thePlayer.getPlayerLocY() == y)
         ) {
        o = thePlayer; 
      }
    }

    return o;
  }

  public void moveObject(Object o, int x, int y) {
    if (x < 0) {
      x = 0;
    }

    if (y < 0) {
      y = 0;
    }

    if (o instanceof Ball) {
      System.out.println("Dragging a ball");
      ((Ball) o).setLocation(x, y);
    } else if (o instanceof BallHolder) {
      System.out.println("Dragging a ball holder");
      ((BallHolder) o).setLocation(x, y);
    } else if (o instanceof Player) {
      System.out.println("Dragging a player");
      thePlayer.setPlayerLocation(x, y);
    }
  } 

  /**
   * getPlayer - returns the player object
   */
  public Player getPlayer() {
    return thePlayer;
  }

  /**
   * getTile - returns the Tile object at the specified position
   */
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

  /**
   * getBallAt - returns the Ball object at the specified position
   *             or null if there is no ball at the position
   */
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

  public BallHolder getBallHolderAt(int x, int y) {
    int i;
    BallHolder ballholderReturn;

    ballholderReturn = null;

    for (i = 0; i < vectBallHolders.size(); i++) {
      if (  ( ((BallHolder) vectBallHolders.elementAt(i)).getPosX() == x ) &&
            ( ((BallHolder) vectBallHolders.elementAt(i)).getPosY() == y )
         ) {
        ballholderReturn = (BallHolder) vectBallHolders.elementAt(i);
      }
    }

    return ballholderReturn;
  }


  /**
   * checkWin - checks to see if the all of the balls are in ball
   *            holders
   */
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

  /**
   * getMaxX, getMaxY, getBalls, 
   * getMoves, getPushes, getBallHolders - accessor methods
   */
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

  public int getMoves() {
    return iMoves;
  }

  public int getPushes() {
    return iPushes;
  }

  /**
   * incrementMove - adds one move
   */
  public void incrementMove() {
    iMoves++;
  }

  /**
   * addTurns - adds the specified number of turns to the move total
   */
  public void addTurns(int i) {
    iMoves += i;
  }

  /**
   * decrementMove - subtracts one move
   */
  public void decrementMove() {
    iMoves--;
  }

  /**
   * incrementPush - adds one push
   */
  public void incrementPush() {
    iPushes++;
  }

  /**
   * decrementPush - subtracts one push
   */
  public void decrementPush() {
    iPushes--;
  }


  /**
   * undo - undo the user's last move
   */
  public void undo() {
    UndoHistory uh;
    if (vectUndoHistory.size() > 0) {
      //System.out.println("Undo Move");
      uh = (UndoHistory) vectUndoHistory.elementAt(vectUndoHistory.size() - 1);
      addChangedTile(thePlayer.getPlayerLocX(), thePlayer.getPlayerLocY());
      thePlayer.setPlayerLocation(thePlayer.getPlayerLocX() + 
                                  uh.getDirectionX(),
                                  thePlayer.getPlayerLocY() +
                                  uh.getDirectionY()
                                 );
      addChangedTile(thePlayer.getPlayerLocX(), thePlayer.getPlayerLocY());

      iMoves -= uh.getTurns(); 
      if (uh.getBallPushed() != null) {
        //System.out.println("Move back ball");
        addChangedTile(uh.getBallPushed().getPosX(), uh.getBallPushed().getPosY());
        uh.getBallPushed().setLocation(uh.getBallPushed().getPosX() + 
                                       uh.getDirectionX(),
                                       uh.getBallPushed().getPosY() + 
                                       uh.getDirectionY()
                                      );
        addChangedTile(uh.getBallPushed().getPosX(), uh.getBallPushed().getPosY());
        decrementPush();
      }
      vectUndoHistory.removeElementAt(vectUndoHistory.size() - 1);
      theSB.repaintViews();
    }
  }

  /**
   * addUndoHistory - add an action to the user's game history
   */
  public void addUndoHistory(UndoHistory u) {
    vectUndoHistory.addElement(u);
  }

  /**
   * getTurnsToMove - calculate the number of turns required
   *                  to move to the specified location
   */
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

  /**
   * findTurnsToMove - recursively finds the number of turns required
   *                   to move to the specified location
   */
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
        //System.out.println("Found tile" + iTurns );
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

  /**
   * addChangedTile - adds a tile to the changed list (for clipping)
   */
  public void addChangedTile(int x, int y) {
    vectChangedTiles.addElement(new java.awt.Point(x, y));    
  }
  
  /**
   * resetChangedTiles - removes all tiles from the changed list
   *                     (for clipping)
   */
  public void resetChangedTiles() {
    vectChangedTiles = new Vector();
  }

  /**
   * getChangedTiles - returns the list of changed tiles 
   *                   (for clipping)
   */
  public Vector getChangedTiles() {
    return vectChangedTiles;
  }
  
}

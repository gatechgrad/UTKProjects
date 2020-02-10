/**
 * Player.java - Object representing the user's movable piece 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import javax.swing.*;

public class Player {

  /*** INSTANCE VARIABLES ***/
  private sokoban theSB;

  private int iPosX;
  private int iPosY;

  /**
   * Player - constructor
   */
  public Player(sokoban sb) {
    theSB = sb;
    iPosX = -1;
    iPosY = -1; 
  }

  /**
   * setPlayerLocation - sets the Player's location
   */
  public void setPlayerLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
  }

  /**
   * playerDragged - what to do when the player is dragged
   *               - NOTE: the player can only be dragged one
   *                       tile at a time
   */
  public void playerDragged(int x, int y) {
    if ((x == iPosX) && (y == iPosY + 1)) {
      moveDown();
    } else if ((x == iPosX) && (y == iPosY - 1)) { 
      moveUp();
    } else if ((x  == iPosX + 1) && (y == iPosY)) { 
      moveRight();
    } else if ((x  == iPosX - 1) && (y == iPosY)) { 
      moveLeft();
    }

  }


  /**
   * moveUp - asks the player to move up
   */
  public void moveUp() {
    if (theSB.getBoardData().getTile(iPosX, iPosY - 1).getPassable()) {

      if (theSB.getBoardData().getBallAt(iPosX, iPosY - 1) != null) {
        if (theSB.getBoardData().getTile(iPosX, iPosY - 2).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX, iPosY - 2) == null) {
          theSB.getBoardData().getBallAt(iPosX, iPosY - 1).setLocation(iPosX, iPosY - 2);
          theSB.getBoardData().addChangedTile(iPosX, iPosY);
          theSB.getBoardData().addChangedTile(iPosX, iPosY - 1);
          theSB.getBoardData().addChangedTile(iPosX, iPosY - 2);
          iPosY--;
          theSB.getBoardData().incrementMove();
          theSB.getBoardData().incrementPush();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(0, 1, 
              theSB.getBoardData().getBallAt(iPosX, iPosY - 1), 1 )
            );
          theSB.repaintViews();

          theSB.getBoardData().checkWin(); 
        } else {
          theSB.cantMove();
        }
      } else {
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.getBoardData().addChangedTile(iPosX, iPosY - 1);
        iPosY--;
        theSB.getBoardData().incrementMove();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(0, 1, 1) 
            );
        theSB.repaintViews();
      }
    } else {
      theSB.cantMove();
    }
  }

  /**
   * moveDown - asks the player to move down
   */
  public void moveDown() {
    if (theSB.getBoardData().getTile(iPosX, iPosY + 1).getPassable()) {

      if (theSB.getBoardData().getBallAt(iPosX, iPosY + 1) != null) {
        if (theSB.getBoardData().getTile(iPosX, iPosY + 2).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX, iPosY + 2) == null) {
          theSB.getBoardData().getBallAt(iPosX, iPosY + 1).setLocation(iPosX, iPosY + 2);
          theSB.getBoardData().addChangedTile(iPosX, iPosY);
          theSB.getBoardData().addChangedTile(iPosX, iPosY + 1);
          theSB.getBoardData().addChangedTile(iPosX, iPosY + 2);
          iPosY++;
          theSB.getBoardData().incrementMove();
          theSB.getBoardData().incrementPush();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(0, -1,
              theSB.getBoardData().getBallAt(iPosX, iPosY + 1), 1 )
            );

          theSB.repaintViews();
                                                                                
          theSB.getBoardData().checkWin();
        } else {
          theSB.cantMove();
        }
      } else {
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.getBoardData().addChangedTile(iPosX, iPosY + 1);
        iPosY++;
        theSB.getBoardData().incrementMove();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(0, -1, 1)
            );

        theSB.repaintViews();
      }
    } else {
      theSB.cantMove();
    }
  }

  /**
   * moveLeft - asks the player to move left
   */
  public void moveLeft() {
    if (theSB.getBoardData().getTile(iPosX - 1, iPosY).getPassable()) {
      if (theSB.getBoardData().getBallAt(iPosX - 1, iPosY) != null) {
        if (theSB.getBoardData().getTile(iPosX - 2, iPosY).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX - 2, iPosY) == null) {
          theSB.getBoardData().getBallAt(iPosX - 1, iPosY).setLocation(iPosX - 2, iPosY);
          theSB.getBoardData().addChangedTile(iPosX, iPosY);
          theSB.getBoardData().addChangedTile(iPosX - 1, iPosY);
          theSB.getBoardData().addChangedTile(iPosX - 2, iPosY);
          iPosX--;
          theSB.getBoardData().incrementMove();
          theSB.getBoardData().incrementPush();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(1, 0,
              theSB.getBoardData().getBallAt(iPosX - 1, iPosY), 1 )
            );

          theSB.repaintViews();
                                                                                
          theSB.getBoardData().checkWin();
        } else {
          theSB.cantMove();
        }
      } else {
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.getBoardData().addChangedTile(iPosX - 1, iPosY);
        iPosX--;
        theSB.getBoardData().incrementMove();
        theSB.getBoardData().addUndoHistory(
            new UndoHistory(1, 0, 1)
          );
        theSB.repaintViews();
      }
    } else {
      theSB.cantMove();
    }

  }

  /**
   * moveRight - asks the player to move right
   */
  public void moveRight() {
    if (theSB.getBoardData().getTile(iPosX + 1, iPosY).getPassable()) {
      if (theSB.getBoardData().getBallAt(iPosX + 1, iPosY) != null) {
        if (theSB.getBoardData().getTile(iPosX + 2, iPosY).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX + 2, iPosY) == null) {
          theSB.getBoardData().getBallAt(iPosX + 1, iPosY).setLocation(iPosX + 2, iPosY);
          theSB.getBoardData().addChangedTile(iPosX, iPosY);
          theSB.getBoardData().addChangedTile(iPosX + 1, iPosY);
          theSB.getBoardData().addChangedTile(iPosX + 2, iPosY);
          iPosX++;
          theSB.getBoardData().incrementMove();
          theSB.getBoardData().incrementPush();
          theSB.getBoardData().addUndoHistory(
              new UndoHistory(-1, 0,
              theSB.getBoardData().getBallAt(iPosX + 1, iPosY), 1 )
            );

          theSB.repaintViews();
                                                                                
          theSB.getBoardData().checkWin();
        } else {
          theSB.cantMove();
        }
      } else {
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.getBoardData().addChangedTile(iPosX + 1, iPosY);
        iPosX++;
        theSB.getBoardData().incrementMove();
        theSB.getBoardData().addUndoHistory(
            new UndoHistory(-1, 0, 1)
          );

        theSB.repaintViews();
      }
    } else {
      theSB.cantMove();
    }
  }


  /**
   * movePlayer - immediately move the player to the specified
   *              position
   */
  public void movePlayer(int iCellX, int iCellY) {
    if ((iPosX != iCellX) || (iPosY != iCellY)) {

      if (theSB.getBoardData().getTile(iCellX, iCellY).getPassable()) {

        theSB.getBoardData().incrementMove();

        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        iPosX = iCellX;
        iPosY = iCellY;
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.repaintViews();
      }
      
    }
  }

  /**
   * getPlayerLocX, getPlayerLocY - accessor methods
   */
  public int getPlayerLocX() {
    return iPosX;
  }

  public int getPlayerLocY() {
    return iPosY;
  }

  /**
   * drawPlayer - tell the player object to draw itself
   */
  public void drawPlayer(Graphics g, Image img, int iOffsetX, int iOffsetY,
                         BoardView bv) {
   if ((iPosX > -1) && (iPosY > -1)) {
    g.setColor(Color.red);
    g.fillRect((iPosX * bv.getTileSize().width) + iOffsetX, 
               (iPosY * bv.getTileSize().height) + iOffsetY, 
               bv.getTileSize().width, 
               bv.getTileSize().height);
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
  }

  /**
   * drawTextPlayer - tell the player object to draw itself
   */
  public void drawTextPlayer(Graphics g, int iOffsetX, int iOffsetY,
                         TextBoardView tbv) {
   if ((iPosX > -1) && (iPosY > -1)) {

      g.setColor(Color.black);
      g.drawString("@", ((iPosX + 0) * tbv.getTileSize().width) + iOffsetX, 
                        ((iPosY + 1) * tbv.getTileSize().height) + iOffsetY);


   }
  }
}

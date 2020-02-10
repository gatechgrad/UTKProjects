/**
 * Player.java - Object representing the user's movable piece 
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import silhouette.*;
import silhouette.shapes.*;
import silhouette.interactors.*;

public class Player {

  /*** INSTANCE VARIABLES ***/
  private sokoban theSB;

  private int iPosX;
  private int iPosY;

  private ImageShape theShape;
  private TextShape theTextShape;

  /**
   * Player - constructor
   */
  public Player(sokoban sb) {
    theSB = sb;
    iPosX = -1;
    iPosY = -1; 

    theShape = new ImageShape(0, 0, "character.gif");
    theTextShape = new TextShape(0, 0, "@");

    addInteractors();

  }

  private void addInteractors() {

    SilInteractor playerDragged = new SilInteractor(
                                new SilMouseEvent(MouseEvent.MOUSE_PRESSED),
                                new SilMouseEvent(MouseEvent.MOUSE_RELEASED)
                                                ) {
      public void startAction(InputEvent e) {
      }

      public void stopAction(InputEvent e) {
      }

      public void runningAction(InputEvent e) {
        int i, j;

       if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
        MouseEvent event = (MouseEvent) e;

        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
        for (i = 0; i < theSB.getBoardData().getMaxX(); i++) {
          for (j = 0; j < theSB.getBoardData().getMaxY(); j++) {
            if (theSB.getBoardData().getTile(i, j).getShape().contains(event.getX(), event.getY())) {
              if ((j != iPosX) || (i != iPosY)) {
                
                playerDragged(i, j);
              }
            }
          }
        }
        }

       }
      }
    };
    theShape.addInteractor(playerDragged);
    
  }

  /**
   * setPlayerLocation - sets the Player's location
   */
  public void setPlayerLocation(int x, int y) {

    iPosX = x;
    iPosY = y;

    updateShape();


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
          theSB.updateMoves();
          

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
        theSB.updateMoves();
      }
    } else {
      theSB.cantMove();
    }
    updateShape();
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

          theSB.updateMoves();
                                                                                
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

        theSB.updateMoves();
      }
    } else {
      theSB.cantMove();
    }
    updateShape();
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

          theSB.updateMoves();
                                                                                
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
        theSB.updateMoves();
      }
    } else {
      theSB.cantMove();
    }

    updateShape();
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

          theSB.updateMoves();
                                                                                
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

        theSB.updateMoves();
      }
    } else {
      theSB.cantMove();
    }
    updateShape();
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

        setPlayerLocation(iCellX, iCellY);
        theSB.getBoardData().addChangedTile(iPosX, iPosY);
        theSB.updateMoves();
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

  public ImageShape getShape() {
    updateShape();
    return theShape; 
  }

  public TextShape getTextShape() {
    return theTextShape;
  }

}

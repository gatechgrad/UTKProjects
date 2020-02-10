import java.awt.*;
import javax.swing.*;

public class Player {

  sokoban theSB;

  int iPosX;
  int iPosY;

  public Player(sokoban sb) {
    theSB = sb;
    iPosX = 0;
    iPosY = 0; 
  }

  public void setPlayerLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
  }

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

  public void moveUp() {
    if (theSB.getBoardData().getTile(iPosX, iPosY - 1).getPassable()) {

      if (theSB.getBoardData().getBallAt(iPosX, iPosY - 1) != null) {
        if (theSB.getBoardData().getTile(iPosX, iPosY - 2).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX, iPosY - 2) == null) {
          theSB.getBoardData().getBallAt(iPosX, iPosY - 1).setLocation(iPosX, iPosY - 2);
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

  public void moveDown() {
    if (theSB.getBoardData().getTile(iPosX, iPosY + 1).getPassable()) {

      if (theSB.getBoardData().getBallAt(iPosX, iPosY + 1) != null) {
        if (theSB.getBoardData().getTile(iPosX, iPosY + 2).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX, iPosY + 2) == null) {
          theSB.getBoardData().getBallAt(iPosX, iPosY + 1).setLocation(iPosX, iPosY + 2);
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

  public void moveLeft() {
    if (theSB.getBoardData().getTile(iPosX - 1, iPosY).getPassable()) {
      if (theSB.getBoardData().getBallAt(iPosX - 1, iPosY) != null) {
        if (theSB.getBoardData().getTile(iPosX - 2, iPosY).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX - 2, iPosY) == null) {
          theSB.getBoardData().getBallAt(iPosX - 1, iPosY).setLocation(iPosX - 2, iPosY);
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

  public void moveRight() {
    if (theSB.getBoardData().getTile(iPosX + 1, iPosY).getPassable()) {
      if (theSB.getBoardData().getBallAt(iPosX + 1, iPosY) != null) {
        if (theSB.getBoardData().getTile(iPosX + 2, iPosY).getPassable() &&
            theSB.getBoardData().getBallAt(iPosX + 2, iPosY) == null) {
          theSB.getBoardData().getBallAt(iPosX + 1, iPosY).setLocation(iPosX + 2, iPosY);
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


  public void movePlayer(int iCellX, int iCellY) {
    if ((iPosX != iCellX) || (iPosY != iCellY)) {

      if (theSB.getBoardData().getTile(iCellX, iCellY).getPassable()) {

        theSB.getBoardData().incrementMove();

        iPosX = iCellX;
        iPosY = iCellY;
        theSB.repaintViews();
      }
      
    }
  }

  public int getPlayerLocX() {
    return iPosX;
  }

  public int getPlayerLocY() {
    return iPosY;
  }

  public void drawPlayer(Graphics g, Image img, int iOffsetX, int iOffsetY) {
    g.setColor(Color.red);
    g.fillRect((iPosX * Tile.TILE_SIZE.width) + iOffsetX, 
               (iPosY * Tile.TILE_SIZE.height) + iOffsetY, 
               Tile.TILE_SIZE.width, 
               Tile.TILE_SIZE.height);
    if (img != null) {
      g.drawImage(img,
                  (iPosX * Tile.TILE_SIZE.width) + iOffsetX,
                  (iPosY * Tile.TILE_SIZE.height) + iOffsetY,
                  null
                 );
    }
  }
}

/**
 * Tile.java - represents a tile object 
 * 
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;
import java.awt.event.*;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;

public class Tile {

  RectangleShape rsTile;
  TextShape rsTextTile;
  sokoban theSokoban;

  int iPosX;
  int iPosY;
  
  /*** CONSTANTS ***/
  private Color WALL_BORDER_COLOR = new Color(192, 192, 255);

  /*** INSTANCE VARIABLES ***/
  private boolean isPassable;
  private BoardData theBoardData;

  /** 
   * Tile - constructor
   */
  public Tile(BoardData bd, sokoban s) {
    isPassable = true;
    theBoardData = bd;
    theSokoban = s;
  }

  /**
   * addRectangleTile
   */
  public void addRectangleTile(BoardView bv, int x, int y,
                               int w, int h, int iRow, int iCol) {
    iPosX = iCol;
    iPosY = iRow;
    rsTile = new RectangleShape(x, y, w, h);
    rsTile.setFilled(true);

    if (isPassable) {
      rsTile.setFillColor(bv.getBlankColor());
      rsTile.setLineColor(bv.getBlankColor());
    } else {
      rsTile.setFillColor(bv.getWallColor());
      rsTile.setLineColor(bv.getWallColor());

    }
    bv.add(rsTile);

    SilInteractor clicked = new SilInteractor(
                                new SilMouseEvent(MouseEvent.MOUSE_PRESSED),
                                new SilMouseEvent(MouseEvent.MOUSE_RELEASED)
                                                ) {
      public void startAction(InputEvent e) {
      }
                                                                                
      public void stopAction(InputEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
          theSokoban.getBoardData().getPlayer().movePlayer(iPosY, iPosX);
        }

      }
                                                                                
      public void runningAction(InputEvent e) {
      }
    };
    rsTile.addInteractor(clicked);

  }

  public void addRectangleTextTile(TextBoardView bv, int x, int y,
                               int w, int h) {

    rsTextTile = new TextShape(x, y, "#");
    rsTextTile.setFilled(true);

    rsTextTile.setFillColor(Color.white);
    rsTextTile.setLineColor(Color.black);
                                                                                
    if (isPassable) {
      rsTextTile.setText(" ");
    } else {
      rsTextTile.setText("#");
    }

    bv.add(rsTextTile);
  }

  public RectangleShape getShape() {
    return rsTile;
  }

  public TextShape getTextShape() {
    return rsTextTile;
  }

  public void setWallColor(Color c) {
    if (!isPassable) {
      rsTile.setFillColor(c);
      rsTile.setLineColor(c);
    }
  }

  public void setBlankColor(Color c) {
    if (isPassable) {
      rsTile.setFillColor(c);
      rsTile.setLineColor(c);
    }
  }

  /**
   * getPassable - accessor method
   */
  public boolean getPassable() {
    return(isPassable);
  }

  /**
   * setPassable - set method
   */
  public void setPassable(boolean b) {
    isPassable = b;
  }

  /**
   * drawTile - tell the Tile to draw itself
   */
  public void drawTile(Graphics g, int x, int y, int iOffsetX, int iOffsetY,
                       ViewInterface bv) {
    if (isPassable) {
      g.setColor(bv.getBlankColor());
      g.fillRect((x * bv.getTileSize().width) + iOffsetX, (y * bv.getTileSize().height) + iOffsetY, 
                 bv.getTileSize().width, bv.getTileSize().height);
    } else {
      g.setColor(bv.getWallColor());
      g.fillRect((x * bv.getTileSize().width) + iOffsetX, (y * bv.getTileSize().height) + iOffsetY, 
                 bv.getTileSize().width, bv.getTileSize().height);



      g.setColor(WALL_BORDER_COLOR);
      if ((theBoardData.getTile(x - 1, y) == null) ||
          ((theBoardData.getTile(x - 1, y) != null) &&
           (theBoardData.getTile(x - 1, y).getPassable())
          )
         ) {
        g.drawLine((x * bv.getTileSize().width) + iOffsetX, 
                   (y * bv.getTileSize().height) + iOffsetY,
                   (x * bv.getTileSize().width) + iOffsetX,  
                   (y * bv.getTileSize().height) + iOffsetY + bv.getTileSize().height - 1 
                  );
      }

      if ((theBoardData.getTile(x + 1, y) == null) ||
          ((theBoardData.getTile(x + 1, y) != null) &&
           (theBoardData.getTile(x + 1, y).getPassable())
          )
         ) {
        g.drawLine((x * bv.getTileSize().width) + iOffsetX + bv.getTileSize().width - 1, 
                   (y * bv.getTileSize().height) + iOffsetY,
                   (x * bv.getTileSize().width) + iOffsetX + bv.getTileSize().width - 1,  
                   (y * bv.getTileSize().height) + iOffsetY + bv.getTileSize().height - 1 
                  );
      }

      if ((theBoardData.getTile(x, y + 1) == null) ||
          ((theBoardData.getTile(x, y + 1) != null) &&
           (theBoardData.getTile(x, y + 1).getPassable())
          )
         ) {
        g.drawLine((x * bv.getTileSize().width) + iOffsetX, 
                   (y * bv.getTileSize().height) + iOffsetY + bv.getTileSize().height - 1,
                   (x * bv.getTileSize().width) + iOffsetX + bv.getTileSize().width - 1,  
                   (y * bv.getTileSize().height) + iOffsetY + bv.getTileSize().height - 1 
                  );
      }

      if ((theBoardData.getTile(x, y - 1) == null) ||
          ((theBoardData.getTile(x, y - 1) != null) &&
           (theBoardData.getTile(x, y - 1).getPassable())
          )
         ) {
        g.drawLine((x * bv.getTileSize().width) + iOffsetX, 
                   (y * bv.getTileSize().height) + iOffsetY,
                   (x * bv.getTileSize().width) + iOffsetX + bv.getTileSize().width - 1,  
                   (y * bv.getTileSize().height) + iOffsetY 
                  );
      }



    }
  }


  /**
   * drawTextTile - tell the Tile to draw itself
   */
  public void drawTextTile(Graphics g, int x, int y, int iOffsetX, int iOffsetY,
                       TextBoardView tbv) {
      g.setColor(Color.white);
      g.fillRect(((x + 0) * tbv.getTileSize().width) + iOffsetX, 
                 ((y + 0) * tbv.getTileSize().height) + iOffsetY,
                 tbv.getTileSize().width, tbv.getTileSize().height);

    if (!isPassable) {
      g.setColor(Color.black);
      g.drawString("#", ((x + 0) * tbv.getTileSize().width) + iOffsetX, 
                        ((y + 1) * tbv.getTileSize().height) + iOffsetY
                  );
    }
  }


}

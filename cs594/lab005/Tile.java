/**
 * Tile.java - represents a tile object 
 * 
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;

public class Tile {

  /*** CONSTANTS ***/
// public static final Dimension TILE_SIZE = new Dimension(20, 20);

/*
  private Color FREE_SPACE_COLOR = new Color(84, 84, 84);
  private Color WALL_COLOR = new Color(64, 64, 192);
*/
  private Color WALL_BORDER_COLOR = new Color(192, 192, 255);

  /*** INSTANCE VARIABLES ***/
  private boolean isPassable;
  private BoardData theBoardData;

  /** 
   * Tile - constructor
   */
  public Tile(BoardData bd) {
    isPassable = true;
    theBoardData = bd;
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

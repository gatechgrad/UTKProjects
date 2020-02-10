import java.awt.*;

public class Tile {

  /*** CONSTANTS ***/
  public static final Dimension TILE_SIZE = new Dimension(20, 20);
  public static final Color FREE_SPACE_COLOR = new Color(84, 84, 84);
  public static final Color WALL_COLOR = new Color(64, 64, 192);
  public static final Color WALL_BORDER_COLOR = new Color(192, 192, 255);

  /*** INSTANCE VARIABLES ***/
  private boolean isPassable;
  private BoardData theBoardData;

  public Tile(BoardData bd) {
    isPassable = true;
    theBoardData = bd;
  }

  public boolean getPassable() {
    return(isPassable);
  }

  public void setPassable(boolean b) {
    isPassable = b;
  }

  public void drawTile(Graphics g, int x, int y, int iOffsetX, int iOffsetY) {
    if (isPassable) {
      g.setColor(FREE_SPACE_COLOR);
      g.fillRect((x * TILE_SIZE.width) + iOffsetX, (y * TILE_SIZE.height) + iOffsetY, 
                 TILE_SIZE.width, TILE_SIZE.height);
    } else {
      g.setColor(WALL_COLOR);
      g.fillRect((x * TILE_SIZE.width) + iOffsetX, (y * TILE_SIZE.height) + iOffsetY, 
                 TILE_SIZE.width, TILE_SIZE.height);



      g.setColor(WALL_BORDER_COLOR);
      if ((theBoardData.getTile(x - 1, y) == null) ||
          ((theBoardData.getTile(x - 1, y) != null) &&
           (theBoardData.getTile(x - 1, y).getPassable())
          )
         ) {
        g.drawLine((x * TILE_SIZE.width) + iOffsetX, 
                   (y * TILE_SIZE.height) + iOffsetY,
                   (x * TILE_SIZE.width) + iOffsetX,  
                   (y * TILE_SIZE.height) + iOffsetY + TILE_SIZE.height - 1 
                  );
      }

      if ((theBoardData.getTile(x + 1, y) == null) ||
          ((theBoardData.getTile(x + 1, y) != null) &&
           (theBoardData.getTile(x + 1, y).getPassable())
          )
         ) {
        g.drawLine((x * TILE_SIZE.width) + iOffsetX + TILE_SIZE.width - 1, 
                   (y * TILE_SIZE.height) + iOffsetY,
                   (x * TILE_SIZE.width) + iOffsetX + TILE_SIZE.width - 1,  
                   (y * TILE_SIZE.height) + iOffsetY + TILE_SIZE.height - 1 
                  );
      }

      if ((theBoardData.getTile(x, y + 1) == null) ||
          ((theBoardData.getTile(x, y + 1) != null) &&
           (theBoardData.getTile(x, y + 1).getPassable())
          )
         ) {
        g.drawLine((x * TILE_SIZE.width) + iOffsetX, 
                   (y * TILE_SIZE.height) + iOffsetY + TILE_SIZE.height - 1,
                   (x * TILE_SIZE.width) + iOffsetX + TILE_SIZE.width - 1,  
                   (y * TILE_SIZE.height) + iOffsetY + TILE_SIZE.height - 1 
                  );
      }

      if ((theBoardData.getTile(x, y - 1) == null) ||
          ((theBoardData.getTile(x, y - 1) != null) &&
           (theBoardData.getTile(x, y - 1).getPassable())
          )
         ) {
        g.drawLine((x * TILE_SIZE.width) + iOffsetX, 
                   (y * TILE_SIZE.height) + iOffsetY,
                   (x * TILE_SIZE.width) + iOffsetX + TILE_SIZE.width - 1,  
                   (y * TILE_SIZE.height) + iOffsetY 
                  );
      }



    }
  }
}

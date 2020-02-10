import java.awt.*;

public class Tile {

  /*** CONSTANTS ***/
  public static final Dimension TILE_SIZE = new Dimension(20, 20);
  public static final Color FREE_SPACE_COLOR = new Color(84, 84, 84);

  /*** INSTANCE VARIABLES ***/
  private boolean isPassable;

  public Tile() {
    isPassable = true;
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
      g.setColor(Color.blue);
      g.fillRect((x * TILE_SIZE.width) + iOffsetX, (y * TILE_SIZE.height) + iOffsetY, 
                 TILE_SIZE.width, TILE_SIZE.height);
    }
  }
}

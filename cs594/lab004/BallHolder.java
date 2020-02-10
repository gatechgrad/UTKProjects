import java.awt.*;
import javax.swing.*;

public class BallHolder {

  public static final Color HOLDER_COLOR = new Color(64, 128, 128);

  int iPosX;
  int iPosY;


  public BallHolder() {

  }

  public void setLocation(int x, int y) {
    iPosX = x;
    iPosY = y;
  }

  public int getPosX() {
    return iPosX;
  }

  public int getPosY() {
    return iPosY;
  }

  public void drawBallHolder(Graphics g, int iOffsetX, int iOffsetY) {
    g.setColor(HOLDER_COLOR);
    g.fillRect((iPosX * Tile.TILE_SIZE.width) + iOffsetX,
               (iPosY * Tile.TILE_SIZE.height) + iOffsetY,
               Tile.TILE_SIZE.width,
               Tile.TILE_SIZE.height
              );

  }
}

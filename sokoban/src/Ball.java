import java.awt.*;
import javax.swing.*;

public class Ball {

  int iPosX;
  int iPosY;

  public Ball() {
    iPosX = 0;
    iPosY = 0; 
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

  public void drawBall(Graphics g, Image img, int iOffsetX, int iOffsetY) {
    g.setColor(Color.green);
    g.fillRect((iPosX * Tile.TILE_SIZE.width) + iOffsetX,
               (iPosY * Tile.TILE_SIZE.height) + iOffsetY,
               Tile.TILE_SIZE.width,
               Tile.TILE_SIZE.height
              );
    if (img != null) {
      g.drawImage(img,
                  (iPosX * Tile.TILE_SIZE.width) + iOffsetX,
                   (iPosY * Tile.TILE_SIZE.height) + iOffsetY,                                    null
                 ); 

    }
  }

}

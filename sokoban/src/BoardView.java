import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardView extends JComponent implements MouseMotionListener, MouseListener {


  sokoban theSB;
  boolean isDragging = false;
  Image imgBall;
  Image imgPlayer;

  private Dimension dimOffset;


  public BoardView(sokoban sb) {
    theSB = sb;
    addMouseMotionListener(this);
    addMouseListener(this);
    setPreferredSize( new Dimension(640, 480));
    imgBall = (new ImageIcon("ball.gif", "")).getImage();
    imgPlayer = (new ImageIcon("character.gif", "")).getImage();
  }

  public void paintComponent(Graphics g) {
   int x, y;
   int i;                                                                             


   if (theSB.getBoardData() != null) {

    dimOffset = new Dimension((getSize().width - ((theSB.getBoardData().getMaxX()) * Tile.TILE_SIZE.width)) / 2,
                              (getSize().height - ((theSB.getBoardData().getMaxY() + 1) * Tile.TILE_SIZE.height)) / 2);

//    System.out.println("Offset: " + dimOffset);


/*
    for (x = 0; x < theSB.getBoardData().getMaxX(); x++) {
      for (y = 0; y < theSB.getBoardData().getMaxY(); y++) {
        if (!theSB.getBoardData().getTile(y, x).getPassable()) {
          System.out.print("X");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println("");
    }
*/
                                                                                
    //draw the walls
    for (x = 0; x < theSB.getBoardData().getMaxX(); x++) {
      for (y = 0; y < theSB.getBoardData().getMaxY(); y++) {
        theSB.getBoardData().getTile(x, y).drawTile(g, x, y, dimOffset.width, dimOffset.height);
      }
    }
                                                                                


    //draw the ball holders
    for (i = 0; i < theSB.getBoardData().getBallHolders().size(); i++) {
      ((BallHolder) theSB.getBoardData().getBallHolders().elementAt(i)).drawBallHolder(g, dimOffset.width, dimOffset.height);
    }

    //draw the balls
    for (i = 0; i < theSB.getBoardData().getBalls().size(); i++) {
      ((Ball) theSB.getBoardData().getBalls().elementAt(i)).drawBall(g, imgBall, dimOffset.width, dimOffset.height);
    }

    //draw the player
    theSB.getBoardData().getPlayer().drawPlayer(g, imgPlayer, dimOffset.width, dimOffset.height);
    
    //draw the score
    g.setColor(Color.black);
    g.setFont(new Font("Helvetica", Font.PLAIN, 12));
    g.drawString("Moves: " + theSB.getBoardData().getMoves(), 5 + dimOffset.width, 
                 theSB.getBoardData().getMaxY() * Tile.TILE_SIZE.height + 20 + dimOffset.height);
    g.drawString("Pushes: " + theSB.getBoardData().getPushes(), 128 + dimOffset.width, 
                 theSB.getBoardData().getMaxY() * Tile.TILE_SIZE.height + 20 + dimOffset.height);

   }
  }

  public Point getMouseCell(int iMouseX, int iMouseY) {
    int x, y;

    x = (iMouseX - dimOffset.width) / Tile.TILE_SIZE.width;
    y = (iMouseY - dimOffset.height) / Tile.TILE_SIZE.height;

    return (new Point(x, y));

  }


  public void cantMove() {
    System.out.println("beep");
    Toolkit.getDefaultToolkit().beep();
  }

  public void showWin() {
    JOptionPane.showMessageDialog( this, 
                  "You Win.", 
                  "Sokoban",
 JOptionPane.INFORMATION_MESSAGE );
  }


  public void mouseMoved(MouseEvent e) { }

  public void mouseDragged(MouseEvent e) {
    Point pntCell;

    if (isDragging) {
      pntCell = getMouseCell(e.getX(), e.getY());

      theSB.getBoardData().getPlayer().playerDragged(pntCell.x, pntCell.y);
    }
  }

  public void mousePressed(MouseEvent e) { 
    Point pntCell;
      System.out.println("Mouse Pressed");

    pntCell = getMouseCell(e.getX(), e.getY());
  
    if ((pntCell.x == theSB.getBoardData().getPlayer().getPlayerLocX()) &&
        (pntCell.y == theSB.getBoardData().getPlayer().getPlayerLocY()) 
       ) {
      isDragging = true;
      System.out.println("Player clicked");
    }
  }

  public void mouseReleased(MouseEvent e) {
    isDragging = false; 
  }

  public void mouseClicked(MouseEvent e) { }

  public void mouseEntered(MouseEvent e) { }

  public void mouseExited(MouseEvent e) { }

}

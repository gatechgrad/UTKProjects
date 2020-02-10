/**
 * BoardView.java -  Displays the game board data ("the View")
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 3, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardView extends JComponent implements MouseMotionListener, MouseListener, ViewInterface {

  /*** CONSTANTS ***/
  //The size of the score display
  public static final Dimension SCORE_DISPLAY_SIZE = new Dimension(300, 20); 

  public static final Dimension DIM_SMALL_TILE_SIZE = new Dimension(10, 10); 
  public static final Dimension DIM_MEDIUM_TILE_SIZE = new Dimension(20, 20); 
  public static final Dimension DIM_LARGE_TILE_SIZE = new Dimension(30, 30); 

  /*** INSTANCE VARIABLES ***/
  private sokoban theSokoban;
  protected BoardData theModel;
  private boolean isDragging = false;
  private Image imgBall;
  private Image imgPlayer;
  protected Dimension dimOffset;

  private Color colorWall;
  private Color colorGoal;
  private Color colorBlank;
  protected Dimension dimTileSize = DIM_MEDIUM_TILE_SIZE; 

  /**
   * BoardView - constructor 
   */
  public BoardView(BoardData m, sokoban s) {
    theModel = m;
    theSokoban = s;
    addMouseMotionListener(this);
    addMouseListener(this);
    setPreferredSize( new Dimension(540, 480));
    imgBall = (new ImageIcon("ball.gif", "")).getImage();
    imgPlayer = (new ImageIcon("character.gif", "")).getImage();
    colorWall =  new Color(64, 64, 192);
    colorGoal =  new Color(64, 128, 128);
    colorBlank = new Color(84, 84, 84);

  }

  /**
   * paintComponent - draws the game board
   */
  public void paintComponent(Graphics g) {
   int x, y;
   int i;
   Rectangle rectClip;

   if (theModel != null) {

    dimOffset = getOffset();
    rectClip = calculateClipRegion();
    
    //The Java setClip method does not seem to work correctly
    //If I set the clip region, then everything outsize of the
    //clip region gets set to the background color
    //My program does however calculate correctly the tiles to be
    //updated (theSB.getChagnedTiles()) and calcuates correctly
    //the clipping region (calcuateClipRegion())
    /*
    if (rectClip != null) {
      g.setClip(rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    }
    */


    //draw the walls
    for (x = 0; x < theModel.getMaxX(); x++) {
      for (y = 0; y < theModel.getMaxY(); y++) {
        theModel.getTile(x, y).drawTile(g, x, y, dimOffset.width, dimOffset.height, this);
      }
    }
                      

    //draw the ball holders
    for (i = 0; i < theModel.getBallHolders().size(); i++) {
      ((BallHolder) theModel.getBallHolders().elementAt(i)).drawBallHolder(g, dimOffset.width, dimOffset.height, this);
    }

    //draw the balls
    for (i = 0; i < theModel.getBalls().size(); i++) {
      ((Ball) theModel.getBalls().elementAt(i)).drawBall(g, imgBall, dimOffset.width, dimOffset.height, this);
    }

    //draw the player
    theModel.getPlayer().drawPlayer(g, imgPlayer, dimOffset.width, dimOffset.height, this);
    
    //draw the score
    g.setColor(Color.black);
    g.setFont(new Font("Helvetica", Font.PLAIN, 12));
    g.drawString("Moves: " + theModel.getMoves(), 5 + dimOffset.width, 
                 theModel.getMaxY() * dimTileSize.height + 20 + dimOffset.height);
    g.drawString("Pushes: " + theModel.getPushes(), 128 + dimOffset.width, 
                 theModel.getMaxY() * dimTileSize.height + 20 + dimOffset.height);
    drawSelected(g);

   }
   
  }

  protected void drawSelected(Graphics g) {
  }

  /**
   * getMouseCell - returns the cell in the game board array
   *                of where the mouse was clicked
   */
  public Point getMouseCell(int iMouseX, int iMouseY) {
    int x, y;

    x = (iMouseX - dimOffset.width) / dimTileSize.width;
    y = (iMouseY - dimOffset.height) / dimTileSize.height;

    return (new Point(x, y));

  }


  /**
   * cantMove - implementation of invalid move
   */
  public void cantMove() {
    System.out.println("beep"); //if sound is not available
    Toolkit.getDefaultToolkit().beep();
  }

  /**
   * showWin - implementation of showing a win
   */
  public void showWin() {
    JOptionPane.showMessageDialog( this, 
                  "You Win.", 
                  "Sokoban",
 JOptionPane.INFORMATION_MESSAGE );
  }


  /**
   * mouseMoved, mouseDragged, mousePressed,
   * mouseClicked, mouseEntered, mouseExited
   * mouseRelease, mouseMoved, mouseDragged  - MouseListener 
   *                                           and MouseMotion-
   *                                           Listener methods
   */
  public void mouseMoved(MouseEvent e) { }

  public void mouseDragged(MouseEvent e) {
    Point pntCell;

    if (isDragging) {
      pntCell = getMouseCell(e.getX(), e.getY());

      theModel.getPlayer().playerDragged(pntCell.x, pntCell.y);
    }
  }

  public void mousePressed(MouseEvent e) { 
   int iTurns;
   Point pntCell;

   if (theModel != null) {
   if (e.getModifiers() == InputEvent.BUTTON1_MASK) {

    pntCell = getMouseCell(e.getX(), e.getY());
  
    if ((pntCell.x == theModel.getPlayer().getPlayerLocX()) &&
        (pntCell.y == theModel.getPlayer().getPlayerLocY()) 
       ) {
      isDragging = true;
//      System.out.println("Player clicked");

    } else if ((pntCell.x == theModel.getPlayer().getPlayerLocX() - 1) &&
        (pntCell.y == theModel.getPlayer().getPlayerLocY()) 
       ) {
      theModel.getPlayer().moveLeft();

    } else if ((pntCell.x == theModel.getPlayer().getPlayerLocX() + 1) &&
        (pntCell.y == theModel.getPlayer().getPlayerLocY()) 
       ) {
      theModel.getPlayer().moveRight();

    } else if ((pntCell.x == theModel.getPlayer().getPlayerLocX()) &&
        (pntCell.y == theModel.getPlayer().getPlayerLocY() + 1) 
       ) {
      theModel.getPlayer().moveDown();

    } else if ((pntCell.x == theModel.getPlayer().getPlayerLocX()) &&
        (pntCell.y == theModel.getPlayer().getPlayerLocY() - 1) 
       ) {
      theModel.getPlayer().moveUp();



    } else {
      iTurns = theModel.getTurnsToMove(
                      theModel.getPlayer().getPlayerLocX(),
                      theModel.getPlayer().getPlayerLocY(),
                      pntCell.x, pntCell.y);
      if (iTurns > 0) {
        theModel.addTurns(iTurns);

        theModel.addUndoHistory(
           new UndoHistory(theModel.getPlayer().getPlayerLocX() - pntCell.x,
           theModel.getPlayer().getPlayerLocY() - pntCell.y, 
           null, iTurns));

        theModel.addChangedTile(theModel.getPlayer().getPlayerLocX(), 
                                            theModel.getPlayer().getPlayerLocY() );
        theModel.getPlayer().setPlayerLocation(pntCell.x, pntCell.y);

        theModel.addChangedTile(theModel.getPlayer().getPlayerLocX(), 
                                            theModel.getPlayer().getPlayerLocY() );
        if (theSokoban != null) {
          theSokoban.repaintViews();
        }
      } else {
        cantMove();
      }
    }
   }

   if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
     theModel.undo();
   }
   }
  }

  public void mouseReleased(MouseEvent e) {
    isDragging = false; 
  }

  public void mouseClicked(MouseEvent e) { }

  public void mouseEntered(MouseEvent e) { }

  public void mouseExited(MouseEvent e) { }

  /** 
   * getOffset - returns the game board offset
   */
  private Dimension getOffset() {
    Dimension d;

    d = new Dimension((getSize().width - ((theModel.getMaxX()) * dimTileSize.width)) / 2,
                              (getSize().height - ((theModel.getMaxY() + 1) * dimTileSize.height)) / 2);
 

    return d; 
  }

  /**
   * calculateClipRegion - calculates the clipping region
   *                       from the list of changed tiles in the
   *                       model (BoardData)
   */
  private Rectangle calculateClipRegion() {
    int i;
    Point p;
    Rectangle rectReturn;

    dimOffset = getOffset();
    rectReturn = null;

   if ((theModel != null) && (theModel.getChangedTiles() != null)) {
    for (i = 0; i < theModel.getChangedTiles().size(); i++) {
      p = (Point) theModel.getChangedTiles().elementAt(i);
      if (rectReturn == null) {
        rectReturn = new Rectangle(dimOffset.width + (p.x * dimTileSize.width), 
                dimOffset.height + (p.y * dimTileSize.height), 
                dimTileSize.width,
                dimTileSize.height);
      } else {
        rectReturn = rectReturn.union(
                       new Rectangle(dimOffset.width + (p.x * dimTileSize.width), 
                       dimOffset.height + (p.y * dimTileSize.height), 
                       dimTileSize.width,
                       dimTileSize.height)
                     );

      }

//      System.out.println("Tile: " + p.x + ", " + p.y);
    }

    theModel.resetChangedTiles();

    //since I am drawing the score board at the bottom, I must add 
    //the score display to the clipping region
    if (rectReturn != null) {
        rectReturn = rectReturn.union(
                       new Rectangle(dimOffset.width, 
                       dimOffset.height + (theModel.getMaxY() * dimTileSize.height), 
                       SCORE_DISPLAY_SIZE.width,
                       SCORE_DISPLAY_SIZE.height)
                     );

    }
   }
    return rectReturn;
  }
 
  public void setWallColor(Color c) {
    colorWall = c;
  }

  public void setBlankColor(Color c) {
    colorBlank = c;
  }

  public void setGoalColor(Color c) {
    colorGoal = c;
  }

  public Color getWallColor() {
    return colorWall;
  }
  
  public Color getGoalColor() {
    return colorGoal;
  }
  
  public Color getBlankColor() {
    return colorBlank;
  }

  public void setViewSmallSize() {
    dimTileSize = DIM_SMALL_TILE_SIZE;
  }

  public void setViewMediumSize() {
    dimTileSize = DIM_MEDIUM_TILE_SIZE;
  }

  public void setViewLargeSize() {
    dimTileSize = DIM_LARGE_TILE_SIZE;
  }

  public Dimension getTileSize() {
    return dimTileSize;
  }

  public void updateModel(BoardData b) {
    theModel = b;  
  }  
}

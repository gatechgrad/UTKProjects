/**
 * BoardView.java -  Displays the game board data ("the View")
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 28, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;

public class BoardView extends CanvasShape implements /* MouseMotionListener, MouseListener, */ ViewInterface {

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

  private TextShape tsScore;
  private TextShape tsPush;
  private RectangleShape rsBoard;

  /**
   * BoardView - constructor 
   */
  public BoardView(BoardData m, sokoban s) {

    theModel = m;
    theSokoban = s;
    setPreferredSize( new Dimension(540, 480));

    colorWall =  new Color(64, 64, 192);
    colorGoal =  new Color(64, 128, 128);
    colorBlank = new Color(84, 84, 84);

    tsScore = new TextShape();
    tsPush = new TextShape();
    rsBoard = new RectangleShape();
    addInteractors();
  }

  public void updateMoves() {
    tsScore.setText("Moves: " + theModel.getMoves());
    tsPush.setText("Pushes: " + theModel.getPushes());
  }


  public void addInteractors() {
   SilInteractor keyInter
        = new SilOneShotInteractor(new SilKeyEvent(KeyEvent.KEY_PRESSED)) {
                                                                                
      public void actionPerformed(InputEvent e1) {
          KeyEvent e = (KeyEvent) e1;

       if (theSokoban.getBoardData() != null) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          theSokoban.getBoardData().getPlayer().moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          theSokoban.getBoardData().getPlayer().moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
          theSokoban.getBoardData().getPlayer().moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          theSokoban.getBoardData().getPlayer().moveDown();
                                                                                
        } else if (e.getKeyCode() == KeyEvent.VK_H) {
          theSokoban.getBoardData().getPlayer().moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
          theSokoban.getBoardData().getPlayer().moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_K) {
          theSokoban.getBoardData().getPlayer().moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
          theSokoban.getBoardData().getPlayer().moveRight();
        }
                                                                                
       }
      }

    };
                                                                                
    this.addInteractor(keyInter);

    SilInteractor mouseInter = new SilInteractor(
                                new SilMouseEvent(MouseEvent.MOUSE_PRESSED),
                                new SilMouseEvent(MouseEvent.MOUSE_RELEASED)
                                                ) {
      public void startAction(InputEvent e) {
      }
                                                                                
      public void stopAction(InputEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
          theSokoban.getBoardData().undo();
        }
                                                                                
      }
                                                                                
    };

    this.addInteractor(mouseInter);

  }

  /**
   * drawSelected - method that can be overwritten by subclasses
   *                to draw the selected cell
   */
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
  }

  /** 
   * getOffset - returns the game board offset
   */
  private Dimension getOffset() {
    Dimension d;

/*
    d = new Dimension((getSize().width - ((theModel.getMaxX()) * dimTileSize.width)) / 2,
                              (getSize().height - ((theModel.getMaxY() + 1) * dimTileSize.height)) / 2);
 */
   d = new Dimension(0, 0);

    return d; 
  }

  /**
   * setWallColor, setBlankColor, setGoalColor - set methods for 
   *                                             color
   */
  public void setWallColor(Color c) {
    int i, j;
    colorWall = c;

    for (i = 0; i < theModel.getMaxX(); i++) {
      for (j = 0; j < theModel.getMaxY(); j++) {
        theModel.getTile(i, j).setWallColor(colorWall); 
      }
    }
  }

  public void setBlankColor(Color c) {
    int i, j;
    colorBlank = c;

    for (i = 0; i < theModel.getMaxX(); i++) {
      for (j = 0; j < theModel.getMaxY(); j++) {
        theModel.getTile(i, j).setBlankColor(colorBlank); 
      }
    }
  }

  public void setGoalColor(Color c) {
    int i;
    colorGoal = c;
    for (i = 0; i < theModel.getBallHolders().size(); i++) {
      ((BallHolder) theModel.getBallHolders().elementAt(i)).setGoalColor(colorGoal);
    }

  }

  /**
   * getWallColor, getGoalColor, getBlankColor - get methods for
   *                                             color
   */
  public Color getWallColor() {
    return colorWall;
  }
  
  public Color getGoalColor() {
    return colorGoal;
  }
  
  public Color getBlankColor() {
    return colorBlank;
  }


  /**
   * setView* - set methods for tile size
   */
  public void setViewSmallSize() {
    dimTileSize = DIM_SMALL_TILE_SIZE;
  }

  public void setViewMediumSize() {
    dimTileSize = DIM_MEDIUM_TILE_SIZE;
  }

  public void setViewLargeSize() {
    dimTileSize = DIM_LARGE_TILE_SIZE;
  }

  /**
   * getTileSize - returns the current tile size
   */
  public Dimension getTileSize() {
    return dimTileSize;
  }

  /**
   * updateModel - set a new model for the view 
   */
  public void updateModel(BoardData b) {
    int i, j;
    theModel = b;  

    rsBoard.setWidth(theModel.getMaxX() * dimTileSize.width);
    rsBoard.setHeight(theModel.getMaxY() * dimTileSize.height);

    tsScore.setText("Moves: " + theModel.getMoves());
    tsScore.setTop(rsBoard.getBottom() + 20);
    tsScore.setLeft(20);
    this.add(tsScore);
    tsPush.setText("Pushes: " + theModel.getPushes());
    tsPush.setTop(rsBoard.getBottom() + 20);
    tsPush.setLeft(200);
    this.add(tsPush);


    rsBoard.addConstraint(
      new silhouette.constraints.alignCenterX(this)
    );

    rsBoard.addConstraint(
      new silhouette.constraints.alignCenterY(this)
    );
    
 
    for (i = 0; i < theModel.getMaxX(); i++) {
      for (j = 0; j < theModel.getMaxY(); j++) {
        theModel.getTile(i, j).addRectangleTile(this, 
                                (int) (i * dimTileSize.width + rsBoard.getLeft()), 
                                (int) (j * dimTileSize.height + rsBoard.getTop()),
                                dimTileSize.width,
                                dimTileSize.height, 
                                i, j
                                ); 
      }
    }

    for (i = 0; i < theModel.getBallHolders().size(); i++) {
      ((BallHolder) theModel.getBallHolders().elementAt(i)).setGoalColor(colorGoal);
      this.add( ((BallHolder) theModel.getBallHolders().elementAt(i)).getShape());
    }

    
    for (i = 0; i < theModel.getBalls().size(); i++) {
      this.add( ((Ball) theModel.getBalls().elementAt(i)).getShape());
    }

    this.add(theModel.getPlayer().getShape());
  }  
}

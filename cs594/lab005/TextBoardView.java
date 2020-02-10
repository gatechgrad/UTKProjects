import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextBoardView extends JComponent implements ViewInterface {
  public static final Font SMALL_FONT = new Font("Courier New", Font.PLAIN, 8);
  public static final Font MEDIUM_FONT = new Font("Courier New", Font.PLAIN, 12);
  public static final Font LARGE_FONT = new Font("Courier New", Font.PLAIN, 16);

  private Font theFont;
  private BoardData theModel;

  public TextBoardView(BoardData bd) {
    theModel = bd;
    setPreferredSize(new Dimension(300, 128));
    setBackground(Color.cyan);
  }

  public void paintComponent(Graphics g) {
   int i;
   int x, y;

   if (theModel != null) {
    //draw the walls
    for (x = 0; x < theModel.getMaxX(); x++) {
      for (y = 0; y < theModel.getMaxY(); y++) {
        theModel.getTile(x, y).drawTextTile(g, x, y, 0, 0, this);
      }
    }
                                                                                
    //draw the ball holders
    for (i = 0; i < theModel.getBallHolders().size(); i++) {
      ((BallHolder) theModel.getBallHolders().elementAt(i)).drawTextBallHolder(g, 0, 0, this);
    }
                                                                                
    //draw the balls
    for (i = 0; i < theModel.getBalls().size(); i++) {
      ((Ball) theModel.getBalls().elementAt(i)).drawTextBall(g, 0, 0, this);
    }

    //draw the player
    theModel.getPlayer().drawTextPlayer(g, 0, 0, this);
   }

  }

  public Dimension getTileSize() {
    return (new Dimension(16, 16));
  }
  
  public Font getViewFont() {
    return theFont;
  }

  public void setGoalColor(Color c) {

  }
  public void setWallColor(Color c) {

  }

  public void setBlankColor(Color c) {

  }

  public Color getGoalColor() {
    return Color.black;
  }
  public Color getWallColor() {
    return Color.black; 
  }

  public Color getBlankColor() {
    return Color.black;
  }

  public void setViewSmallSize() {
    theFont = SMALL_FONT;
  }

  public void setViewMediumSize() {
    theFont = MEDIUM_FONT;
  }

  public void setViewLargeSize() {
    theFont = LARGE_FONT;
  }

  public void cantMove() {
    //No graphical representation needed
  }

  public void showWin() {
    //No graphical representation needed
  }

  public void updateModel(BoardData b) {
    theModel = b;
  }

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddWidget extends JComponent implements MouseListener {

  public static final Rectangle RECT_UP = new Rectangle(20, 0, 20, 20); 
  public static final Rectangle RECT_RIGHT = new Rectangle(40, 20, 20, 20); 
  public static final Rectangle RECT_DOWN = new Rectangle(20, 40, 20, 20); 
  public static final Rectangle RECT_LEFT = new Rectangle(0, 20, 20, 20); 

  boolean isDisabled;
  GameBoardEditor theGBE;

  public AddWidget(GameBoardEditor gbe) {
    theGBE = gbe;
    isDisabled = false;
    setPreferredSize(new Dimension(60, 60));
    addMouseListener(this);
  }

  public void setDisabled(boolean b) {
    isDisabled = b;
  }

  public void paintComponent(Graphics g) {
    g.setColor(Color.blue);
    g.fillRect(RECT_UP.x, RECT_UP.y, RECT_UP.width, RECT_UP.height);

    g.fillRect(RECT_RIGHT.x, RECT_RIGHT.y, RECT_RIGHT.width, RECT_RIGHT.height);

    g.fillRect(RECT_DOWN.x, RECT_DOWN.y, RECT_DOWN.width, RECT_DOWN.height);

    g.fillRect(RECT_LEFT.x, RECT_LEFT.y, RECT_LEFT.width, RECT_LEFT.height);

    if (!isDisabled) {
      g.setColor(Color.white);
      //UP ARROW
      g.drawLine(RECT_UP.x + (RECT_UP.width / 2), 
                 RECT_UP.y, 
                 RECT_UP.x + (RECT_UP.width / 2), 
                 RECT_UP.y + RECT_UP.height);

      g.drawLine(RECT_UP.x + (RECT_UP.width / 4), 
                 RECT_UP.y + (RECT_UP.height / 4), 
                 RECT_UP.x + (RECT_UP.width / 2), 
                 RECT_UP.y);

      g.drawLine(RECT_UP.x + (3 * RECT_UP.width / 4), 
                 RECT_UP.y + (RECT_UP.height / 4), 
                 RECT_UP.x + (RECT_UP.width / 2), 
                 RECT_UP.y);


      //RIGHT ARROW
      g.drawLine(RECT_RIGHT.x, 
                 RECT_RIGHT.y + (RECT_RIGHT.height / 2), 
                 RECT_RIGHT.x + RECT_RIGHT.width, 
                 RECT_RIGHT.y + (RECT_RIGHT.height / 2));

      g.drawLine(RECT_RIGHT.x + (3 * RECT_RIGHT.width / 4), 
                 RECT_RIGHT.y + (RECT_RIGHT.height / 4), 
                 RECT_RIGHT.x + RECT_RIGHT.width, 
                 RECT_RIGHT.y + (RECT_RIGHT.height / 2));

      g.drawLine(RECT_RIGHT.x + (3 * RECT_RIGHT.width / 4), 
                 RECT_RIGHT.y + (3 * RECT_RIGHT.height / 4), 
                 RECT_RIGHT.x + RECT_RIGHT.width, 
                 RECT_RIGHT.y + (RECT_RIGHT.height / 2));


      //DOWN ARROW
      g.drawLine(RECT_DOWN.x + (RECT_DOWN.width / 2), 
                 RECT_DOWN.y, 
                 RECT_DOWN.x + (RECT_DOWN.width / 2), 
                 RECT_DOWN.y + RECT_DOWN.height);

      g.drawLine(RECT_DOWN.x + (RECT_DOWN.width / 4), 
                 RECT_DOWN.y + (3 * RECT_DOWN.height / 4), 
                 RECT_DOWN.x + (RECT_DOWN.width / 2), 
                 RECT_DOWN.y + RECT_DOWN.height);

      g.drawLine(RECT_DOWN.x + (3 * RECT_DOWN.width / 4), 
                 RECT_DOWN.y + (3 * RECT_DOWN.height / 4), 
                 RECT_DOWN.x + (RECT_DOWN.width / 2), 
                 RECT_DOWN.y + RECT_DOWN.height);


      //LEFT ARROW
      g.drawLine(RECT_LEFT.x, 
                 RECT_LEFT.y + (RECT_LEFT.height / 2), 
                 RECT_LEFT.x + RECT_LEFT.width, 
                 RECT_LEFT.y + (RECT_LEFT.height / 2));

      g.drawLine(RECT_LEFT.x + (RECT_LEFT.width / 4), 
                 RECT_LEFT.y + (RECT_LEFT.height / 4), 
                 RECT_LEFT.x, 
                 RECT_LEFT.y + (RECT_LEFT.height / 2));

      g.drawLine(RECT_LEFT.x, 
                 RECT_LEFT.y + (RECT_LEFT.width / 2), 
                 RECT_LEFT.x + (RECT_LEFT.width / 4), 
                 RECT_LEFT.y + (3 * RECT_LEFT.width / 4));

    }

  }

  public void mousePressed(MouseEvent e) {
    if (!isDisabled) {
      buttonClicked(e.getX(), e.getY()); 
    }
  }

  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }


  private void buttonClicked(int x, int y) {
   if (theGBE.getDrawingArea().getHighlighted()) {
    if (RECT_UP.contains(x, y)) {
      if (theGBE.getDrawingArea().getSelectedCell().y > -1) {
        theGBE.getModel().addRow(theGBE.getDrawingArea().getSelectedCell().y, 
                                 true);
        theGBE.getDrawingArea().moveDownSelected();
        theGBE.getDrawingArea().repaint(); 
      }
    } else if (RECT_RIGHT.contains(x, y)) {
      if (theGBE.getDrawingArea().getSelectedCell().x > -1) {
        theGBE.getModel().addCol(theGBE.getDrawingArea().getSelectedCell().x, 
                                 true);
        theGBE.getDrawingArea().repaint(); 
      }
    } else if (RECT_DOWN.contains(x, y)) {
      if (theGBE.getDrawingArea().getSelectedCell().y > -1) {
        theGBE.getModel().addRow(theGBE.getDrawingArea().getSelectedCell().y, 
                                 false);
        theGBE.getDrawingArea().repaint(); 
      }
    } else if (RECT_LEFT.contains(x, y)) {
      if (theGBE.getDrawingArea().getSelectedCell().x > -1) {
        theGBE.getModel().addCol(theGBE.getDrawingArea().getSelectedCell().x, 
                                 false);
        theGBE.getDrawingArea().moveRightSelected();
        theGBE.getDrawingArea().repaint(); 
      }
    }
   }
  }
}

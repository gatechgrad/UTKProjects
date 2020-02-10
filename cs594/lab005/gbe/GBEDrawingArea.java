import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GBEDrawingArea extends BoardView implements MouseMotionListener {

  Object objDragged;
  boolean isDragging;

  private BoardData theBoardData;
  private GameBoardEditor theGBE;
  int xSel, ySel;
  boolean highlightCell = false;

  public GBEDrawingArea(BoardData bd, GameBoardEditor gbe) {
    super(bd, null);
    xSel = -1;
    ySel = -1;
    theGBE = gbe;
    isDragging = false;
  }

  public void drawSelected(Graphics g) {
    int i;
    
    if ((highlightCell) &&(xSel > -1) && (ySel > -1)) {
      g.setColor(Color.green);
      System.out.println(dimOffset.width + "");
      g.fillRect(dimOffset.width + (xSel * dimTileSize.width), 
                 dimOffset.height + (ySel * dimTileSize.height),
                 dimTileSize.width, 
                 dimTileSize.height);

      //g.fillRect(0, 0, 50, 50);
    }
    
    //draw the grid
    g.setColor(new Color(0, 192, 0));
    for (i = 0; i < theModel.getMaxY() + 1; i++) {
      g.drawLine(dimOffset.width,
                 dimOffset.height + (i * dimTileSize.height),
                 dimOffset.width + (theModel.getMaxX() * dimTileSize.width),
                 dimOffset.height + (i * dimTileSize.height)
                );
    }

    for (i = 0; i < theModel.getMaxX() + 1; i++) {
      g.drawLine(dimOffset.width + (i * dimTileSize.width),
                 dimOffset.height,
                 dimOffset.width + (i * dimTileSize.width),
                 dimOffset.height + (theModel.getMaxY() * dimTileSize.height)
                );
    }

  }

  public void moveRightSelected() {
    xSel++;
  }

  public void moveDownSelected() {
    ySel++;
  }

  public void mousePressed(MouseEvent e) {
   Point pnt;

    pnt = getMouseCell(e.getX(), e.getY());
    if ((pnt.x < theModel.getMaxX()) &&
        (pnt.y < theModel.getMaxY()) 
       ) {
      xSel = pnt.x;
      ySel = pnt.y;
    } else {
      xSel = -1;
      ySel = -1;
      highlightCell = false;
    }
    
    if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
      highlightCell = true;
      objDragged = theModel.getPieceAt(xSel, ySel);
      if ((objDragged != null) && (xSel > -1) && (ySel > -1)) {
        isDragging = true;
      }
      repaint();
    }

    if (e.getModifiers() == InputEvent.BUTTON2_MASK) {
      if ((xSel > -1) && (ySel > -1)) {
        theModel.deleteGamePiece(xSel, ySel);
        repaint();
      }
    }

    if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
      highlightCell = false;
      theGBE.checkButtons();
      repaint();
    }
  }

  public void mouseReleased(MouseEvent e) {
    Point pnt;

    pnt = getMouseCell(e.getX(), e.getY());
    if ((pnt.x < theModel.getMaxX()) &&
        (pnt.y < theModel.getMaxY()) 
       ) {
      xSel = pnt.x;
      ySel = pnt.y;
    } else {
      xSel = -1;
      ySel = -1;
    }

    if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
     if (isDragging) {
      isDragging = false;
      theModel.moveObject(objDragged, -1, -1); //Move object off board so it
                                               //won't be deleted
      theModel.deleteGamePiece(xSel, ySel);
      theModel.moveObject(objDragged, xSel, ySel); 
      repaint();
     }
    }

  }

  public void mouseDragged(MouseEvent e) {
    Point pnt;

    pnt = getMouseCell(e.getX(), e.getY());
    if ((pnt.x < theModel.getMaxX()) &&
        (pnt.y < theModel.getMaxY()) 
       ) {
      xSel = pnt.x;
      ySel = pnt.y;
    } else {
      xSel = -1;
      ySel = -1;
    }

    if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
      if (isDragging) {
        highlightCell = false;
        theModel.moveObject(objDragged, xSel, ySel); 
        repaint();
      }
    } else {
      isDragging = false;
    }

    if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
      highlightCell = false;
      theGBE.checkButtons();
      repaint();
    }

    if (e.getModifiers() == InputEvent.BUTTON2_MASK) {
      if ((xSel > -1) && (ySel > -1)) {
        theModel.deleteGamePiece(xSel, ySel);
        repaint();
      }
    }

  }

  public void mouseMoved(MouseEvent e) {

  }
 
  public boolean getHighlighted() {
    return highlightCell;
  }

  public void clearSelectedCell() {
    xSel = -1;
    ySel = -1;
  }

  public Point getSelectedCell() {
    return new Point(xSel, ySel);
  }


}

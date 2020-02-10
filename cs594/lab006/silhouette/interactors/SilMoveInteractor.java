package silhouette.interactors;

import java.awt.*;
import java.awt.Event;
import java.awt.event.*;
import silhouette.shapes.*;

/**
 * An interactor that moves an object around the screen
 **/
public class SilMoveInteractor extends SilInteractor {
  BoxShape objToMove; 
  double xOffset;
  double yOffset;

  public SilMoveInteractor () {
    super(new SilMouseEvent(MouseEvent.MOUSE_PRESSED),
      new SilMouseEvent(MouseEvent.MOUSE_RELEASED));
  }

  public SilMoveInteractor (SilInputEvent startEvent, 
    SilInputEvent stopEvent) {
    super(startEvent, stopEvent);
  }

  public void startAction(InputEvent e) {
    MouseEvent event = (MouseEvent)e;
    Object source = getSelectedObject();
    if (source instanceof BoxShape) {
      objToMove = (BoxShape)getSelectedObject();
      xOffset = event.getX() - objToMove.getLeft();
      yOffset = event.getY() - objToMove.getTop();
    }
  }
  public void stopAction(InputEvent e) {
    MouseEvent event = (MouseEvent)e;
    objToMove.setLeft(event.getX() - xOffset);
    objToMove.setTop(event.getY() - yOffset);
  }
  public void runningAction(InputEvent e) {
    MouseEvent event = (MouseEvent)e;
    objToMove.setLeft(event.getX() - xOffset);
    objToMove.setTop(event.getY() - yOffset);
  }
}

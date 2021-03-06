package silhouette.interactors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Stack;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import silhouette.shapes.*;

/**************************************************************************
 *
 * The SilEventHandler class distributes Java events to the
 * Silhouette Interactors. If an interactor is already running, then
 * any event is passed to that interactor. If an interactor is not
 * running, then the event handler gets a stack of objects that
 * contain the event. The topmost object on the stack will be the leaf
 * object that contains the event. The next object on the stack will
 * be the composite object that contains the leaf object and so on. The
 * bottommost object on the stack will be the canvas that contains the
 * event. The event handler runs through the interactor list of
 * each object on the stack and gives each interactor the chance to
 * handle the event. If an interactor handles the event, the event
 * handler makes the interactor be the active interactor and returns.
 * An interactor indicates that it has handled the event by changing
 * its state to something other than START. 
 * The interactor will then receive all events until it enters the
 * STOP state. 
 *
 *************************************************************************/

public class SilEventHandler {

  /**
   * Stack of shapes that contain an event. This stack could be declared
   * locally but then it would be constantly allocated and de-allocated.
   * By declaring it as an instance variable we prevent constant allocations
   * and de-allocations.
   */
  protected Stack foundShapes = new Stack();

  /**
   * The interactor that is currently receiving events
   */
  protected SilInteractor ActiveInteractor = null;

  /**
   * The hash table that contains each object's list of interactors
   */
  static protected Hashtable interactorTable = new Hashtable();

  /**
   * Saves the last mouse position so that a key event can determine
   * where the mouse is
   */
  protected int mouseX;
  protected int mouseY;

  /** 
   * The Silhouette panel that this interactor acts on
   */
  SilPanel panel;

  /**
   * Add event listeners to the canvas that call the silhouette interactor
   * handler.
   * 
   */
  public SilEventHandler(CanvasShape c) {
    panel = c.getSilPanel();
    SilMouseEventListener mouseHandler = new SilMouseEventListener(this);
    panel.addMouseListener(mouseHandler);
    panel.addMouseMotionListener(mouseHandler);
    panel.addKeyListener(new SilKeyEventListener(this));
  }

  /**
   * Add an interactor to an object's interactor list
   */
  static public void addInteractor(Object object, SilInteractor interactor) {
    LinkedList interactorList = (LinkedList)interactorTable.get(object);
    if (interactorList == null) {
      interactorList = new LinkedList();
      interactorTable.put(object, interactorList);
    }
    interactorList.addLast(interactor);
  }

  /**
   * Remove an interactor from an object's interactor list
   */
  static public void removeInteractor(Object object, SilInteractor interactor)
  {
    LinkedList interactorList = (LinkedList)interactorTable.get(object);
    if (interactorList != null) {
      interactorList.remove(interactor);
    }
  }

  /**
   * Select an interactor to handle the given event
   */
  public void selectSilInteractor(InputEvent event) {

    // if the event is a mouse event, save the mouse's (x,y) position
    // so that a subsequent key event can access this position
    if (event instanceof MouseEvent) {
      MouseEvent e = (MouseEvent)event;
      mouseX = e.getX();
      mouseY = e.getY();
    }

    // if an interactor is currently receiving events, send the event to
    // that interactor. In order to send the event to the interactor, it
    // is necessary to go through the interactor's list of event handlers
    // and find the event handler that will handle the event. Once the
    // event is handled, check the active interactor's state. If the
    // stop state has been reached, set the ActiveInteractor variable to
    // null and reset the interactor's state to start.
    if (ActiveInteractor != null) {
      ActiveInteractor.setXY(mouseX, mouseY);
      ActiveInteractor.handleEvent(event);
      if (ActiveInteractor.getState() == SilInteractor.STOP) {
        ActiveInteractor.setState(SilInteractor.START);
        ActiveInteractor = null;
      }
    }
    // if there is not an interactor currently receiving an event, try to
    // find an interactor that will handle the event. If the event is a mouse
    // motion event we just
    // ignore it because: 1) an interactor generally does not start with
    // a mouse motion event, and 2) so many mouse motion events get
    // generated that a lot of time would be wasted checking to see whether
    // an interactor should start on a mouse motion event
    else if ((event.getID() == MouseEvent.MOUSE_MOVED)
      || (event.getID() == MouseEvent.MOUSE_DRAGGED))
      return;
    else {
      SilInteractor candidateInteractor;
      boolean eventHandled = false;

      // Get a stack of shapes that contain the event.
      foundShapes.removeAllElements();
      panel.getCanvas().findShapes(mouseX, mouseY, foundShapes);

      // Starting with the topmost shape, examine each shape's interactor
      // list to determine if it has an interactor that handles the event. If
      // such an interactor is found, send the event to the interactor. If
      // the interactor immediately stops, reset the interactor's state to
      // start. However, if the interactor wants to continue to operate (its
      // state will not equal stop), then make the interactor the
      // ActiveInteractor.
      while (!eventHandled && !foundShapes.empty()) {
        Object candidateObject = foundShapes.pop();
	// System.out.println("candidate shape = " + candidateObject);
        LinkedList interactorList =
          (LinkedList)interactorTable.get(candidateObject);
        if (interactorList != null) {
          Iterator interIter = interactorList.iterator();
          while (!eventHandled && interIter.hasNext()) {
            candidateInteractor = (SilInteractor)interIter.next();

            SilInputEvent startEvent = candidateInteractor.getStartEvent();
            // If the start event is null, then the interactor should
            // override the handleEvent method and handle all events
            // and state setting itself.
            if (startEvent == null) {
              candidateInteractor.setSelectedObject(candidateObject);
              candidateInteractor.setXY(mouseX, mouseY);
              candidateInteractor.handleEvent(event);
              if (candidateInteractor.getState() == SilInteractor.STOP) {
                candidateInteractor.setState(SilInteractor.START);
                eventHandled = true;
              }
              else if (candidateInteractor.getState() 
                != SilInteractor.START) {
                ActiveInteractor = candidateInteractor;
                eventHandled = true;
              }
            }
            else if (candidateInteractor.getStartEvent().equals(event)) {
              eventHandled = true;
              candidateInteractor.setSelectedObject(candidateObject);
              candidateInteractor.setXY(mouseX, mouseY);
              candidateInteractor.handleEvent(event);

              switch (candidateInteractor.getState()) {
                case SilInteractor.STOP:
                  candidateInteractor.setState(SilInteractor.START);
                  break;
                default:
                  ActiveInteractor = candidateInteractor;
                  break;
              }
            }
          }
        }
      }
    }
  }

  /**
   * Tells the event handler to start sending events to the indicated
   * interactor
   */
  public void setActiveInteractor(SilInteractor inter) {
    ActiveInteractor = inter;
  }

  /**
   * A listener that directs all mouse events to the selectSilInteractor
   * method
   */
  class SilMouseEventListener extends MouseInputAdapter {
    SilEventHandler handler;

    SilMouseEventListener(SilEventHandler h) {
      handler = h;
    }

    public void mousePressed (MouseEvent event) {
      handler.selectSilInteractor(event);
    }
    public void mouseReleased (MouseEvent event) {
      handler.selectSilInteractor(event);
    }
    public void mouseClicked (MouseEvent event) {
      handler.selectSilInteractor(event);
    }
    public void mouseMoved(MouseEvent event) {
      handler.selectSilInteractor(event);
    }
    public void mouseDragged(MouseEvent event) {
      handler.selectSilInteractor(event);
    }
  }

  /**
   * A listener that directs all key events to the selectSilInteractor
   * method
   */
  class SilKeyEventListener extends KeyAdapter {
    SilEventHandler handler;

    SilKeyEventListener(SilEventHandler h) {
      handler = h;
    }

    public void keyTyped(KeyEvent event) {
      handler.selectSilInteractor(event);
    }
    public void keyPressed(KeyEvent event) {
      handler.selectSilInteractor(event);
    }
    public void keyReleased(KeyEvent event) {
      handler.selectSilInteractor(event);
    }
  }
}

package silhouette.interactors;

import java.awt.*;
import java.awt.Event;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.Iterator;
import silhouette.shapes.*;

/**
 * An abstract base class for creating responses to input events. An
 * interactor handles a series of related events that are required to
 * implement a behavior, such as a move behavior, a text editing behavior,
 * or a selection behavior. Once an interactor starts running, it receives
 * all input events until its stop event is received.
 * <p>
 * Conceptually an interactor is a finite state machine with three
 * states and a series of transitions between states based on input events.
 * The three states are:
 * <ol>
 * <li> START state: The state that an interactor is in when it is
 *      inactive and waiting for its start event. When the interactor's
 *      start event is received, the interactor executes the startAction
 *      method and then transitions to the RUNNING state.
 * <li> RUNNING state: The state that an interactor is in while it runs.
 *      When the stop event is received, the interactor transitions to
 *      the STOP state and executes the stopAction. When the abort event
 *      is received, the interactor transitions to the STOP state and executes
 *      the abortAction. When any other event is received, the runningAction
 *      method is executed.
 * <li> STOP method: The state that an interactor is in when it stops. The
 *      STOP state tells the Silhouette event handler to stop directing events
 *      to this interactor and to re-set the interactor's state to START.
 * </ol>
 * <p>
 * A Silhouette interactor handles events differently than Java's
 * event model. In Java, one implements a behavior using the MouseListener, 
 * MouseMotionListener, and KeyListener interfaces. In Silhouette, one
 * implements a behavior by providing a start and stop event, and then
 * overriding the various action methods. In the action methods, the
 * programmer may need to check the event's id in order to determine what
* type of event has been received. This check is the drawback of the
* interactor method, since in Java one simply overrides the method that
* handles the event. The advantage of the interactor method is three-fold:
* <ol>
* <li> The behavior is divided into its logical components, which makes
*      it easier for someone reading the code to figure out what the
*      behavior accomplishes;
* <li> The Silhouette event-handler implements the "traffic cop" code
*      that determines which interactor should handle the event. In
*      Java's event-handling model, all listeners that are interested
*      in a event will receive the event and they must all have code
*      that ignores the event if some other listener should handle the
*      event instead of themself.
* <li> The Silhouette event-handler allows the programmer to provide
*      a start event and a stop event so that it is easy for someone
*      looking at the code to know when the interactor should start and
*      when it should stop. In Java's event handling model, a programmer
*      has to look through the various listeners to figure out where
*      the start and stop events are implemented.
* </ol>
* <p>
* A silhouette interactor provides a couple of other useful features.
* The Silhouette object that the interactor is 
* operating on can be found in the selectedObject field and the canvas
* that contains the selected object can be retrieved from the source field
* of the event.
*/

abstract public class SilInteractor {

  /**
   * a constant that denotes the interactor's start state
   */
  public static final int START = -1; // start state

  /**
   * a constant that denotes the interactor's stop state
   */
  public static final int STOP = -2;  // stop state

  /**
   * a constant that denotes the interactor's running state
   */
  public static final int RUNNING = -3;  // running state

  /**
   * The interactor's current state
   */
  int state = START;

  /** 
   * The interactor's start event
   */
  SilInputEvent startEvent = null;

  /** 
   * The interactor's stop event
   */
  SilInputEvent stopEvent = null;

  /** 
   * The interactor's abort event
   */
  SilInputEvent abortEvent = null;

  /**
   * The object that the interactor is currently operating on
   */
  Object selectedObject = null;

  /**
   * Stores the last mouse position so that a key event can determine
   * where the mouse is
   */
  protected int mouseX;
  protected int mouseY;

  /** 
   * Constructor that allows the specification of the start and stop
   * events
   */
  public SilInteractor (SilInputEvent start, SilInputEvent stop) {
    startEvent = start;
    stopEvent = stop;
  }

  /**
   * Constructor that allows the specification of the start, stop, and
   * abort events
   */
  public SilInteractor (SilInputEvent start, SilInputEvent stop,
    SilInputEvent abort) {
    startEvent = start;
    stopEvent = stop;
    abortEvent = abort;
  }

  /**
   * Get the interactor's state
   */
  public int getState () {
    return state;
  }

  /**
   * Set the interactor's state
   */
  public SilInteractor setState (int newState) {
    state = newState;
    return this;
  }

  /**
   * Get the selected object
   */
  public Object getSelectedObject() {
    return selectedObject;
  }

  /**
   * Set the selected object
   */
  public SilInteractor setSelectedObject (Object newSelectedObject) {
    selectedObject = newSelectedObject;
    return this;
  }

  /** 
   * Return the x position associated with the current event 
   */
  public int getX() { return mouseX; }

  /**
   * Return the y position associate with the current event
   */
  public int getY() { return mouseY; }

  /**
   * Set the (x,y) position associated with the current event
   */
  void setXY(int x, int y) {
    mouseX = x;
    mouseY = y;
  }

  /**
   * Get the start event
   */
  public SilInputEvent getStartEvent() { return startEvent; }

  /**
   * Set the start event
   */
  public SilInteractor setStartEvent(SilInputEvent e) {
    startEvent = e;
    return this;
  }

  /**
   * Get the stop event
   */
  public SilInputEvent getStopEvent() { return stopEvent; }

  /**
   * Set the stop event
   */
  public SilInteractor setStopEvent(SilInputEvent e) {
    stopEvent = e;
    return this;
  }

  /**
   * Get the abort event
   */
  public SilInputEvent getAbortEvent() { return abortEvent; }

  /**
   * Set the abort event
   */
  public SilInteractor setAbortEvent(SilInputEvent e) {
    abortEvent = e;
    return this;
  }

  /**
   * Start the interactor by placing it in the given state with the
   * given selected object
   */
  public void startInteractor(int beginState, Object targetObject) {
    state = beginState;
    selectedObject = targetObject;
    if (!(selectedObject instanceof SilShape)) {
      System.out.println("***Error--SilInteractor.java: startInteractor was passed an object that was not of type SilShape");
      System.exit(1);
    }
    ((SilShape)selectedObject).getCanvas().getEventHandler().setActiveInteractor (this);
  }

  /**
   * Dispatch the event to the appropriate method in the interactor
   */
  protected void handleEvent(InputEvent event) {
    switch(state) {
      case START:
        // determine if the interactor's start event(s) matches
        // the input event
        if ((startEvent != null) && startEvent.equals(event)) {
          handleEventHelper(event, RUNNING);
          startAction(event);
        }
        break;
      case RUNNING:
        if ((stopEvent != null) && stopEvent.equals(event)) {
          handleEventHelper(event, STOP);
          stopAction(event);
        }
        else if ((abortEvent != null) && abortEvent.equals(event)) {
          handleEventHelper(event, STOP);
          abortAction(event);
        }
        else {
          handleEventHelper(event, RUNNING);
          runningAction(event);
        }
        break;
      default:
        handleEventHelper(event, RUNNING);
        runningAction(event);
        break;
    }   
  }

  /** Translate mouse coordinates to coordinate system of the selected
   *  object's parent
   */
  void translateMouseCoordinates(MouseEvent event) {
    Object obj = getSelectedObject();
    if (obj instanceof CanvasShape)
      return;   // mouse coordinates already correct
    else {
      SilShape selection = (SilShape)obj;
      SilPanel panel = (SilPanel)event.getSource();
      SilShape stoppingObj = (SilShape)panel.getCanvas();
      CompositeShape parent = (CompositeShape)selection.getParent();

      while (parent != stoppingObj) {
        event.translatePoint(-(int)parent.getLeft(),
          -(int)parent.getTop());
        parent = (CompositeShape)parent.getParent();
      }
    }
  }

  /** 
   * Helps out SilEventHandler by performing some necessary bookkeeping
   * before an action method is called
   */
  void handleEventHelper(InputEvent event, int newState) {
    // translate mouse coordinates to coordinate system of the 
    // selected object's parent
    if (event instanceof MouseEvent)
      translateMouseCoordinates((MouseEvent)event);

    state = newState;
  }

  /** 
   * Actions that the interactor performs when it starts executing
   */
  public void startAction(InputEvent event) {}

  /** 
   * Actions that the interactor performs while it is running
   */
  public void runningAction(InputEvent event) {}

  /** 
   * Actions that the interactor performs when it stops executing
   */
  public void stopAction(InputEvent event) {}

  /** 
   * Actions that the interactor performs when its execution is aborted
   */
  public void abortAction(InputEvent event) {}
}


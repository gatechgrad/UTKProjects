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
 * An abstract base class for creating Silhouette interactors that
 * use the Java event model rather than the Silhouette interactor
 * model. Specifically, a SilJavaInteractor allows the user to
 * write methods defined by the Mouse, MouseMotion, and KeyListener
 * interfaces. If the programmer uses a SilJavaInteractor, then the
 * user is responsible for explicitly setting the state variable so
 * that Silhouette knows whether or not to dispatch events to this interactor.
 * Specifically, if the interactor receives an event that causes it
 * to start executing, it should set its state to something other than
 * SilInteractor.START. When the interactor receives its stop event,
 * it should set its state to SilInteractor.STOP. 
 */

abstract public class SilJavaInteractor extends SilInteractor 
  implements MouseListener, MouseMotionListener, KeyListener {

  /**
   * Default constructor function does nothing.
   */
    public SilJavaInteractor () {
	super(null, null);
    }

  /**
   * Dispatch the event to the appropriate method in the interactor
   */
  protected void handleEvent(InputEvent event) {
      if (event instanceof MouseEvent) {
	  MouseEvent me = (MouseEvent)event;
	  if (me.getID() == MouseEvent.MOUSE_CLICKED)
	      mouseClicked(me);
	  else if (me.getID() == MouseEvent.MOUSE_PRESSED)
	      mousePressed(me);
	  else if (me.getID() == MouseEvent.MOUSE_RELEASED)
	      mouseReleased(me);
	  else if (me.getID() == MouseEvent.MOUSE_ENTERED)
	      mouseEntered(me);
	  else if (me.getID() == MouseEvent.MOUSE_EXITED)
	      mouseExited(me);
	  else if (me.getID() == MouseEvent.MOUSE_DRAGGED)
	      mouseDragged(me);
	  else if (me.getID() == MouseEvent.MOUSE_MOVED)
      	      mouseMoved(me);
      }
      else if (event instanceof KeyEvent) {
	  KeyEvent ke = (KeyEvent)event;
	  if (ke.getID() == KeyEvent.KEY_TYPED)
	      keyTyped(ke);
	  else if (ke.getID() == KeyEvent.KEY_PRESSED)
	      keyPressed(ke);
	  else if (ke.getID() == KeyEvent.KEY_RELEASED)
	      keyReleased(ke);
      }
  }

  /**
   * Invoked when the mouse has been clicked on a shape
   */
  public void mouseClicked(MouseEvent e) {}

  /**
   * Invoked when a mouse button has been pressed on a shape
   */
  public void mousePressed(MouseEvent e) {}

  /**
   * Invoked when a mouse button has been released on a shape
   */
  public void mouseReleased(MouseEvent e) {}

  /**
   * Invoked when the mouse enters a Silhouette canvas. In the future
   * this method may be extended to handle the case when the mouse enters
   * a shape.
   */
  public void mouseEntered(MouseEvent e) {}

  /**
   * Invoked when the mouse leaves a Silhouette canvas. In the future
   * this method may be extended to handle the case when the mouse leaves
   * a shape.
   */
  public void mouseExited(MouseEvent e) {}

  /**
   * Invoked when a mouse button is pressed on a shape and then dragged
   */
  public void mouseDragged(MouseEvent e) {}

  /**
   * Invoked when a mouse button has been moved on a shape (with no buttons
   * down)
   */
  public void mouseMoved(MouseEvent e) {}

  /**
   * Invoked when a key is typed
   */
  public void keyTyped(KeyEvent e) {}

  /**
   * Invoked when a key is pressed
   */
  public void keyPressed(KeyEvent e) {}

  /**
   * Invoked when a key is released
   */
  public void keyReleased(KeyEvent e) {}
}


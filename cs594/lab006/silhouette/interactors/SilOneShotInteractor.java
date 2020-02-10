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
 * An interactor that should perform an action in response to an event
 * and immediately stop. The user is responsible for providing two
 * pieces of information:
 *
 * <ol>
 * <li> The activation event: This event should be provided with the
 *   constructor and should be a SilInputEvent.
 *
 * <li> The actionPerformed method: This method is meant to be overridden
 *   by the programmer. It takes a single parameter, which is the 
 *   java.awt.InputEvent that caused the interactor to start. The 
 *   actionPerformed method should perform whatever action the user
 *   wishes the interactor to perform.
 */

abstract public class SilOneShotInteractor extends SilInteractor {

  /**
   * Constructor that allows the specification of the start
   * activation event
   */
  public SilOneShotInteractor (SilInputEvent activationEvent) {
    super(activationEvent, null, null);
  }

  /** 
   * Call the user-defined action method and then put the interactor
   * in the stop state
   */
  public void startAction(InputEvent event) {
    actionPerformed(event);
    setState(STOP);
  }

  /**
   * The action that the user wants performed when the interactor
   * is invoked.
   */
  public void actionPerformed(InputEvent event) {}
}


package silhouette.shapes;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import silhouette.inspector.*;
import silhouette.constraints.*;

/**
 * SilPanel extends JPanel to let SilCanvas to be attached to,
 * it itself should be added to componentPane.
 */
public class SilPanel extends JPanel {

  // SilCanvas that attached to this SilPanel
  CanvasShape topCanvas;

  public static final int ApplicationWindow = -1; // default window type

  public static final int DebugWindow = -2;

  public static final int InspectorWindow = -3;

  int windowType = ApplicationWindow;

  boolean firstTime = true;

  public SilPanel(CanvasShape canvas1) {
    super();
    topCanvas = canvas1;
    setPreferredSize(new Dimension((int)topCanvas.getWidth(),
        (int)topCanvas.getHeight()));

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
      ((SilPanel)e.getSource()).requestFocus();
      }
      });

    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
      if (topCanvas != null) {
      topCanvas.panelResize(getSize());
      }
      }
      });
  }

  public CanvasShape getCanvas() {
    return topCanvas;
  }

  /**
   * Draws the shapes that have been registered with the canvas by
   * calling the display manager that has been registered with the
   * canvas. paintComponent first calls its superclass method so that
   * any Swing components that have been added to the canvas get
   * drawn.
   */
  public void paintComponent(Graphics g) {
    //System.out.println("enter silpanel's paintComponent");
    /*    if(this.getWindowType() == ApplicationWindow) {
          System.out.println("Application window call paintComponent");
          }
          else if(this.getWindowType() == DebugWindow) {
          System.out.println("Debug  window call paintComponent");
          }
          else if(this.getWindowType() == InspectorWindow) {
          System.out.println("Inpector  window call paintComponent");
          }*/

    if(getWindowType() == ApplicationWindow) {
      // force evaluation of all constraints on the constraint queue
      if (constraintDebugger.getState() != constraintDebugger.RUNNING){
        if(!constraint.evalQueue.isEmpty()){
	  // if running the first time or the constraint debugger
	  // hasn't been activated, the constraint evaluation process
	  // is still done in the main thread
          if(firstTime || constraintDebugger.cnDebugger == null) {
            firstTime = false;
	    //System.out.println(" done in main thread\n");
            constraint.Evaluate();
          }
	  // otherwise, it's done in another new thread because
	  // it may be suspended.
          else {
            if(!constraintDebugger.panelList.contains(this)) {
              constraintDebugger.panelList.addLast(this);
            }	
            Thread eval = new Thread() {
              public void run() {
                constraint.Evaluate();

		// update all the existed inspector window
		Inspector.update();
	      }
            };
            eval.start();

            super.paintComponent(g);
            //this.removeAll();
            return;
          }   
        }
      }
      else {
        if(!constraintDebugger.panelList.contains(this)) {
          constraintDebugger.panelList.addLast(this);
          //return;   // made object dynamic 
        }
        super.paintComponent(g);
        return;
      }
    }	

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;
    // save the latest version of the font render context
    TextShape.setFontStats(g2.getFontRenderContext());

    if ((topCanvas.getWidth() != super.getWidth()) ||
      (topCanvas.getHeight() != super.getHeight()) ){
      setPreferredSize(new Dimension((int)topCanvas.getWidth(),
          (int)topCanvas.getHeight()));
    }

    if (isVisible())
      topCanvas.getDisplay().display(g2);


  }

  public void setWindowType(int type) {
    windowType = type;
  }

  public int getWindowType() {
    return windowType;
  }	

}

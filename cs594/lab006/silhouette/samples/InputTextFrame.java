package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;
import silhouette.interactors.*;
import java.awt.geom.*;

/**
 * This application echos keypresses over rectangles and prints
 * the name of the rectangle that the keypress occurs over.
 */
public class InputTextFrame extends JFrame {

  private CanvasShape canvas;

  public InputTextFrame() {
    canvas = new CanvasShape();
    SilShape r;

    SilInteractor keyInter 
	= new SilOneShotInteractor(new SilKeyEvent(KeyEvent.KEY_PRESSED)) {

      // Echo all key presses
      public void actionPerformed(InputEvent e) {
	  KeyEvent event = (KeyEvent)e;
	  RectangleShape selectedRect 
	      = (RectangleShape)this.getSelectedObject();
	  System.out.println("character = " + event.getKeyChar());
	  System.out.println(selectedRect.getName());
      }
    };

    r = new RectangleShape(10, 10, 50, 50);
    r.setName("Rectangle10");
    r.addInteractor (keyInter);
    canvas.add(r);

    r = new RectangleShape(150, 100, 50, 50);
    r.setName("Rectangle150");
    r.addInteractor(keyInter);
    canvas.add(r);

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("InputTextFrame");
    canvas.setFillColor(Color.white);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
	      dispose();
	      System.exit(0);
      }
    });
  }

  public static void main(String argv[]) {

    InputTextFrame mywindow = new InputTextFrame();

    mywindow.pack();
    mywindow.show();
  }

}

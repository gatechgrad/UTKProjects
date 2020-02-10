/**
 * MovingLabelBox.java
 * this program demostrate a labeledBox, you can use mouse to move it.
 * the main purpose is to test the scriping language
 **/

package silhouette.samples.MovingLabelBox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import silhouette.shapes.*;

public class MovingLabelBox extends JFrame {
  private LabelBoxCanvas canvas;

  // constructor
  public MovingLabelBox() {
    canvas = new LabelBoxCanvas();

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("Moving Label Box");
    setLocation(100, 100);
    addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  // main function
  public static void main( String argv[]) {
    MovingLabelBox movingLabelBox = new MovingLabelBox();

    movingLabelBox.pack();
    movingLabelBox.show();
  }
}

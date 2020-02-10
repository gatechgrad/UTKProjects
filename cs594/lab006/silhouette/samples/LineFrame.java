package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;
import silhouette.interactors.*;
import java.awt.geom.*;

/**
 * This application records how many times the mouse cursor hits a
 * line
 */
public class LineFrame extends JFrame {

  private final CanvasShape lineCanvas = new CanvasShape();
  private final CanvasShape textCanvas = new CanvasShape();
  JFrame textFrame = new JFrame();

  public LineFrame() {

    lineCanvas.addInteractor(
       new SilOneShotInteractor(new SilMouseEvent(MouseEvent.MOUSE_CLICKED))
	   {
	       int hitCount = 0;
	       int missCount = 0;

	       // Determine whether a mouse press lies near the line. If it 
	       // does, increment the hit count; otherwise increment the miss 
	       // count.

	       public void actionPerformed(InputEvent e) {
		   MouseEvent event = (MouseEvent)e;
		   LineShape line = (LineShape)lineCanvas.getShape("Linebox");
		   if (line.contains(event.getX(), event.getY())) {
		       TextShape text 
			   = (TextShape)textCanvas.getShape("Hitbox");
		       hitCount++;

		       // update the hit count label
		       text.setText(String.valueOf(hitCount));
		   }
		   else {
		       TextShape text 
			   = (TextShape)textCanvas.getShape("Missbox");
		       missCount++;

		       // update the miss count label
		       text.setText(String.valueOf(missCount));
		   }
	       }
	   }
    );

    getContentPane().add (lineCanvas.getSilPanel());
    this.setTitle("LineFrame");
    lineCanvas.setFillColor(Color.white);

    // create the line
    LineShape linebox = new LineShape();

    lineCanvas.add(linebox, "Linebox");
    linebox.setP1(new Point2D.Double(10, 60))
      .setP2(new Point2D.Double(50,100));

    linebox.setLineColor(Color.red);

    // create the labels for the hit count
    TextShape label = new TextShape(10, 10, "Hit Count: ");
    textCanvas.add(label, "Labelbox");

    // position the hit counter to the right of its label
    TextShape hitLabel = new TextShape(0, 10, "0");
    hitLabel.setLeft(label.getLeft() + label.getWidth() + 10);
    textCanvas.add(hitLabel, "Hitbox");

    // create the labels for the miss count
    label = new TextShape(10, 30, "Miss Count: ");
    textCanvas.add(label, "MissLabelbox");

    // position the miss counter to the right of its label
    TextShape missLabel = new TextShape(0, 30, "0");
    missLabel.setLeft(label.getLeft() + label.getWidth() + 10);
    textCanvas.add(missLabel, "Missbox");

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
	      dispose();
	      System.exit(0);
      }
    });

    textFrame.getContentPane().add(textCanvas.getSilPanel());
    textFrame.setTitle("TextFrame");
    textCanvas.setFillColor(Color.white);
    textFrame.setLocation(450, 0);
    textFrame.pack();
    textFrame.show();
  }

  public static void main(String argv[]) {

    LineFrame mywindow = new LineFrame();

    mywindow.pack();
    mywindow.show();
  }

}

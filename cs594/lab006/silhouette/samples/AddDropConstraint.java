package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;

/**
 * This sample shows how to create editable text objects and how to
 * use constraints to align objects.
 */
public class AddDropConstraint extends JFrame {

  private CanvasShape canvas;

  EditableTextShape textbox;

  public AddDropConstraint() {
    canvas = new CanvasShape();

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("AddDropConstraint");

    // create a blue colored text string that is set in a white box
    textbox = new EditableTextShape(20, 100, "Add (<--) / Drop (-->) A Constraint");
    textbox.setFillColor(Color.white);
    textbox.setFilled(true);
    textbox.setLineColor(Color.blue);
    canvas.add(textbox);

    // create a rectangle that is positioned 40 pixels after the textbox
    RectangleShape rs1 = new RectangleShape(330, 100, 10, 10);
    canvas.add(rs1);


    constraint cn = new constraint () {

        public void formula(constrainedObject owner) {

	    RectangleShape self = (RectangleShape)owner;
	    CompositeShape parent = (CompositeShape)self.getParent();

	    if(textbox.getWidth() > 290) {

	        self.setLeft( textbox.getRight() + 30 );
		self.setFillColor(Color.blue);

	    }

	}

    };

    constraint cn1 = new constraint () {

        public void formula(constrainedObject owner) {

	    RectangleShape self = (RectangleShape)owner;
	    CompositeShape parent = (CompositeShape)self.getParent();

	    if(textbox.getWidth() < 290) {

	        self.setLeft( textbox.getRight() + 30 );
		self.setFillColor(Color.green);

	    }

	}

    };

    rs1.addConstraint(cn);
    rs1.addConstraint(cn1);


    addWindowListener(new WindowAdapter()  {
      public void windowClosing(WindowEvent event) {
	dispose();
	System.exit(0);
      }
    } );
  }

  public static void main(String argv[]) {

    AddDropConstraint mywindow = new AddDropConstraint();

    mywindow.pack();
    mywindow.show();
  }

}

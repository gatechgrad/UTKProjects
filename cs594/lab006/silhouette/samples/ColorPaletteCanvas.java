package silhouette.samples;

import silhouette.shapes.*;
import silhouette.interactors.*;
import java.awt.*;
import java.awt.event.*;

public class ColorPaletteCanvas extends CanvasShape {

  private RectangleShape forebox;

  private RectangleShape tempbox;

  public ColorPaletteCanvas() {
    super( );

    // select and move a color chip
    SilInteractor moveObject = new SilInteractor (new SilMouseEvent(MouseEvent.MOUSE_PRESSED),
						  new SilMouseEvent(MouseEvent.MOUSE_RELEASED)) {
	    ColorPaletteCanvas canvas;

      public void startAction(InputEvent e) {
	  MouseEvent event = (MouseEvent)e;
	  canvas = (ColorPaletteCanvas)((SilPanel)event.getSource()).getCanvas();
	  canvas.setColorSelection((BoxShape)this.getSelectedObject());
      }
      public void stopAction(InputEvent e) {
	  MouseEvent event = (MouseEvent)e;
	  BoxShape targetObject = (BoxShape)this.getSelectedObject();
	  targetObject.setLeft(event.getX());
	  targetObject.setTop(event.getY());
      }
      public void runningAction(InputEvent e) {
	  if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
	      MouseEvent event = (MouseEvent)e;
	      BoxShape targetObject = (BoxShape)this.getSelectedObject();
	      targetObject.setLeft(event.getX());
	      targetObject.setTop(event.getY());
	  }
      }
    };

    // create the rectangle that shows the color of the currently selected
    // color chip
    forebox = new RectangleShape(10, 10, 20, 20);
    forebox.setFilled(false);

    this.add(forebox, "Foreground", CanvasShape.FeedbackDepth);

    // create the color chips

    tempbox = new RectangleShape( 50, 10, 15, 15);
    tempbox.setFillColor(Color.black);
    tempbox.addInteractor(moveObject);
    this.add(tempbox, "Black");

    tempbox = new RectangleShape( 50, 25, 15, 15);
    tempbox.setFillColor(Color.white);
    tempbox.addInteractor(moveObject);
    this.add(tempbox, "White");

    tempbox = new RectangleShape( 65, 10, 15, 15);
    tempbox.setFillColor(Color.blue);
    tempbox.addInteractor(moveObject);
    this.add(tempbox, "Blue");

    tempbox = new RectangleShape( 65, 25, 15, 15);
    tempbox.setFillColor(Color.green);
    tempbox.addInteractor(moveObject);
    this.add(tempbox, "Green");

    // create a composite color chip that has two colors--a yellow chip
    // and a gray chip
    CompositeShape compbox = new CompositeShape();
    compbox.setLeft(50);
    compbox.setTop(40);
    compbox.setWidth(30);
    compbox.setHeight(15);
    compbox.setFillColor(Color.yellow);
    compbox.addInteractor(moveObject);

    tempbox = new RectangleShape(0, 0, 15, 15);
    tempbox.setFillColor(Color.yellow);
    compbox.add(tempbox);

    tempbox = new RectangleShape(15, 0, 15, 15);
    tempbox.setFillColor(Color.gray);
    compbox.add(tempbox);

    this.add(compbox);
  }

  // change the color of the feedback rectangle to the color of the
  // selected chip
  public void setColorSelection(BoxShape selectedObject) {
    forebox.setFilled(true);
    forebox.setFillColor(selectedObject.getFillColor());
  }

}

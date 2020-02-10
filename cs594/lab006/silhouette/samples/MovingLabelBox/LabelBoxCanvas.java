/**
 * LabelBoxCanvas.java
 **/

package silhouette.samples.MovingLabelBox;

import java.awt.*;
import java.awt.event.*;
import silhouette.shapes.*;
import silhouette.interactors.*;

public class LabelBoxCanvas extends CanvasShape {
    SilInteractor moveObject;
    SilInteractor createBox;
    SilInteractor createArrow;

  // constructor
  public LabelBoxCanvas() {
    super();

    // interactor, move the box
    moveObject =  new SilInteractor (new SilMouseEvent(MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1_MASK), new SilMouseEvent(MouseEvent.MOUSE_RELEASED)) {
      LabelBoxCanvas canvas;

      public void startAction(InputEvent e) {
        MouseEvent event = (MouseEvent) e;
        canvas = (LabelBoxCanvas) ((SilPanel)event.getSource()).getCanvas();
      }
      public void stopAction(InputEvent e) {
        MouseEvent event = (MouseEvent) e;
        BoxShape targetObject = (BoxShape) this.getSelectedObject();
        targetObject.setLeft(event.getX());
        targetObject.setTop(event.getY());
      }
      public void runningAction(InputEvent e) {
        if(e.getID() == MouseEvent.MOUSE_DRAGGED) {
          MouseEvent event = (MouseEvent) e;
          BoxShape targetObject = (BoxShape) this.getSelectedObject();
          targetObject.setLeft(event.getX());
          targetObject.setTop(event.getY());
        }
      }

    };

    createBox = new SilOneShotInteractor(new SilMouseEvent(MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON3_MASK)){
	    int count = 0;
	    public void actionPerformed(InputEvent e) {
		MouseEvent me = (MouseEvent)e;
		LabelBox newBox = new LabelBox("label" + count,
					       me.getX(), me.getY());
		newBox.setFilled(false);
		newBox.addInteractor(moveObject);

		LabelBoxCanvas canvas = (LabelBoxCanvas) ((SilPanel)e.getSource()).getCanvas();
		canvas.add(newBox, "label" + count);
		count++;
	    }
	};
    this.addInteractor(createBox);

    createArrow = new SilInteractor(new SilMouseEvent(MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON2_MASK), new SilMouseEvent(MouseEvent.MOUSE_RELEASED)) {
      LabelBoxCanvas canvas;
	    LineShape feedbackLine = null;
	    SilShape fromObj = null;
	    SilShape toObj = null;

      public void startAction(InputEvent e) {
        MouseEvent event = (MouseEvent) e;
        canvas = (LabelBoxCanvas) ((SilPanel)event.getSource()).getCanvas();
	fromObj = canvas.find(event.getX(), event.getY());
	if (feedbackLine == null) {
	    feedbackLine = new LineShape();
	    canvas.add(feedbackLine, CanvasShape.FeedbackDepth);
	}
	feedbackLine.setX1(event.getX());
	feedbackLine.setY1(event.getY());
	feedbackLine.setVisible(true);
      }
      public void stopAction(InputEvent e) {
        MouseEvent event = (MouseEvent) e;
	feedbackLine.setVisible(false);
        SilShape toObj = canvas.find(event.getX(), event.getY());
	if ((fromObj != null) && (toObj != null)) {
	    arrowLine newArrow = new arrowLine((BoxShape)fromObj, 
					       (BoxShape)toObj);
	    canvas.add(newArrow, 0.5);
	}
      }
      public void runningAction(InputEvent e) {
        if(e.getID() == MouseEvent.MOUSE_DRAGGED) {
          MouseEvent event = (MouseEvent) e;
          feedbackLine.setX2(event.getX());
          feedbackLine.setY2(event.getY());
        }
      }
	};
    this.addInteractor(createArrow);
  }
}

// Tue Oct 02 16:33:19 EDT 2001
// the following code is automatically generately from the parser

package silhouette.samples.MovingLabelBox;

import silhouette.shapes.*;
import silhouette.constraints.*;

public class LabelBox extends CompositeShape {
	/* declaration of new properties */
	protected objectProperty value = new objectProperty("value", "foo", this);

	/* declaration of the parts */
	protected RectangleShape frame;
	protected TextShape label;

	/* get(set) accessors of new properties */
	String getValue() {
		return  (String) value.getValue();
	}
	SilShape setValue(String newValue) {
		value.setValue(this, newValue);
		return this;
	}


	/* get accessors of the parts */
	protected RectangleShape getFrame() {
		return frame;
	}
	protected TextShape getLabel() {
		return label;
	}

	protected class _frame extends RectangleShape {
		/* initial block */
		{
			// initialize the pre-defined properties
			setLeft(0);
			setTop(0);
			addConstraint( new constraint () {
				public void formula(constrainedObject self) {
					setWidth(  ((LabelBox) getParent()).getWidth());
				}
			});
			addConstraint( new constraint () {
				public void formula(constrainedObject self) {
					setHeight(  ((LabelBox) getParent()).getHeight());
				}
			});

		}	// end of initial block


	}

	protected class _label extends TextShape {
		/* initial block */
		{
			// initialize the pre-defined properties
			setLeft(5);
			setTop(5);
			addConstraint( new constraint () {
				public void formula(constrainedObject self) {
					setText(  ((LabelBox) getParent()).getValue());
				}
			});

		}	// end of initial block


	}

	/* initial block */
	{
		// initialize the pre-defined properties
		setLeft(10);
		setTop(20);
		addConstraint( new constraint () {
			public void formula(constrainedObject self) {
				setWidth( getLabel().getWidth() + 10);
			}
		});
		addConstraint( new constraint () {
			public void formula(constrainedObject self) {
				setHeight( getLabel().getHeight() + 10);
			}
		});


		/* create and add part to the composite shape in initialization block*/
		frame = new _frame();
		add(frame, "frame");
		frame.setParent(this);

		label = new _label();
		add(label, "label");
		label.setParent(this);

	}	// end of initial block

	/* user code provided by user between "begin java" and "end java" statements */

    LabelBox(String v, int l, int t) {
      setValue(v);
      setLeft(l);
      setTop(t);
    }
  

}

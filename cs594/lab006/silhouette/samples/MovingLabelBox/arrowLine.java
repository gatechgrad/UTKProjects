// Tue Oct 23 11:10:14 EDT 2001
// the following code is automatically generately from the parser

package silhouette.samples.MovingLabelBox;

import silhouette.shapes.*;
import silhouette.constraints.*;
import silhouette.shapes.*;

public class arrowLine extends SilArrowLine {
	/* declaration of new properties */
	protected objectProperty fromObj = new objectProperty("fromObj", null, this);
	protected objectProperty toObj = new objectProperty("toObj", null, this);

	/* get(set) accessors of new properties */
	BoxShape getFromObj() {
		return  (BoxShape) fromObj.getValue();
	}
	SilShape setFromObj(BoxShape newFromObj) {
		fromObj.setValue(this, newFromObj);
		return this;
	}

	BoxShape getToObj() {
		return  (BoxShape) toObj.getValue();
	}
	SilShape setToObj(BoxShape newToObj) {
		toObj.setValue(this, newToObj);
		return this;
	}


	/* initial block */
	{

		addConstraint(new  attachY2ToCenter(toObj));

	}	// end of initial block

	/* user code provided by user between "begin java" and "end java" statements */

    public arrowLine(BoxShape from, BoxShape to) {
      setFromObj(from);
      setToObj(to);
		// initialize the pre-defined properties
		addConstraint(new  attachX1ToRight(getFromObj()));
		addConstraint(new  attachY1ToCenter(getFromObj()));
		addConstraint(new  attachX2ToLeft(getToObj()));

    }
  

}

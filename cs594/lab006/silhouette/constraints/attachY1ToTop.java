package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach Y1 to the top of the reference object.
 */

public class attachY1ToTop extends simpleLineAttachConstraint {

    /**
     * Attach Y1 to the top of the reference object
     **/
    public attachY1ToTop(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY1(referenceObj.getTop());
    }

    public String toString () {
	return "constraint attachY1ToTop";
    }

}

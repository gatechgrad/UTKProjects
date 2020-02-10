package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach Y1 to the center of the reference object.
 */

public class attachY1ToCenter extends simpleLineAttachConstraint {

    /**
     * Attach Y1 to the center of the reference object
     **/
    public attachY1ToCenter(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY1(referenceObj.getCenterY());
    }

    public String toString () {
	return "constraint attachY1ToCenter";
    }

}

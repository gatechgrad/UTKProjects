package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach Y2 to the center of the reference object.
 */

public class attachY2ToCenter extends simpleLineAttachConstraint {

    /**
     * Attach Y2 to the center of the reference object
     **/
    public attachY2ToCenter(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY2(referenceObj.getCenterY());
    }

    public String toString () {
	return "constraint attachY2ToCenter";
    }

}

package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach Y2 to the top of the reference object.
 */

public class attachY2ToTop extends simpleLineAttachConstraint {

    /**
     * Attach Y2 to the top of the reference object
     **/
    public attachY2ToTop(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY2(referenceObj.getTop());
    }

    public String toString () {
	return "constraint attachY2ToTop";
    }

}

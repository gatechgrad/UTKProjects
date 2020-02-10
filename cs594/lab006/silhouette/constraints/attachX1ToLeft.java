package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach X1 to the left side of the reference object.
 */

public class attachX1ToLeft extends simpleLineAttachConstraint {

    /**
     * Attach X1 to the left side of the reference object
     **/
    public attachX1ToLeft(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX1(referenceObj.getLeft());
    }

    public String toString () {
	return "constraint attachX1ToLeft";
    }

}

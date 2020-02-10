package silhouette.constraints;

import silhouette.shapes.*;

/**
 * attach X2 to the left side of the reference object.
 */

public class attachX2ToLeft extends simpleLineAttachConstraint {

    /**
     * Attach X2 to the left side of the reference object
     **/
    public attachX2ToLeft(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX2(referenceObj.getLeft());
    }

    public String toString () {
	return "constraint attachX2ToLeft";
    }

}

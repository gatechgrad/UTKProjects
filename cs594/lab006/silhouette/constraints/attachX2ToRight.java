package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach X2 to the right side of the reference object. 
 */

public class attachX2ToRight extends simpleLineAttachConstraint {

    /** 
     * Attach X2 to the right side of the reference object
     **/
    public attachX2ToRight(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX2(referenceObj.getRight());
    }

    public String toString () {
	return "constraint attachX2ToRight";
    }

}

package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach X1 to the right side of the reference object. 
 */

public class attachX1ToRight extends simpleLineAttachConstraint {

    /** 
     * Attach X1 to the right side of the reference object
     **/
    public attachX1ToRight(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX1(referenceObj.getRight());
    }

    public String toString () {
	return "constraint attachX1ToRight";
    }

}

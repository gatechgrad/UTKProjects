package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach X1 to the center of the reference object. 
 */

public class attachX1ToCenter extends simpleLineAttachConstraint {

    /** 
     * Attach X1 to the center of the reference object
     **/
    public attachX1ToCenter(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX1(referenceObj.getCenterX());
    }

    public String toString () {
	return "constraint attachX1ToCenter";
    }

}

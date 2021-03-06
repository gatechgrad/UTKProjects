package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach X2 to the center of the reference object. 
 */

public class attachX2ToCenter extends simpleLineAttachConstraint {

    /** 
     * Attach X2 to the center of the reference object
     **/
    public attachX2ToCenter(Object obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setX2(referenceObj.getCenterX());
    }

    public String toString () {
	return "constraint attachX2ToCenter";
    }

}

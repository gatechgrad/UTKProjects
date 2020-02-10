package silhouette.constraints;

import silhouette.shapes.*;

/** 
 * attach Y2 to the bottom of the reference object. 
 */

public class attachY2ToBottom extends simpleLineAttachConstraint {

    /** 
     * Attach Y2 to the bottom of the reference object
     **/
    public attachY2ToBottom(SilShape obj) {
	super(obj);
    }

    public void formula(constrainedObject self) {
	if (referenceProperty != null) 
	    referenceObj = (SilShape)referenceProperty.getValue();
	((LineShape)self).setY2(referenceObj.getBottom());
    }

    public String toString () {
	return "constraint attachY2ToBottom";
    }

}


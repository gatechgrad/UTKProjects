package silhouette.constraints;

import java.awt.geom.*;

import silhouette.shapes.*;
import silhouette.globals;

/** 
 * Attaches the endpoint to one of the eight compass points on
 * the attaching object. The reference object is used to determine which
 * compass point would look most pleasing. If the attach object is smaller
 * than the reference object, then the constraint tries 
 * to attach the endpoint to the corner of the attaching object unless the 
 * leading edge of the attaching object is near the center of the reference 
 * object.
 *
 * If the attach object is bigger than the reference object, then the
 * endpoint is attached to the corner of the attaching object if the
 * attaching object does not overlap the reference object; otherwise the
 * endpoint is attached to the center of the attaching object (the endpoint
 * is also attached to the center of the attaching object if the two objects
 * are about to overlap--moving the attach point just before the objects
 * overlap prevents the line between the two objects from ever being vertical
 * or horizontal, which looks ugly). 
 *
 * If the attaching object actually intersects the reference object, then
 * the line may be attached to the actual center of the attaching object,
 * rather than one of the eight compass points. 
 */

abstract class attachPtToNearestCompassPt extends constraint {

    protected SilShape referenceObj;
    protected SilShape attachObj;

    /** 
     * In order to calculate where the line intersects the reference
     * object, we need to know the object from which the line originates
     **/
    attachPtToNearestCompassPt(Object aObj, Object rObj) {
	    attachObj = (SilShape)aObj;
	    referenceObj = (SilShape)rObj;
    }

    int getXPoint(boolean attachObjIsSmallest) {
	double verticalDistance = 0;
	
	// determine the vertical distance between the two objects
	if (attachObj.getBottom() < referenceObj.getTop())
	    verticalDistance = referenceObj.getTop() - attachObj.getBottom();
	else if (attachObj.getTop() > referenceObj.getBottom())
	    verticalDistance = attachObj.getTop() - referenceObj.getBottom();

	// if the width of the attaching object is less than the width
	// of the reference object, then the line should stay attached
	// to either end of the attaching object as long as the angle between
	// the leading edge of the attaching object and the center of the
	// reference object is 65 degrees or less
	if (attachObjIsSmallest) {
	    if ((attachObj.getRight() + (verticalDistance / 2))
		< referenceObj.getCenterX()) 
		return globals.RightSide;
	    else if ((attachObj.getLeft() - (verticalDistance / 2))
		     > referenceObj.getCenterX())
		return globals.LeftSide;
	    else
		return globals.CenterX;
	}
	// if the width of the attaching object is greater than the width
	// of the reference object, then the line should stay attached
	// to either end of the attaching object as long as the angle between
	// the leading edge of the attaching object and the leading edge of the
	// reference object is 65 degrees or less
	else {
	    if ((attachObj.getRight() + (verticalDistance / 2))
		< referenceObj.getLeft())
		return globals.RightSide;
	    else if ((attachObj.getLeft() - (verticalDistance / 2))
		     > referenceObj.getRight())
		return globals.LeftSide;
	    else
		return globals.CenterX;
	}
    }

    int getYPoint(boolean attachObjIsSmallest) {
	double horizontalDistance = 0;
	
	// determine the horizontal distance between the two objects
	if (attachObj.getRight() < referenceObj.getLeft()) {
	    horizontalDistance = referenceObj.getLeft() - attachObj.getRight();
	}
	else if (attachObj.getLeft() > referenceObj.getRight())
	    horizontalDistance = attachObj.getLeft() - referenceObj.getRight();

	// if the height of the attaching object is less than the height
	// of the reference object, then the line should stay attached
	// to either end of the attaching object as long as the angle between
	// the leading edge of the attaching object and the center of the
	// reference object is 65 degrees or less
	if (attachObjIsSmallest) {
	    if ((attachObj.getBottom() + (horizontalDistance / 2))
		< referenceObj.getCenterY()) 
		return globals.BottomSide;
	    else if ((attachObj.getTop() - (horizontalDistance / 2))
		     > referenceObj.getCenterY())
		return globals.TopSide;
	    else
		return globals.CenterY;
	}
	// if the height of the attaching object is greater than the height
	// of the reference object, then the line should stay attached
	// to either end of the attaching object as long as the angle between
	// the leading edge of the attaching object and the leading edge of the
	// reference object is 65 degrees or less
	else {
	    if ((attachObj.getBottom() + (horizontalDistance / 2))
		< referenceObj.getTop())
		return globals.BottomSide;
	    else if ((attachObj.getTop() - (horizontalDistance / 2))
		     > referenceObj.getBottom())
		return globals.TopSide;
	    else
		return globals.CenterY;
	}
    }

    public int getCompassPt(int horizontalSide, int verticalSide) {
	if (horizontalSide == globals.LeftSide) {
	    if (verticalSide == globals.TopSide)
		return globals.NorthWest;
	    else if (verticalSide == globals.CenterY)
		return globals.West;
	    else
		return globals.SouthWest;
	}
	if (horizontalSide == globals.CenterX) {
	    if (verticalSide == globals.TopSide)
		return globals.North;
	    else if (verticalSide == globals.CenterY)
		return globals.Center;
	    else
		return globals.South;
	}
	else { // globals.TopSide
	    if (verticalSide == globals.TopSide)
		return globals.NorthEast;
	    else if (verticalSide == globals.CenterY)
		return globals.East;
	    else
		return globals.SouthEast;
	}
    }
}

package silhouette.inspector;

import silhouette.shapes.TextShape;
import silhouette.shapes.SilShape;

    /**
     * A class that can display the name of a silhouette object, and, if
     * the user double clicks on the name, display the object's properties
     * in an inspector window.
     */
    public class ObjectText extends TextShape {
	protected SilShape objectToDisplay;

	public ObjectText(int left, int top, String text, SilShape obj,
			  Inspector.InspectorWindow inspectorObj) {
	    super(left, top, text);
	    objectToDisplay = obj;
	    addInteractor(inspectorObj.getNewObjectBehavior());
	}

	public SilShape getObjectToDisplay() { return objectToDisplay; }
    }

package silhouette.constraints;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;

/**
 * Provides a constrained boolean property
 */
public class booleanProperty extends property {
  /**
   * The default value of the property is true
   */
  boolean value = true;

  /**
   * Constructs and initializes an booleanProperty with the indicated value
   */
  public booleanProperty(String nm, boolean v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public boolean getValue() {
    validate();
    return value;
  }

  /**
   * Returns the owner of this property
   */
  public SilShape getOwner() {
    return owner;
  }

  /**
   * set the property's value to the indicated value. The
   * owner parameter is the object that owns the property.
   */
  public void setValue(constrainedObject owner, boolean newValue) {
    // If a constraint is setting this property, then add this
    // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }

  public String getPropertyType() {
    return "boolean: ";
  }

  public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
    return new EditableBooleanProperty(0, 0, this);
  }

  // inner class for editting the displayed value
  class EditableBooleanProperty extends EditableTextShape {

    // current property
    booleanProperty      self;

    // constructor
    public EditableBooleanProperty (int x, int y, booleanProperty in_property)
    {
      super(x, y, new Boolean(in_property.value).toString());
      self = in_property;

    }

    // when ENTER pressed
    public void stopAction(SilInteractor inter) {
      if(constraintDebugger.getState() != constraintDebugger.STOP) {
                this.myInputInt.abortAction(null);
        }
      else {
	self.setValue((constrainedObject)self.getOwner(),
        (new Boolean(getText())).booleanValue());
      }	
    }
  } // end of inner class

}

package silhouette.constraints;

import silhouette.shapes.*;
import java.lang.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;

/**
 * Provides a constrained string property
 */
public class stringProperty extends property {
  /**
   * The default value of the property is ""
   */
  String value = "";

  /**
   * Constructs and initializes an stringProperty with the indicated value
   */
  public stringProperty(String nm, String v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public String getValue() {
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
  public void setValue(constrainedObject owner, String newValue) {
    // If a constraint is setting this property, then add this
    // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }


  public String getPropertyType() {
    return "java.lang.String: ";
  }

  public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
    return new EditableStringProperty(0, 0, this);
  }

  // inner class for editting the displayed value
  class EditableStringProperty extends EditableTextShape {

    // current property
    stringProperty      self;

    // constructor
    public EditableStringProperty (int x, int y, stringProperty in_property)
    {
      super(x, y, in_property.value);
      self = in_property;

    }

    // when ENTER pressed
    public void stopAction(SilInteractor inter) {
      if(constraintDebugger.getState() != constraintDebugger.STOP) {
                this.myInputInt.abortAction(null);
        }
      else {
	self.setValue((constrainedObject)self.getOwner(),
        getText());
      }
    }
  } // end of inner class

}

package silhouette.constraints;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;

/**
 * Provides a constrained integer property
 */
public class intProperty extends property {
  /**
   * The default value of the property is 0
   */
  int value = 0;

  /**
   * Constructs and initializes an intProperty with the indicated value
   */
  public intProperty(String nm, int v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public int getValue() {

    //if constraint debugger is in process, not to go into evaluation process
    if(constraintDebugger.cnDebugger != null) {
      if(constraintDebugger.getState() != constraintDebugger.RUNNING ||
        constraintDebugger.getOperation() == constraintDebugger.STEP ||
        constraintDebugger.getOperation() == constraintDebugger.NEXT ||
        constraintDebugger.getOperation() == constraintDebugger.CONTINUE) {

        validate();
      }
    }else {
      validate();
    }

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
  public void setValue(constrainedObject owner, int newValue) {
    // If a constraint is setting this property, then add this
    // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }



  public String getPropertyType() {
    return "int: ";
  }

  public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
    return new EditableIntegerProperty(0, 0, this);
  }

  // inner class for editting the displayed value
  class EditableIntegerProperty extends EditableTextShape {

    // current property
    intProperty	self;

    // constructor
    public EditableIntegerProperty (int x, int y, intProperty in_property)
    {
      super(x, y, new Integer(in_property.value).toString());
      self = in_property;
    }

    // when ENTER pressed
    public void stopAction(SilInteractor inter) {
      if(constraintDebugger.getState() != constraintDebugger.STOP) {
            this.myInputInt.abortAction(null);
      }
      else {			      
      	self.setValue((constrainedObject)self.getOwner(),
        (new Integer(getText())).intValue());
      }	
    }
  } // end of inner class

}

package silhouette.constraints;

import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;

/**
 * Provides a constrained double property
 */
public class doubleProperty extends property {

  /**
   * The default value of the property is 0.0
   */
  double value = 0.0;
  /**
   * Constructs and initializes a doubleProperty with the indicated value
   */
  public doubleProperty(String nm, double v, SilShape o) {
    super(nm);
    value = v;
    setOwner(o);
  }

  /**
   * Returns the property's value.
   */
  public double getValue() {
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
  public void setValue(constrainedObject owner, double newValue) {
      // If a constraint is setting this property, then add this
      // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }

    /**
     * set the property's value to the indicated value but do not
     * do anything else
     */
    public void setValueQuickly(double newValue) {
	value = newValue;
    }

    public String getPropertyType() {
	return "double: ";
    }

    public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
	return new EditableDoubleProperty(0, 0, this);
    }

  // inner class for editting the displayed value
  class EditableDoubleProperty extends EditableTextShape {

    // current property
    doubleProperty	self;

    // constructor
      public EditableDoubleProperty (int x, int y, doubleProperty in_property)
    { 
      // here don't use getValue() because we don't like to let it get into
      // constraint evaluation process, and just present the current value,
      // not the updated value
      super(x, y, new Double(in_property.value).toString());
      self = in_property;
    }

    // when ENTER pressed
    public void stopAction(SilInteractor inter) {
        if(constraintDebugger.getState() != constraintDebugger.STOP) {
		this.myInputInt.abortAction(null);
	}
	else {
      		self.setValue((constrainedObject)self.getOwner(),
		      (new Double(getText())).doubleValue());
        }		      
    }
  } // end of inner class


}





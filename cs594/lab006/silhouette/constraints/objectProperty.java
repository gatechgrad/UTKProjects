package silhouette.constraints;

import silhouette.shapes.*;
import java.lang.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;
import silhouette.inspector.ObjectText;

/**
 * Provides a constrained object property
 */
public class objectProperty extends property {
  /**
   * The default value of the property is null
   */
  Object value = null;

  /**
   * Constructs and initializes an objectProperty with the indicated value
   */
  public objectProperty(String nm, Object v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public Object getValue() {
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
  public void setValue(constrainedObject owner, Object newValue) {
    // If a constraint is setting this property, then add this
    // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }

  public String getPropertyType() {
    return "Object: ";
  }

  public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
    if (this.getValue() instanceof SilShape) {
      return new ObjectText(0, 0, this.getValue().toString(),
        (SilShape)this.value, inspectorObj);
    }
    else
      return new TextShape(0, 0, this.getValue().toString());
  }
}

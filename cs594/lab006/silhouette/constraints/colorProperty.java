package silhouette.constraints;

import silhouette.shapes.*;
import java.awt.*;
import silhouette.interactors.*;
import silhouette.inspector.Inspector;

import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
/**
 * Provides a constrained color  property
 */
public class colorProperty extends property {
  /**
   * The default value of the property is Color.black
   */
  Color value = Color.black;

  /**
   * Constructs and initializes an colorProperty with the indicated value
   */
  public colorProperty(String nm, Color v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public Color getValue() {
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
  public void setValue(constrainedObject owner, Color newValue) {
    // If a constraint is setting this property, then add this
    // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }

  public String getPropertyType() {
    return "java.awt.Color: ";
  }

  public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
    // color chip represents the property value
    RectangleShape Crect = new RectangleShape(0, 0, 10, 10);
    Crect.setFillColor(this.value);
    Crect.addInteractor(new ChangeColor(this, Crect));
    return Crect;
  }

  // Change Color data type, a color chooser dialog will show up,
  // whenever the new color has been choosen, the constrained object
  // who own the property will be updated concurrently.
  class ChangeColor extends SilOneShotInteractor {
    colorProperty curColor;

    RectangleShape Crect;     // the representative
    Color	defColor;

    ChangeColor ( colorProperty in_color, RectangleShape in_Crect )
    {
      super(new SilMouseEvent(MouseEvent.MOUSE_CLICKED));
      curColor  = in_color;
      Crect     = in_Crect;
    }

    public void actionPerformed(InputEvent mev) {
      defColor = curColor.getValue();
      CanvasShape curCanvas = ((SilPanel)mev.getSource()).getCanvas();
      //get new color
      Color newColor = JColorChooser.showDialog( curCanvas.getSilPanel(),
        "Choose Color",
        defColor );
      if (newColor != null) {
        curColor.setValue((constrainedObject)curColor.getOwner(), newColor);
        Crect.setFillColor(newColor);
      }
    }
  }

}

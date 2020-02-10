package silhouette.constraints;

import silhouette.shapes.*;
import java.awt.*;
import silhouette.interactors.*;
import java.awt.event.*;
import javax.swing.*;
import silhouette.inspector.Inspector;

/**
 * Provides a constrained font property
 */
public class fontProperty extends property {
  /**
   * The default value of the property is null
   */
  Font value = null;

  /**
   * Constructs and initializes a fontProperty with the indicated value
   */
  public fontProperty(String nm, Font v, SilShape o) {
    super(nm);
    value = v;
    owner = o;
  }

  /**
   * Returns the property's value.
   */
  public Font getValue() {
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
  public void setValue(constrainedObject owner, Font newValue) {
      // If a constraint is setting this property, then add this
      // property to the constraint's output list
    recordOutput(owner);

    if (value != newValue) {
      value = newValue;
      setValueHelper();
    }
  }

    public String getPropertyType() {
	return "java.awt.Font: ";
    }

    public BoxShape displayPropertyValue(Inspector.InspectorWindow inspectorObj) {
	TextShape ts4 = new TextShape(0, 0, this.value.toString());
	ts4.addInteractor(new ChangeFont(this, ts4) );
	return ts4;
  }

 // Change Font data type, a font chooser dialog will show up,
 // whenever the new font has been choosen, the constrained object
 // who own the property will be updated concurrently.
  class ChangeFont extends SilOneShotInteractor {
    fontProperty curFont;
    TextShape   valueText;
    Font	defFont;

    ChangeFont (fontProperty in_font, TextShape in_text)
    {
	super(new SilMouseEvent(MouseEvent.MOUSE_CLICKED));
      curFont = in_font;
      valueText = in_text;
    }

    public void actionPerformed(InputEvent mev) {
      defFont = curFont.getValue();
      CanvasShape curCanvas = ((SilPanel)mev.getSource()).getCanvas();
      Font newFont = FontDialog.showDialog(curCanvas.getSilPanel(), defFont );
      if (newFont!= null) {
        defFont = newFont ;
        curFont.setValue((constrainedObject)curFont.getOwner(), newFont);
        valueText.setText(newFont.toString());
      }
     }
    } // end of inner class



}

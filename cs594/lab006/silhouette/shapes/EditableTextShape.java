package silhouette.shapes;

import java.awt.*;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.font.*;

import silhouette.interactors.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

import java.text.*;
import java.awt.geom.AffineTransform;

import java.util.Vector;

/**************************************************************************
 *
 * Change Log:
 *  10/1/00, Weizhong Ji: Created EditableTextShape
 *  10/8/00, bvz:
 *    1) Changed name of class from EditableText to EditableTextShape
 *    2) Augmented the SilCaret class so that it contains all the state
 *        information required to perform a text edit
 *    3) Created a static variable containing the text edit interactor so
 *        that there is only one interactor for the entire class rather than
 *        one interactor for each instance of the class.
 *    4) Modified the draw routine so that it creates a new TextLayout
 *        object only if the string has been edited. This change was
 *        implemented by adding a boolean variable called textModified.
 *        Any edit that changes the string sets this variable to true.
 *    5) Introduced a StringBuffer object to handle the editing of the
 *        text string. StringBuffer provides an efficient way to handle
 *        the editing of strings.
 *    6) Modified the draw method so that it always calls super.draw() and
 *        then draws the caret if the caret is visible. Calling super.draw()
 *        ensures that the string is rendered with the appropriate color
 *        and background, as well as the appropriate font.
 *    7) Modified the handling of the Enter key so that the caret is made
 *        invisible and the interactor is stopped. This change fixed a bug
 *        in which the caret would never disappear.
 *    8) Modified the mouse handling method so that the interactor's state
 *        is changed to RUNNING. Changing the state to RUNNING tells the
 *        Silhouette event handler to send all key events to the text string
 *        currently being edited. This allows the text string to be edited,
 *        even when the mouse cursor is not over the text string. In the
 *        original version, the text string would not respond to key events
 *        if the mouse cursor was moved outside the string.
*    9) The change in (8) also prevents the user from clicking on and
*        starting to edit another text string while the current text string
*        is being edited. The only way to start editing another text string
*        is to hit the enter key, thus terminating editing of the current
*        text string, and then selecting the other text string.
*   10) Added a new set of constructors so that the constructors for
*        EditableTextShape would be the same as the constructors for
*        TextShape.
*
*  10/15/00, wji:
*    1) Added fields shadowbuffer, shadowtif, shadowtextlayout
*        to fix the bug that happens when deleting the last character in
*        the StringBuffer, which causes an exception to be raised
*        (TextLayout constructed from empty StringBuffer).
*    2) Added one more feature:  "Escape"  keypress to cancel changes.
*
*  10/17/00, bvz:
*    1) Moved the code for "normal" key processing from the keyPressed
*       method to the keyTyped method. The reason for the move is that
*       keyPressed gives you key events for pressing the Shift or Control
*       buttons and causes useless characters to get inserted into the
*       string. keyTyped only seems to get called with "normal" keys.
*    2) Added startAction, runningAction, stopAction, and abortAction
*       methods to EditableTextShape so that the user can control what
*       happens during text editing.
*    3) Simplified the processing that occurs when the edited string
*       becomes empty. In particular, removed the shadowXXX variables
*       and replaced them with a saveText variable that saves the
*       text in case the Escape key is pressed. If the string becomes
*       empty, the previous textLayout object is used and the tif object
*       is set so that the selection index will be 0.
*
*   7/29/01, bvz:
*    1) Changed the interactor code to conform to the new version of
*       interactors.
*
****************************************************************************/

public class EditableTextShape extends TextShape {

  // Holds the information required to do text editing on an EditableTextShape.
  // This information is placed in a class rather than in an EditableTextShape
  // so that only the EditableTextShape currently being edited must store
  // this state information.
  class SilCaret {
    TextLayout    textlayout = null;
    StringBuffer buffer = null;
    TextHitInfo	tif = null;
    String saveText = null;
    boolean CaretRight = false;
    boolean CaretLeft  = false;
    boolean textModified = false;
  };

  // use one edit interactor for all EditableTextShapes
  protected static InputInt myInputInt = null;

  SilCaret caret = null;

  void initialize() {
    if (myInputInt == null)
      myInputInt = new InputInt(new SilMouseEvent(MouseEvent.MOUSE_CLICKED),
        new SilKeyEvent(KeyEvent.KEY_PRESSED,
          KeyEvent.VK_ENTER),
        new SilKeyEvent(KeyEvent.KEY_PRESSED,
          KeyEvent.VK_ESCAPE));
    this.addInteractor(myInputInt);
  }

  /**
   * Constructs a new EditableTextShape object with an empty
   * string and a default font
   */
  public EditableTextShape() {
    super();
    this.initialize();
  }

  /**
   * Constructs a new EditableTextShape object with the specified parameters.
   * Default font is the current system font.
   * @param x_in 	   the x coordinate of the border bounding box
   * @param y_in 	   the y coordinate of the border bounding box
   * @param text_in	   the string to be displayed
   */
  public EditableTextShape(int x_in, int y_in, String text_in) {
    super(x_in, y_in, text_in,
      new Font( "Lucida Sans Typewriter", Font.PLAIN, 12) );
    this.initialize();
  }

  /**
   * Constructs a new EditableTextShape object with the specified parameters.
   * @param x_in 	   the x coordinate of the border bounding box
   * @param y_in 	   the y coordinate of the border bounding box
   * @param text_in	   the string to be displayed
   * @param font_in	   the font in which the string will be drawn
   */

  public EditableTextShape(int x_in, int y_in, String text_in, Font font_in) {
    super(x_in, y_in, text_in, font_in);
    this.initialize();
  }


  // a callback procedure to be overridden when
  // implementing an action to be caused by ENTER
  public void stopAction(SilInteractor inter) {
  }

  // a callback procedure to be overridden when
  // implementing an action to be executed as the interactor is running
  public void runningAction(SilInteractor inter) {
  }

  // a callback procedure to be overridden when
  // implementing an action to be executed as the interactor starts running
  public void startAction(SilInteractor inter) {
  }

  // a callback procedure to be overridden when
  // implementing an action to be executed if the interactor is aborted
  public void abortAction(SilInteractor inter) {
  }

  protected class InputInt extends SilInteractor {
    SilCaret caret = null;
    EditableTextShape obj;   // the object being edited
    SilPanel panel;

    InputInt(SilInputEvent start, SilInputEvent stop, SilInputEvent abort) {
      super(start, stop, abort);
    }

    public void startAction(InputEvent event) {
      MouseEvent me = (MouseEvent)event;
      if (caret == null) {
        obj = (EditableTextShape)getSelectedObject();
        panel = (SilPanel)me.getSource();

        obj.caret = new SilCaret();
        caret = obj.caret;
        String stringToEdit = obj.getText();
        caret.textlayout = new TextLayout ( stringToEdit,
          obj.getFont(),
          obj.fontStats );
        caret.buffer = new StringBuffer(stringToEdit);
        caret.saveText = stringToEdit;

        // we do this so that a new text layout object gets generated
        // with the most recent version of the fontRenderContext
        caret.textModified = true;
        caret.tif = caret.textlayout.hitTestChar(
          me.getX()-(float)obj.getLeft(), me.getY()-(float)obj.getTop());
        obj.startAction(this);

        // we must explicitly call repaint because none of the text object's
        // properties have been modified so the display manager doesn't
        // know that the canvas should be repainted.
        panel.repaint();
      }
      // maybe there should be an 'else' here so that something happens
      // if there is a mouse press and someone is already editing another
      // piece of text
    }

    public void runningAction(InputEvent event) {
      if (event instanceof KeyEvent) {
        KeyEvent ke = (KeyEvent)event;
        if (ke.getID() == KeyEvent.KEY_PRESSED)
          keyPressed(ke);
        else if (ke.getID() == KeyEvent.KEY_TYPED)
          keyTyped(ke);

        obj.runningAction(this);
        // we must explicitly call repaint because some of the
        // text editing operations do not change any of the text object's
        // properties and therefore the display manager may not
        // know that the canvas should be repainted.
        panel.repaint();
      }
    }

    // stop the interactor, make the caret disappear, and call
    // the user's callback procedure
    public void stopAction(InputEvent event) {
      obj.stopAction(this);
      caret = null;
      obj.caret = null;

      // we must explicitly call repaint because none of the text object's
      // properties have been modified so the display manager doesn't
      // know that the canvas should be repainted (the running action
      // modifies the text object's text property but the stop event
      // does not modify it).
      panel.repaint();
    }

    // abort the interactor by restoring the old text
    public void abortAction(InputEvent event) {
      obj.setText(caret.saveText);
      obj.abortAction(this);
      caret = null;
      obj.caret = null;

      // we must explicitly call repaint because none of the text object's
      // properties have been modified so the display manager doesn't
      // know that the canvas should be repainted (the abort action resets
      // the text object's text property but if the property has the same
      // value as before, the display manager will not be notified).
      panel.repaint();
    }

    public void keyPressed(KeyEvent ke) {
      if (caret != null) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
          caret.CaretRight = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
          caret.CaretLeft  = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
          if (caret.tif.getInsertionIndex() != 0) {
            caret.buffer.deleteCharAt(caret.tif.getInsertionIndex()-1);
            obj.setText(new String(caret.buffer));
            caret.CaretLeft = true;
            caret.textModified = true;
          }
        } else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
          if (caret.tif.getInsertionIndex() < caret.buffer.length()) {
            caret.buffer.deleteCharAt(caret.tif.getInsertionIndex());
            obj.setText(new String(caret.buffer));
            caret.textModified = true;
          }
        }
      }
    }

    // handle the typing of "normal" keys
    public void keyTyped(KeyEvent ke) {
      if (caret != null) {
        char inkey = ke.getKeyChar();
        if ((Character.isISOControl(inkey) == false) && !ke.isActionKey()) {
          Character inchar = new Character(inkey);
          caret.buffer.insert(caret.tif.getInsertionIndex(), inchar);
          obj.setText(new String(caret.buffer));
          caret.CaretRight = true;
          caret.textModified = true;
        }
      }
    }
  }

  public void draw (Graphics2D g) {
    int caretPosition = 0;
    if (getVisible()) {
      // draw the object in the appropriate font and color, then draw
      // a caret if necessary
      super.draw(g);
      if (caret != null) {
        if (caret.textModified) {
          if (caret.buffer.length() > 0)
            caret.textlayout
              = new TextLayout(getText(), getFont(), g.getFontRenderContext() );
          else // string is now empty so make caret be at position 0
            caret.tif = caret.textlayout.getNextLeftHit(1);
          caret.textModified = false;
        }
        if (caret.buffer.length() == 0) {
          caret.CaretRight = false;
          caret.CaretLeft = false;
        }
        else if (caret.CaretRight) {
          caret.tif = 
            caret.textlayout.getNextRightHit(caret.tif.getInsertionIndex());
          if (caret.tif == null)
            caret.tif = caret.textlayout.getNextRightHit(
              caret.textlayout.getCharacterCount()-1);
          caret.CaretRight = false;
        }

        else if (caret.CaretLeft) {
          // for case: Backspace from the end
          if ( caret.tif.getInsertionIndex() > 
            caret.textlayout.getCharacterCount() ) 
          {
            caret.tif = caret.textlayout.getNextRightHit(
              caret.textlayout.getCharacterCount()-1);
          } else {
            caret.tif = caret.textlayout.getNextLeftHit(
              caret.tif.getInsertionIndex());

            if (caret.tif == null)
              caret.tif = caret.textlayout.getNextLeftHit(1);
          }
          caret.CaretLeft = false;
        }

        float ascent = getFont().getLineMetrics(
          getText(), fontStats).getAscent();

        //draw caret line
        if (caret.buffer.length() > 0)
          caretPosition = caret.tif.getInsertionIndex();
        else
          caretPosition = 0;
        Shape[] carets = caret.textlayout.getCaretShapes(caretPosition);
        for (int i = 0; i < carets.length; i++) {
          if (carets[i] != null)  {
            AffineTransform at =
              AffineTransform.getTranslateInstance(getLeft(), getTop()+ascent);
            Shape shape = at.createTransformedShape(carets[i]);
            g.setStroke(new BasicStroke());
            g.draw(shape);
          }
        }
      }
      validate();
    }
  } // end of draw()

  /* add fields for reflect usage */

  public static Vector Fields   = new Vector(10);

  static {
    Fields.addElement("Top");
    Fields.addElement("Left");
    Fields.addElement("Height");
    Fields.addElement("Width");
    Fields.addElement("Visible");
    Fields.addElement("LineColor");
    Fields.addElement("FillColor");
    Fields.addElement("Filled");
    Fields.addElement("Font");
    Fields.addElement("Text");
  }

}

package silhouette.interactors;

import java.awt.event.*;

public class SilKeyEvent implements SilInputEvent {
  public static final int ANY_KEY = -1;
  static final int USE_KEY_CHAR = -2;
  public static final int ANY_MODIFIER = -1;

  int eventType;
  int modifiers;
  int keyCode;
  char keyChar;

  public SilKeyEvent(int type) {
    eventType = type;
    modifiers = ANY_MODIFIER;
    keyCode = ANY_KEY;
    keyChar = ' ';
  }

  public SilKeyEvent(int type, int code) {
    eventType = type;
    modifiers = ANY_MODIFIER;
    keyCode = code;
    keyChar = ' ';
  }

  public SilKeyEvent(int type, char key) {
    eventType = type;
    modifiers = ANY_MODIFIER;
    keyCode = USE_KEY_CHAR;
    keyChar = key;
  }

  public SilKeyEvent(int type, int code, int mods) {
    eventType = type;
    modifiers = mods;
    keyCode = code;
    keyChar = ' ';
  }

  public SilKeyEvent(int type, char key, int mods) {
    eventType = type;
    modifiers = mods;
    keyChar = key;
    keyCode = USE_KEY_CHAR;
  }

  public boolean equals(InputEvent event) {
    if (event instanceof KeyEvent) {
      KeyEvent e = (KeyEvent)event;
      if (e.getID() == eventType) {
        if ((modifiers == ANY_MODIFIER) ||
          (e.getModifiers() == modifiers)) {
          if (keyCode == USE_KEY_CHAR)
            return (e.getKeyChar() == keyChar);
          else if (keyCode == ANY_KEY) 
            return true;
          else
            return (e.getKeyCode() == keyCode);
        }
        else 
          return false;
      }
      else
        return false;
    }
    else
      return false;
  }
}

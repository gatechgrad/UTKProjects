package silhouette.interactors;

import java.awt.event.*;

public class SilMouseEvent implements SilInputEvent {
  int eventType;
  int modifiers;
  int clickCount;

  public static int ANY_MODIFIER = -1;
  public static int ANY_CLICK = -1;

  public SilMouseEvent(int type) {
    eventType = type;
    modifiers = ANY_MODIFIER;
    clickCount = ANY_CLICK;
  }

  public SilMouseEvent(int type, int mods) {
    eventType = type;
    modifiers = mods;
    clickCount = ANY_CLICK;
  }

  public SilMouseEvent(int type, int mods, int count) {
    eventType = type;
    modifiers = mods;
    clickCount = count;
  }

  public boolean equals(InputEvent event) {
    if (event instanceof MouseEvent) {
      MouseEvent e = (MouseEvent)event;
      return ((e.getID() == eventType) &&
        ((modifiers == ANY_MODIFIER) || (e.getModifiers() == modifiers)) &&
        ((clickCount == ANY_CLICK) || (e.getClickCount() == clickCount)));
    }
    else
      return false;
  }
}

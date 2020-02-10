package silhouette.constraints;

import silhouette.shapes.*;
import java.util.*;

// an inner class for storing property pairs
public class EdgeElement {
  property p; 
  constraint cn;
  String fromName;
  String toName;
  double fromX = 0;
  double fromY = 0;
  double toX = 0;
  double toY = 0;
  boolean pValid = true; // validity  of property
  boolean fromIsProperty;


  public EdgeElement(property p1, constraint c, boolean b) {
    p = p1;
    cn = c;
    fromIsProperty = b;
    if(fromIsProperty == true) {
      fromName = p1.toString();
      toName = cn.getName();
    }
    else {
      fromName = cn.getName();
      toName = p1.toString();
    }	
  }

  public String   getFromName() { return fromName; }
  public String   getToName() { return toName; }


  public void setFrom(double x, double y) {
    fromX = x;
    fromY = y;
  }

  public void setTo(double x, double y) {
    toX = x;
    toY = y;
  }
} // end of Class EdgeElement


//Title:        Silhouette
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Brad Vander Zanden
//Company:      UTK
//Description:  Keeps track of the zOrder of a set of Silhouette shapes

package silhouette.properties;

import java.util.Vector;
import java.util.Enumeration;
import silhouette.shapes.*;

class zElement {
  /**
   * The z-depth of this set of Silhouette shapes
   */
  double z;

  /**
   * The list of shapes at this z-depth
   */
  Vector shapes = new Vector(10,5);

  public zElement(double zValue) {
    z = zValue;
  }

  public double getZ() {
    return z;
  }

  public void add(SilShape newshape) {
    shapes.add(newshape);
  }

  public void add(SilShape newshape, int index) {
    shapes.insertElementAt(newshape, index);
  }

  public boolean remove(SilShape shape) {
    System.out.println("Removing " + shape.toString());  //debug
    //return shapes.remove(shape);
    return shapes.removeElement(shape);  //debug
  }

  /**
   * Gets the index of the specified Shape.
   */
  public int getShapeIndex(SilShape shape) {
    return shapes.indexOf(shape);
  }

    /**
   * Sends the specified Shape to the back of this z-level
   * @return true if this z-level contains the shape, false otherwise
   */
  public boolean sendToBack(SilShape moveshape) {
    int index;
    index = shapes.indexOf(moveshape);

    if (index >= 0) {
      shapes.removeElementAt(index);
      shapes.insertElementAt(moveshape, 0);
      return true;
    }
    else
      return(false);
  }

  /**
   * Brings the specified Shape to the front of this z-level.
   * @return true if this z-level contains the shape, false otherwise
   */
  public boolean bringToFront(SilShape moveshape) {
    int index;
    index = shapes.indexOf(moveshape);

    if (index >= 0) {
      shapes.removeElementAt(index);
      shapes.addElement(moveshape);
      return true;
    }
    else
      return false;
  }

  /**
   * Gets a reference to the specified Shape.
   * @return A reference to the shape if it is found, null otherwise
   */
  public SilShape getShape(String shapename) {
    int index;
    index = getShapeIndex(shapename);
    if(index > -1) {
      return( (SilShape)shapes.elementAt(index) );
    } else {
      return(null);
    }
  }

  /**
   * Gets the index of the specified Shape.
   * @return The index of the shape if found, -1 otherwise
   */
  protected int getShapeIndex(String shapename) {
    int shape_cnt;
    int i;
    SilShape cur_shape;

    shape_cnt = shapes.size();  
    for(i = 0; i < shape_cnt; i++) {
      cur_shape = (SilShape)shapes.elementAt(i);
      if(cur_shape.getName().equals(shapename)) {
        return(i);
      }
    }
    return(-1);
  }

  /**
   * Removes all elements from the shapes list
   */
  public void removeAll() {
    shapes.removeAllElements();
  }

  /**
   * Return an enumerator for the shapes list
   */
  public Enumeration getShapes () {
    return shapes.elements();
  }
} 

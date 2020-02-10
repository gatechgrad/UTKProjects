
//Title:        Silhouette
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Brad Vander Zanden
//Company:      UTK
//Description:  Keeps track of the z-order zLevels for a canvas

package silhouette.properties;

import java.util.Vector;
import silhouette.shapes.*;
import silhouette.exceptions.NoSuchDepthException;
import java.util.Enumeration;
import java.util.ListIterator;

public class zList {
  /**
   * The list of zLevels
   */
  Vector zLevels = new Vector(10, 5);

  /**
   * A sentinel value for the list
   */
  zElement zSentinelValue = new zElement(Double.MAX_VALUE) {
    public boolean bringToFront(SilShape shape) {
      return false;
    }
    public boolean sendToBack(SilShape shape) {
      return false;
    }
    public boolean remove(SilShape shape) {
      return false;
    }
  };

  public zList() {
    zLevels.add(zSentinelValue);
  }

  ListIterator getZLevels() {
    return zLevels.listIterator();
  }

  protected zElement findZElement(double depth) {
    int i;
    zElement z;
    for (i = 0; ; i++) {
      z = (zElement)zLevels.elementAt(i);
      if (z.getZ() >= depth) break;
    }
    if (z.getZ() == depth)
      return z;
    else
      return null;
  }

  protected zElement addZElement(double depth) {
    zElement newZ = new zElement(depth);
    for (int i = 0; ; i++) {
      zElement z = (zElement)zLevels.elementAt(i);
      if (z.getZ() > depth) {
        zLevels.add(i, newZ);
        break;
      }
    }
    return newZ;
  }

  public void add(SilShape newshape, double depth) {
    zElement z = findZElement(depth);
    if (z == null)
      z = addZElement(depth);
    z.add(newshape);
  }

  public void add(SilShape newshape, double depth, int index) {
    zElement z = findZElement(depth);
    if (z == null)
      z = addZElement(depth);
    z.add(newshape, index);
  }

  public boolean remove(SilShape shape) {
    for (int i = 0; i < zLevels.size(); i++) {
      zElement z = (zElement)zLevels.elementAt(i);
      if (z.remove(shape))
        return true;
    }
    return false;
  }

  public void removeAll() {
    for (int i = 0; i < zLevels.size() - 1; i++)
      ((zElement)(zLevels.elementAt (i))).removeAll();
  }

  /**
   * Returns a reference to the specified shape
   */
  public SilShape getShape(String shapename) {
    zElement z;
    SilShape retrievedShape = null;
    for (int i = 0; (retrievedShape == null) && (i < zLevels.size()); i++) {
      z = (zElement)zLevels.elementAt(i);
      retrievedShape = z.getShape(shapename);
    }
    return retrievedShape;
  }
  
  /**
   * Brings the specified Shape to the front of its z-level.
   */
  public boolean bringToFront(SilShape moveshape) {
    for (int i = 0; i < zLevels.size(); i++) {
      zElement z = (zElement)zLevels.elementAt(i);
      if (z.bringToFront(moveshape))
        return true;
    }
    return false;
  }

  /**
   * Sends the specified Shape to the back of its z-level.
   */
  public boolean sendToBack(SilShape moveshape) {
    for (int i = 0; i < zLevels.size(); i++) {
      zElement z = (zElement)zLevels.elementAt(i);
      if (z.sendToBack(moveshape))
        return true;
    }
    return false;
  }

  /**
   * Gets the index of the specified Shape.
   */
  public int getShapeIndex(SilShape target) {
    zElement z;
    int index;

    for (int i = 0; i < zLevels.size(); i++) {
      z = (zElement)zLevels.elementAt(i);
      index = z.getShapeIndex (target);
      if (index >= 0)
        return index;
    }
    return -1;
  }

  /**
   * Gets the depth of the specified Shape.
   */
  public double getShapeDepth(SilShape target) {
    zElement z;
    int index;

    for (int i = 0; i < zLevels.size(); i++) {
      z = (zElement)zLevels.elementAt(i);
      index = z.getShapeIndex (target);
      if (index >= 0)
        return z.getZ();
    }
    return -1;
  }

  /**
   * Returns an enumerator for the shapes at the specified z-level
   */
  public Enumeration getShapes(double zLevel) throws NoSuchDepthException {
    zElement z = findZElement(zLevel);
    if (z == null)
      throw new NoSuchDepthException(zLevel);
    return z.getShapes();
  }

  /**
   * Returns an enumerator for all the shapes managed by this zList
   */
  public Enumeration getShapes() {
    return new zListEnumerator(this);
  }
  
}

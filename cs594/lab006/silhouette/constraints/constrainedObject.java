package silhouette.constraints;

/**
 * A top-level class for objects that will contain constraints. Objects
 * that contain constraints should derive from this class.
 */

import java.util.*;

public class constrainedObject {
  /**
   * observers that get notified when a property is read 
   */
  static List getObservers;    

  /**
   * observers that get notified when a property is set
   */
  static List setObservers;

  /**
   * observers that get notified when a property is marked out of date
   */
  static List markObservers;

  /**
   * The list of constraints contained by this object
   */
  List constraints = new LinkedList();

  /**
   * A method that adds constraints to an object
   */
  public void addConstraint(constraint cn) {
    constraints.add(cn);
    cn.initialize(this);
  }

  public Iterator getConstraints() {
    return constraints.iterator();
  }
}


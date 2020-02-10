package silhouette.properties;

import silhouette.properties.Property;
import java.util.*;

/**
 * Data structure for holding Set.
 */
public class PropertySet extends Object {

  private List set = new LinkedList();

  /**
   * Constructs a new, empty PropertySet.
   */
  public PropertySet() {}

  /**
   * Creates a new Property with specified name and value.  Adds
   * this new Property to the Property set.
   * @return The Property that was created.
   */
  public Property addProperty(String name_in, Object value_in) {
    Property temp_prop = new Property(name_in, value_in);
    set.add(temp_prop);
    return(temp_prop);
  }


  /**
   * Returns the number of Properties held in this set.
   */ 
  public int countProperties() {
    return( set.size() );
  }

  /**
   * Returns the value of a Property held within this PropertySet.
   * @param name_in 	the name of the Property
   */
  public Object getValue(String name_in) {
    Property temp_prop = getProperty(name_in);
    if(temp_prop != null) {
      return(temp_prop.value);
    } else {  
      return null;
    }
  }

  /**
   * Returns a reference to the Property with the specifed name.
   */
  public Property getProperty(String name_in) {
    Iterator iter = set.iterator();
    Property temp_prop;
  
    while(iter.hasNext()) {
      temp_prop = (Property)iter.next();
      if(temp_prop.name.equals(name_in)) {
        return(temp_prop);
      }
    }
    return(null);
  }

  /** 
   * Changes the value of the Property with the specified name to the
   * specified value.
   */
  public Object setValue(String name_in, Object value_in) {
    Property temp_prop = getProperty(name_in);

    if(temp_prop != null) {
      temp_prop.value = value_in;
    } else {
      addProperty(name_in, value_in);
    }    
    return(value_in);
  }

  /**
   * Removes the Property with the specified name from this ProperySet.
   * @return The Property that was removed.
   */
  public Property removeProperty(String name_in) {
    Iterator iter = set.iterator();
    Property temp_prop;

    while (iter.hasNext()) {
      temp_prop = (Property)iter.next();
      if(temp_prop.name.equals(name_in)) {
        iter.remove();
        return(temp_prop);
      }
    }         
    
    return(null);
  }
}

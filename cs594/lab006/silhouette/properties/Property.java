


package silhouette.properties;

/**
 * A method for dynamically adding attributes to a class at run-time.
 * Each property has a name and holds a value.
 */
public class Property extends Object {

  /**
   * The name of this Property.
   */
  //protected String name;

  /**
   *  The value of this Property.
   */
  protected Object value;
  
  /**
   * The name of this Property.
   */
  protected String name;

  /**
   * Constructs a new Property object with the specified name and value.
   */
  public Property(String name_in, Object value_in) {
    name  = name_in;
    value = value_in;
  }

  /**
   * Returns the name of this Property.
   */
  public String getName() {
    return(name);
  }

  /**
   * Changes the value of this Property.
   */
  public Object setValue(Object value_in) {
    value = value_in;
    return(value);
  }

  /**
   * Returns the value of this Property.
   */
  public Object getValue() {
    return(value);
  }
}

package silhouette.shapes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Enumeration;
import java.util.ArrayList;
import silhouette.interactors.*;
import silhouette.properties.*;
import silhouette.inspector.Inspector;

/**
 * CanvasShape is a compositeShape that can display Silhouette shapes. To display
 * Silhouette shapes, add them to a CanvasShape using one of CanvasShape's
 * addShape methods. CanvasShape is attached to a SilPanel, the user should add
 * this SilPanel to the componetPane to let everything been displayed. when
 * SilPanel's paintComponent method is called, it will automatically redraw all the
 * Silhouette shapes (plus any Swing components that may have been added to the
 * canvas).
 */
public class CanvasShape extends CompositeShape implements SilContainer {

  /**
   * Default z-depth for feedback objects
   */
  public static final double FeedbackDepth = 0.0;

  /**
   * Default z-depth for shapes
   */
  public static final double DefaultDepth = 1.0;

  /**
   * A list of z-depths for the canvas. Each z-depth contains a list of
   * objects at that depth.
   */
  protected zList zLevels = new zList();

  /**
   * The list of selected objects on this canvas
   */
  protected java.util.List selectedObjects = new LinkedList();

  /**
   * The SilPanel which the CanvasShape is attached to
   */
  protected SilPanel	topPanel;

  Inspector myInspector;

    /**
     * The true left for the CanvasShape. The left returned by CanvasShape
     * to Silhouette objects should always be 0
     */
    protected double trueLeft;

    /**
     * The true left for the CanvasShape. The left returned by CanvasShape
     * to Silhouette objects should always be 0
     */
    protected double trueTop;
     
  /**
   * The Silouette event handler that handles input events for this canvas
   */
  protected SilEventHandler eventHandler;

  /**
   * Constructs a new CanvasShape object.
   */
  public CanvasShape() {
    super();
    topPanel = new SilPanel(this);
    initializeCanvasShape();
  }

  /**
   * Constructs a new CanvasShape object with the specifed DisplayManager.
   */
  public CanvasShape(DisplayManager dm) {
    super(dm);
    topPanel = new SilPanel(this);
    initializeCanvasShape();
  }

  /**
   * Constructs a new CanvasShape object with the specifed Size.
   */
  public CanvasShape(double x_in, double y_in, double width_in,
    double height_in) {
    super(x_in, y_in, width_in, height_in);
    topPanel = new SilPanel(this);
    initializeCanvasShape();

  }

  /**
   * Constructs the initial state information for the canvas
   */
  protected void initializeCanvasShape() {
    this.setWidth(400);
    this.setHeight(400);
    getDisplay().setClient(this);
    eventHandler = new SilEventHandler(this);
    myInspector = new Inspector();
    this.addInteractor(myInspector);
  }

  /**
   * Initializes the bounds of the composite shape
   */
  protected void initializeBounds(double x_in, double y_in,
    double width_in, double height_in) {
    super.setLeft(x_in);
    super.setTop(y_in);
    super.setWidth(width_in);
    super.setHeight(height_in);
  }

  /**
   * return the SilPanel for user to add to contentPane
   */
  public SilPanel getSilPanel() {
    return topPanel;
  }

  /**
   * set CanvasShape's left
   */
  public SilShape setLeft(double left0) {
      //    super.setLeft(left0);
      trueLeft = left0;
      topPanel.setLocation((int)left0, (int)trueTop);
      return this;
  }

  /**
   * set CanvasShape's top
   */
  public SilShape setTop(double top0) {
      //    super.setTop(top0);
      trueTop = top0;
    topPanel.setLocation((int)trueLeft, (int)top0);
    return this;
  }

  /**
   * set CanvasShape's width
   */
  public SilShape setWidth(double width0) {
    super.setWidth(width0);
    topPanel.setPreferredSize(new Dimension((int)width0,
        (int)this.getHeight()));
    return this;
  }

  /**
   * set CanvasShape's height
   */
  public SilShape setHeight(double height0) {
    super.setHeight(height0);
    topPanel.setPreferredSize(new Dimension((int)this.getWidth(),
        (int)height0));
    return this;
  }

  /** 
   * Reshapes the canvas after the panel to which it belongs is
   * reshaped by a ComponentEvent
   */
  void panelResize(Dimension size) {
    super.setWidth(size.getWidth());
    super.setHeight(size.getHeight());
  }

  /**
   * Adds a Shape at the default z-depth.
   * @param newshape 	the Shape to be added
   */
  public SilShape add(SilShape newshape) {
    return add(newshape, CanvasShape.DefaultDepth);
  }

  /**
   * Adds a Shape at the indicated z-depth.
   */
  public SilShape add(SilShape newshape, double depth) {
    zLevels.add(newshape, depth);
    return(super.add(newshape));
  }

  /**
   * Adds a Shape at the default z-depth.
   * @param index 	where to put the Shape in the specified z-depth
   */
  public SilShape add(SilShape newshape, int index) {
    return add(newshape, index, CanvasShape.DefaultDepth);
  }

  /**
   * Adds a Shape at the indicated z-depth.
   * @param index 	where to put the Shape in the specified z-depth
   */
  public SilShape add(SilShape newshape, int index, double depth) {
    zLevels.add(newshape, depth, index);
    return(super.add(newshape,index));
  }

  /**
   * Adds a Shape at the default z-depth.
   * @param shapename 	the name to be given to this Shape
   */
  public SilShape add(SilShape newshape, String shapename) {
    return( add(newshape, shapename, CanvasShape.DefaultDepth) );
  }

  /**
   * Adds a Shape at the indicated z-depth.
   * @param newshape 	the Shape to be added
   * @param shapename 	the name to be given to this Shape
   */
  public SilShape add(SilShape newshape, String shapename, double depth) {
    super.add(newshape, shapename);
    zLevels.add(newshape, depth);
    return(this);
  }

  /**
   * Adds a Shape at the default z-depth.
   * @param shapename 	the name to be given to this Shape
   * @param index 	where to put the Shape in the given z-depth
   */
  public SilShape add(SilShape newshape, String shapename, int index) {
    return( add(newshape, shapename, index, CanvasShape.DefaultDepth) );
  }

  /**
   * Adds a Shape at the indicated z-depth.
   * @param shapename 	the name to be given to this Shape
   * @param index   where to put the Shape in the given z-depth
   */
  public SilShape add(SilShape newshape, String shapename, int index,
    double depth) {
    super.add(newshape, shapename, index);
    zLevels.add(newshape, depth, index);
    return(this);
  }

  /**
   * Removes a Shape from this CanvasShape.
   * @param delshape	 the Shape to remove
   * @return  whether the CanvasShape contained the shape
   */
  public SilShape remove(SilShape delshape) {
    super.remove(delshape);
    zLevels.remove(delshape); 
    return this;
  }

  /**
   * Removes all Shapes from this CanvasShape.
   */
  public void removeAll() {
    super.removeAll();
    topPanel.removeAll();
    zLevels.removeAll();
  }

  /**
   * Brings the specified Shape to the front of its z-level.
   * @return true if this canvas contains the shape, false otherwise
   */
  public boolean bringToFront(SilShape moveshape) {
    return zLevels.bringToFront(moveshape);
  }

  /**
   * Sends the specified Shape to the back of its z-level.
   * @return true if this canvas contains the shape, false otherwise
   */
  public boolean sendToBack(SilShape moveshape) {
    return zLevels.sendToBack(moveshape);
  }

  /**
   * Gets a reference to the specified Shape.
   * @param shapename 	the name of the Shape
   */
  public SilShape getShape(String shapename) {
    return( zLevels.getShape(shapename) );
  }

  /**
   * Gets the index of the specified Shape.
   * @return The index of the specified shape if it is found, -1 otherwise
   */
  public int getShapeIndex(SilShape target) {
    return( zLevels.getShapeIndex(target) );
  }

  /**
   * Gets the z-level of the specified Shape.
   * @return The z-level of the specified shape if it is found, -1 otherwise
   */
  public double getShapeDepth(SilShape target) {
    return ( zLevels.getShapeDepth(target) );
  }



  /**
   * refresh paints the CanvasShape by calling the DisplayManager's redisplay
   * method.
   */
  public void refresh(Graphics g) {
    super.refresh((Graphics2D)g);
  }

  /**
   * repaint everything by calling SilPanel's repaint method.
   */
  public void repaint() {
      if (valid == true) {
	  valid = false;
	  topPanel.repaint();
      }
  }

    public void invalidate() {
	if (valid == true) {
	    valid = false;
	    if (topPanel != null)
		topPanel.repaint();
	}
    }

    public void validate() {
	valid = true;
    }

  /**
   * Locates the Shape at the given x, y position.
   * @return A Shape if found, null otherwise.
   */
  public SilShape find(double x, double y) {
    SilShape tempShape;
    SilShape candidateShape = null;
    Enumeration shapeIter = this.getShapes();

    while (shapeIter.hasMoreElements()) {
      tempShape = (SilShape)shapeIter.nextElement();
      if ((tempShape.getVisible()) && (tempShape.contains(x, y))) {
        candidateShape = tempShape;
      }
    }

    return(candidateShape);
  }

  /**
   * Locates the leaf object in the composite object hierarchy that
   * is at the given x, y position.
   * @return A Shape if found, null otherwise.
   */
  public SilShape findLeaf(double x, double y) {
    SilShape candidateShape = find(x, y);

    if (candidateShape != null) {
      SilShape childShape = candidateShape.findLeaf(x, y);
      if (childShape != null)
        return childShape;
      else
        return candidateShape;
    }
    return(candidateShape);
  }

  /**
   * find all the shapes in the composite object hierarchy that contain
   * the given (x,y) point
   * @param When findShapes is finished, foundShapes will contain a stack
   *   of shapes that contain the given (x,y) point
   */
  public void findShapes(int x, int y, Stack foundShapes) {
    SilShape candidateShape = null;
    SilShape tempShape;
    Enumeration shapeIter = this.getShapes();

    foundShapes.push(this);

    // do not stop iterating after the first object containing the point
    // is found because we want the <i>top</i> object that contains the
    // point and that will occur later in the stacking order
    while (shapeIter.hasMoreElements()) {
      tempShape = (SilShape)shapeIter.nextElement();
      if (tempShape.contains(x, y)) {
        candidateShape = tempShape;
      }
    }
    if (candidateShape != null)
      candidateShape.findShapes(x, y, foundShapes);
  }

  /**
   * returns an iterator that can be used to iterate through all the
   * shapes contained in this canvas
   */
  public Enumeration getShapes () {
    return zLevels.getShapes();
  }

  /**
   * returns an iterator that can be used to iterate through all the
   * shapes at the indicated z-level
   */
  public Enumeration getShapes (double depth) {
    return zLevels.getShapes(depth);
  }

  /**
   * Sets the preferred size of the canvas.
   */
  public void setPreferredSize(Dimension size) {
    topPanel.setPreferredSize(size);
    this.setWidth(size.getWidth());
    this.setHeight(size.getHeight());
  }

  /**
   * Returns the list of selected objects. This list can be modified in
   * any way that the application programmer sees fit.
   */
  public java.util.List getSelectedObjects () {
    return selectedObjects;
  }

  /**
   * Sets the fill color of the canvas by setting the canvas's SilPanel's
   * background color.
   */
  public SilShape setFillColor(Color color_in) {
    super.setFillColor(color_in);
    topPanel.setBackground(color_in);
    return(this);
  }

  /**
   * Return this canvas's event handler
   */
  public SilEventHandler getEventHandler() {
    return eventHandler;
  }

  /**
   * return the canvas to which this object is attached, which in a
   * canvas's shape, is itself
   */
  public CanvasShape getCanvas () {
    return this;
  }

  public Inspector getInspector() {
    return myInspector;
  }	

}

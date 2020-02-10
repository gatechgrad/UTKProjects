package silhouette.shapes;

import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;
import silhouette.constraints.*;

/**
 * A Arrowline that is drawn on the screen. In addition to the properties of
 * a normal Silhouette Line shape, a arrow-line also has the following 
 * properties:
 * <p>
 * <ol>
 * <li> X1, Y1: The first endpoint
 * <li> X2, Y2: The second endpoint
 *
 * <li> ArrowLength:  Arrow Length, could be changed by user
 *
 * <li> ArrowWidth:   Arrow Width,  could be changed by user
 *
 * </ol>
 */

public class SilArrowLine extends LineShape 
{
  protected GeneralPath  	path;
    // indicates if the arrowhead needs to be recomputed
  protected boolean             invalidArrowhead = true;
  protected int 		ArrowLength, ArrowWidth;

  protected SilArrowLine() {
     this(0, 0, 40, 40, 8, 4, false); }

  public SilArrowLine( int inX1, int inY1, int inX2, int inY2 ) 
  {
    this( inX1, inY1, inX2, inY2, 8, 4, false );
  } 

  public SilArrowLine(int inX1, int inY1, int inX2, int inY2, 
    boolean ifFill) 
  {
    this( inX1, inY1, inX2, inY2, 8, 4, ifFill );
  }

  /* ArrowLine from TextShape to Rectangle */
  public SilArrowLine (TextShape ts, RectangleShape rect) 
  {
    Rectangle tsRect = ts.getBounds();

    Point2D.Float tsEnd, rectEnd;
    rectEnd = getIntersectPoint(
      new Point2D.Float((float)tsRect.getCenterX(),(float)tsRect.getCenterY()),
      rect );
    tsEnd = getIntersectPoint(
      new Point2D.Float((float) (rect.getLeft() + rect.getWidth()/2), 
	                (float) (rect.getTop()  + rect.getHeight()/2) ),
      new RectangleShape(
	(int) tsRect.getX(),     (int) tsRect.getY(),
	(int) tsRect.getWidth(), (int) tsRect.getHeight()) 
      );

    setX1(tsEnd.getX());
    setY1(tsEnd.getY());
    setX2(rectEnd.getX());
    setY2(rectEnd.getY());
    setFilled(false);
    ArrowLength  = 8;
    ArrowWidth   = 4;
  }


  /* ArrowLine From rect to TextShape */
  public SilArrowLine (RectangleShape rect, TextShape ts) 
  {
    Rectangle tsRect = ts.getBounds();

    Point2D.Float tsEnd, rectEnd;
    rectEnd = getIntersectPoint(
      new Point2D.Float((float)tsRect.getCenterX(),(float)tsRect.getCenterY()),
      rect );
    tsEnd = getIntersectPoint(
      new Point2D.Float((float) (rect.getLeft() + rect.getWidth()/2), 
	                (float) (rect.getTop()  + rect.getHeight()/2) ),
      new RectangleShape((int) tsRect.getX(), (int) tsRect.getY(),
	(int) tsRect.getWidth(), (int) tsRect.getHeight()) );

    setX1(rectEnd.getX());
    setY1(rectEnd.getY());
    setX2(tsEnd.getX());
    setY2(tsEnd.getY());
    setFilled(false);
    ArrowLength  = 8;
    ArrowWidth   = 4;
   }

  public SilArrowLine( int inX1, int inY1, int inX2, int inY2, 
    int inLength, int inWidth, boolean ifFill ) 
  {
    setX1((double) inX1);
    setY1((double) inY1);
    setX2((double) inX2);
    setY2((double) inY2);
    setFilled(ifFill);
    ArrowLength  = inLength;
    ArrowWidth   = inWidth;
  }

    public LineShape setX1(double x) {
	/* we would like to use a constraint but can't because the
	   debugger cannot use objects that have constraints */
	invalidArrowhead = true;
	return super.setX1(x);
    }

    public LineShape setY1(double y) {
	/* we would like to use a constraint but can't because the
	   debugger cannot use objects that have constraints */
	invalidArrowhead = true;
	return super.setY1(y);
    }

    public LineShape setX2(double x) {
	/* we would like to use a constraint but can't because the
	   debugger cannot use objects that have constraints */
	invalidArrowhead = true;
	return super.setX2(x);
    }

    public LineShape setY2(double y) {
	/* we would like to use a constraint but can't because the
	   debugger cannot use objects that have constraints */
	invalidArrowhead = true;
	return super.setY2(y);
    }

  public int getArrowLength () {
    return ArrowLength;
  }

  public SilArrowLine setArrowLength (int ArrowLength_in) {
    ArrowLength = ArrowLength_in;
    return this;
  }

  public int getArrowWidth () {
    return ArrowWidth;
  }

  public SilArrowLine setArrowWidth (int ArrowWidth_in) {
    ArrowWidth = ArrowWidth_in;
    return this;
  }

  void calculateArrowhead ()
   {
    float                 LeftX, LeftY, RightX, RightY;

    float dx = (float) getX2() - (float) getX1();
    float dy = (float) getY2() - (float) getY1();
    float d = (float) Math.sqrt (dx * dx + dy * dy);
    if (d < 1.0) {
      d  = 1.0f;
      dx = 1.0f;
    }
    float lx = ((float) ArrowLength * dx) / d;
    float ly = ((float) ArrowLength * dy) / d;
    float wx = ((float) ArrowWidth * dx) / d;
    float wy = ((float) ArrowWidth * dy) / d;


    LeftX  = (float) getX2() - lx + wy;
    LeftY  = (float) getY2() - ly - wx;
    RightX = (float) getX2() - lx - wy;
    RightY = (float) getY2() - ly + wx;
    path = new GeneralPath();
    path.moveTo(LeftX, LeftY);
    path.lineTo((float) getX2(), (float) getY2());
    path.lineTo(RightX, RightY);
    if(getFilled()) {
      path.closePath();
    }
   }


  /* This method should never be called directly by an application. To get a
   * line shape to appear on the screen, either add it to a Silhouette canvas
   * or to a composite shape that has been added to a Silhouette canvas.
   */
  public void draw (Graphics2D g2) {
    if(getVisible()) {
      // Use antialiasing.
      g2.setColor(getLineColor());

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);

      //      if (invalidArrowhead)
	  calculateArrowhead();

      if(getFilled()) {
	g2.fill(path);
      } else {
	g2.draw(path);
      }

      g2.draw(new Line2D.Float(
	  (float)getX1(), (float)getY1(), (float)getX2(), (float)getY2()));
      validate();
    }
  }

  /* add Fields for reflect usage */

  public static Vector Fields        = new Vector(8);

  static {
    Fields.addElement("X1");
    Fields.addElement("Y1");
    Fields.addElement("X2");
    Fields.addElement("Y2");
    Fields.addElement("Visible");
    Fields.addElement("LineColor");
    Fields.addElement("FillColor");
    Fields.addElement("Filled");
    //Fields.addElement("ArrowFilled");
    //Fields.addElement("ArrowLength");
    //Fields.addElement("ArrowWidth");
  }

  /**
   * Function getIntersectPoint() used to get the exact intersect point of
   * the given point to the center of a rectangle
   */
  public Point2D.Float getIntersectPoint ( 
    Point2D.Float FmPt, RectangleShape ToRect )
  {

    final int LeftSide   = 101; 
    final int TopSide    = 102;
    final int RightSide  = 103;
    final int BottomSide = 104;

    float x1, y1, wd, ht;
    float ptX, ptY;

    Point2D.Float 	Ipt = new Point2D.Float(0.f, 0.f); // intersected point

    // if that points is inside of the rectangle, return (0,0)
    if ( ToRect.contains(FmPt.getX(), FmPt.getY()) ) return Ipt;

    x1 = (float) ToRect.getLeft();
    y1 = (float) ToRect.getTop();
    wd = (float) ToRect.getWidth();
    ht = (float) ToRect.getHeight();

    ptX = (float) FmPt.getX();
    ptY = (float) FmPt.getY();

    float CenterX = x1 + wd/2.0f;
    float CenterY = y1 + ht/2.0f;

    // decides the position of the pointer
    int ptPos;
    float slope = (ptY-CenterY)/(ptX-CenterX);

    if (ptY < y1) {
      // Upper
      if (ptX < x1) {
	float ts = (y1-CenterY)/(x1-CenterY);
	if (slope >= ts)       ptPos = TopSide;  
	else                   ptPos = LeftSide;
      } 
      else if (ptX < (x1+wd))  ptPos = TopSide;
      else {
	float ts = (y1-CenterY)/(x1+wd-CenterY);
	if (slope >= ts)       ptPos = RightSide;
	else                   ptPos = TopSide;
      }
    } else if (ptY < (y1+ht)) {
      if (ptX < x1)            ptPos = LeftSide;
      else                     ptPos = RightSide;
    } else if (ptX < x1) {
      float ts = (y1+ht-CenterY)/(x1-CenterY);
      if (slope >= ts)         ptPos = BottomSide;
      else 	               ptPos = BottomSide;
    } else if (ptX < (x1+wd))  ptPos = BottomSide;
    else {
      float ts = (y1+ht-CenterY)/(x1+wd-CenterY);
      if (slope <= ts)         ptPos = RightSide;
      else                     ptPos = BottomSide;
    }

    switch (ptPos) {
      case LeftSide:
	Ipt = 
	  new Point2D.Float(x1, CenterY-slope*0.5f*wd);
	break;
      case TopSide:
	Ipt = 
	  new Point2D.Float(CenterX-ht/(2f*slope), y1);
	break;
      case RightSide:
	Ipt = 
	  new Point2D.Float(x1+wd, CenterY+wd*(0.5f*slope) );
	break;
      case BottomSide:
	Ipt = 
	  new Point2D.Float(CenterX+ht/(2f*slope), y1+ht);
	break;
    }
    return Ipt;
  }  // end of getIntersectPoint()

} // end of class ArrowLine


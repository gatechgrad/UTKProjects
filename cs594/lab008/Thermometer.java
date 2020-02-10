import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.*;

public class Thermometer extends JComponent implements MouseListener, MouseMotionListener {

  JFrame theFrame;
  boolean isFirstTime;
  AffineTransform at;
  int iMinValue, iMaxValue, iValue;
  boolean showBorderHandles;
  boolean mouseDragged;
  boolean handleDragged;

  boolean thermPressed;
  boolean thermDragged;

  boolean EHandleDragged, SEHandleDragged, SHandleDragged, SWHandleDragged,
          WHandleDragged, NWHandleDragged, NHandleDragged, NEHandleDragged;

  Rectangle2D.Double rHandle; 
  Rectangle2D.Double rNWHandle, rNHandle, rNEHandle, rEHandle,
                     rSEHandle, rSHandle, rSWHandle, rWHandle; 


  int iThermWidth, iThermHeight;
  int iBulbSize;

  int iHandleYOffset = 45;
  int iHandleXOffset = 70;

  int iRotateAngle;

  int iThermX, iThermY;
  double iScaleX, iScaleY;

  int iMouseXOff, iMouseYOff;

  public Thermometer(int i1, int i2, int i3) {
    isFirstTime = true;

    at = new AffineTransform();

    iMinValue = 32;
    iMaxValue = 212;
    iValue = 76;
   
    showBorderHandles = false;
    mouseDragged = false;
    handleDragged = false;
    thermPressed = false;
    thermDragged = false;

    EHandleDragged = false;
    SEHandleDragged = false;
    SHandleDragged = false;
    SWHandleDragged = false;
    WHandleDragged = false;
    NWHandleDragged = false;
    NHandleDragged = false;
    NEHandleDragged = false;

    setPreferredSize(new Dimension(640, 480));

    iMinValue = i1;
    iMaxValue = i2;
    iValue = i3;

    addMouseMotionListener(this);
    addMouseListener(this);


  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    int iHeight;
    int iViewWidth;
    int iViewHeight;
  
    int iMeterHeight;

    int i;
    int iPosition;
    int iTextOffset = 40;


    Ellipse2D.Double eBulb;
    Rectangle2D.Double rTube;
    Rectangle2D.Double rMercury;
    Area areaThermometer;
    Area areaHandle;
    Area areaMercury;

    //set up variables
    iViewWidth = getWidth();
    iViewHeight = getHeight();

    iThermHeight = iViewHeight * 3 / 4;
    iThermWidth = iViewHeight * 1 / 15;
    iBulbSize = iThermWidth * 3;


    iMeterHeight = iThermHeight - iBulbSize;
    iPosition = (iValue - iMinValue) * (iThermHeight - iBulbSize) 
                / (iMaxValue - iMinValue);

    //set the transform
   if (isFirstTime) {
     iThermX = getWidth() / 2;
     iThermY = getHeight() / 2;

     iScaleX = 1.0;
     iScaleY = 1.0;
   
     iRotateAngle = 0;

     isFirstTime = false;
   }

    at.setToIdentity();

    at.scale(iScaleX, iScaleY);
    at.translate(iThermX, iThermY);
    at.rotate(Math.toRadians(-iRotateAngle));
    g2.transform(at);

    //draw the thermometer
    eBulb = new Ellipse2D.Double();
    eBulb.setFrame(-iBulbSize / 2, (-iThermHeight / 2) + iThermHeight - (iBulbSize / 2), 
                   iBulbSize, iBulbSize);

    rTube = new Rectangle2D.Double();
    rTube.setFrame(-iThermWidth / 2, (iThermHeight / 2) - (iBulbSize), iThermWidth, iBulbSize);

    areaThermometer = new Area(eBulb);
    areaThermometer.add(new Area(rTube));
    
    g2.setColor(Color.red);
    g2.fill(areaThermometer); 

    g2.setColor(Color.black);
    g2.draw(areaThermometer); 

    //draw the mercury
    rMercury = new Rectangle2D.Double();
    rMercury.setFrame(-iThermWidth / 2, (iThermHeight / 2) - (iBulbSize) - iPosition, 
                      iThermWidth, iPosition + 1);

    areaMercury = new Area(rMercury);
    g2.setColor(Color.red);
    g2.fill(areaMercury); 
 
    //draw the handle
    rHandle = new Rectangle2D.Double();
    rHandle.setFrame(-4, (iThermHeight / 2) - iBulbSize - iPosition - 4, 8, 8);
    g2.setColor(Color.black);
    areaHandle = new Area(rHandle);
    g2.fill(areaHandle);

    //draw border handles
    if (showBorderHandles) {
    g2.setColor(Color.black);

    rNWHandle = new Rectangle2D.Double();
    rNWHandle.setFrame(-iHandleXOffset, (-iThermHeight / 2) - iHandleYOffset, 8, 8);
    areaHandle = new Area(rNWHandle);
    g2.fill(areaHandle);

    rNHandle = new Rectangle2D.Double();
    rNHandle.setFrame(0, (-iThermHeight / 2) - iHandleYOffset, 8, 8);
    areaHandle = new Area(rNHandle);
    g2.fill(areaHandle);

    rNEHandle = new Rectangle2D.Double();
    rNEHandle.setFrame(iHandleXOffset, (-iThermHeight / 2) - iHandleYOffset, 8, 8);
    areaHandle = new Area(rNEHandle);
    g2.fill(areaHandle);

    rEHandle = new Rectangle2D.Double();
    rEHandle.setFrame(iHandleXOffset, 0, 8, 8);
    areaHandle = new Area(rEHandle);
    g2.fill(areaHandle);

    rSEHandle = new Rectangle2D.Double();
    rSEHandle.setFrame(iHandleXOffset, (iThermHeight / 2) + iHandleYOffset, 8, 8);
    areaHandle = new Area(rSEHandle);
    g2.fill(areaHandle);

    rSHandle = new Rectangle2D.Double();
    rSHandle.setFrame(0, (iThermHeight / 2) + iHandleYOffset, 8, 8);
    areaHandle = new Area(rSHandle);
    g2.fill(areaHandle);

    rSWHandle = new Rectangle2D.Double();
    rSWHandle.setFrame(-iHandleXOffset, (iThermHeight / 2) + iHandleYOffset, 8, 8);
    areaHandle = new Area(rSWHandle);
    g2.fill(areaHandle);

    rWHandle = new Rectangle2D.Double();
    rWHandle.setFrame(-iHandleXOffset, 0, 8, 8);
    areaHandle = new Area(rWHandle);
    g2.fill(areaHandle);
    }



    //draw the outline
    GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                                     4);
    gp.moveTo(-iThermWidth / 2, (iThermHeight / 2) - iBulbSize ); 
    gp.lineTo(-iThermWidth / 2, -iThermHeight / 2); 
    gp.lineTo(iThermWidth / 2, -iThermHeight / 2); 
    gp.lineTo(iThermWidth / 2, (iThermHeight / 2) - iBulbSize ); 

    g2.setColor(Color.black);
    g2.draw(gp);

    //draw the tickmarks
    for (i = 0; i < 5; i++) {
      Line2D.Double theLine = new Line2D.Double(-iThermWidth / 2, 
                                                -iThermHeight / 2 + (i * (iThermHeight - iBulbSize) / 4),
                                                -iThermWidth / 2 - 10, 
                                                -iThermHeight / 2 + (i * (iThermHeight - iBulbSize) / 4));

      g2.setColor(Color.black);
      g2.draw(theLine);
 
      TextLayout tl = new TextLayout("" + (iMinValue + ((4 - i) * (iMaxValue - iMinValue) / 4)), new Font("Helvetica", Font.PLAIN, 12), new
FontRenderContext(null, false, false));
      g2.setColor(Color.black);
      tl.draw(g2, (-iThermWidth / 2) - iTextOffset, -iThermHeight / 2 + (i * (iThermHeight - iBulbSize) / 4));
     
    } 
  
    //draw the current value
    TextLayout tl2 = new TextLayout("" + iValue, new Font("Helvetica", Font.PLAIN, 12), new
FontRenderContext(null, false, false));
      g2.setColor(Color.black);
      tl2.draw(g2, (iThermWidth / 2) + 10, (iThermHeight / 2) - iBulbSize - iPosition);


  }

  public void setupWindow() {
    theFrame = new JFrame();
    theFrame.getContentPane().add(this);

    WindowListener wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.hide();
        System.exit(0);
      }
    };
    theFrame.addWindowListener(wl);
    theFrame.pack();
    theFrame.show();
  }

  private void setMercuryPosition(int x, int y) {
    int iHeight;

    iHeight = -y + (iThermHeight / 2) - iBulbSize;

    iValue = iHeight * (iMaxValue - iMinValue) / (iThermHeight - iBulbSize);
    iValue += iMinValue;
    if (iValue > iMaxValue) {
      iValue = iMaxValue;
    }

    if (iValue < iMinValue) {
      iValue = iMinValue;
    }
    repaint();
  }

  public void setRotateAngle(int i) {
    iRotateAngle = i;
    repaint();
  }

  public Point2D.Double getTransformedPoint(int x, int y) {
    Point2D.Double pnt2D;
    Point2D.Double pnt;

    pnt = null;

    try {
      pnt2D =  new Point2D.Double(x, y);
      pnt = (Point2D.Double) at.inverseTransform((Point2D) pnt2D, null);

    } catch (NoninvertibleTransformException e2) { }
    return pnt;

  }

  public void mouseDragged(MouseEvent e) {
    Point2D.Double pnt;

    pnt = getTransformedPoint(e.getX(), e.getY());
 
    if (handleDragged) {
      setMercuryPosition((int) pnt.x, (int) pnt.y);  
    }
 
    if (thermDragged) {
      iThermX += (int) pnt.x - iMouseXOff;
      iThermY += (int) pnt.y - iMouseYOff;      
      thermPressed = false;
    }

    if (EHandleDragged) {
      iScaleX += (pnt.x - rEHandle.x) / (iThermWidth + iHandleXOffset);
    } else if (WHandleDragged) {
      iScaleX -= (pnt.x - rWHandle.x) / (iThermWidth + iHandleXOffset);
    } else if (NHandleDragged) {
      iScaleY -= (pnt.y - rNHandle.y) / (iThermHeight + iHandleYOffset);
    } else if (SHandleDragged) {
      iScaleY += (pnt.y - rSHandle.y) / (iThermHeight + iHandleYOffset);
    } else if (NEHandleDragged) {
      iScaleY -= (pnt.y - rNEHandle.y) / (iThermHeight + iHandleYOffset);
      iScaleX += (pnt.x - rNEHandle.x) / (iThermWidth + iHandleXOffset);
    } else if (SEHandleDragged) {
      iScaleY += (pnt.y - rSEHandle.y) / (iThermHeight + iHandleYOffset);
      iScaleX += (pnt.x - rSEHandle.x) / (iThermWidth + iHandleXOffset);
    } else if (SWHandleDragged) {
      iScaleY += (pnt.y - rSWHandle.y) / (iThermHeight + iHandleYOffset);
      iScaleX -= (pnt.x - rSWHandle.x) / (iThermWidth + iHandleXOffset);
    } else if (NWHandleDragged) {
      iScaleY -= (pnt.y - rNWHandle.y) / (iThermHeight + iHandleYOffset);
      iScaleX -= (pnt.x - rNWHandle.x) / (iThermWidth + iHandleXOffset);
    }

    repaint();
  }



  public void mouseMoved(MouseEvent e) {

  }

  public void mousePressed(MouseEvent e) { 
    Point2D.Double pnt;
    Rectangle r = new Rectangle(-iHandleXOffset - iThermWidth, 
                                -iHandleYOffset - iThermHeight, 
                                2 * (iThermWidth + iHandleXOffset), 
                                2 * (iThermHeight + iHandleYOffset)
                               );

    pnt = getTransformedPoint(e.getX(), e.getY());
    iMouseXOff = (int) pnt.x;
    iMouseYOff = (int) pnt.y; 

    if (rHandle.contains(getTransformedPoint(e.getX(), e.getY()))) {
      handleDragged = true;
      repaint();
    } else if  (showBorderHandles && (rEHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      EHandleDragged = true;
    } else if  (showBorderHandles && (rWHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      WHandleDragged = true;
    } else if  (showBorderHandles && (rNHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      NHandleDragged = true;
    } else if  (showBorderHandles && (rSHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      SHandleDragged = true;
    } else if  (showBorderHandles && (rNEHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      NEHandleDragged = true;
    } else if  (showBorderHandles && (rSEHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      SEHandleDragged = true;
    } else if  (showBorderHandles && (rSWHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      SWHandleDragged = true;
    } else if  (showBorderHandles && (rNWHandle.contains(getTransformedPoint(e.getX(), e.getY())))) {
      NWHandleDragged = true;
    } else if (r.contains(getTransformedPoint(e.getX(), e.getY()))) {
      thermDragged = true;
      thermPressed = true;
      repaint();
    } else {
      showBorderHandles = false; 
      repaint();
    }
  }

  public void mouseReleased(MouseEvent e) {
    if (thermPressed) {
      showBorderHandles = !showBorderHandles;
      repaint();
    }

    EHandleDragged = false;
    SEHandleDragged = false;
    SHandleDragged = false;
    SWHandleDragged = false;
    WHandleDragged = false;
    NWHandleDragged = false;
    NHandleDragged = false;
    NEHandleDragged = false;

    thermDragged = false;
    thermPressed = false;
    handleDragged = false;
  }

  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }

  public static final void main(String args[]) {
    Thermometer t =  new Thermometer(32, 212, 76);
    t.setupWindow();
  }
}

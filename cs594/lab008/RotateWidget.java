import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.font.*;

public class RotateWidget  extends JComponent implements MouseMotionListener {

  ThermometerApplication theTA;
  int iRadius;
  int iAngle;

  public RotateWidget(ThermometerApplication ta) {
    theTA = ta;
    iRadius = 75;
    setPreferredSize(new Dimension((iRadius * 2) + 50, iRadius * 2));
    iAngle = 0;
    addMouseMotionListener(this);
  }

  public void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    Arc2D.Double theArc;
    Line2D.Double theLine;
    TextLayout tl;
 
    Area aArc;
    Area aLine;

    int x, y; 

    theArc = new Arc2D.Double(0.0, 0.0, 
                              iRadius * 2, iRadius * 2,
                              0.0, (double) iAngle, Arc2D.PIE);
    aArc = new Area(theArc);
    g.setColor(Color.red);
    g.fill(aArc);

    x = (int) (Math.cos(iAngle * Math.PI / 180.0) * (double) iRadius);
    y = (int) (Math.sin(iAngle * Math.PI / 180.0) * (double) iRadius);
    theLine = new Line2D.Double(iRadius, iRadius,
                                iRadius + x, iRadius - y);
    

    g.setColor(Color.black);
    g.draw(theLine);


    tl = new TextLayout("" + iAngle, new Font("Helvetica", Font.PLAIN, 12), new FontRenderContext(null, false, false));
    g.setColor(Color.black);
    tl.draw(g, (iRadius * 2) + 20, iRadius);
  }

  public int getAngle() {
    return iAngle;
  }

  public void mouseMoved(MouseEvent e) { }
  public void mouseDragged(MouseEvent e) {
    int x, y;
    x = - iRadius + e.getX();
    y = iRadius  - e.getY(); 
    iAngle = (int) (Math.atan((double) y / (double) x) * 180.0 / Math.PI);

    if ((x < 0) && (y >= 0)) {
      iAngle = 180 + iAngle; 
    } else if ((x < 0) && (y < 0)) {
      iAngle = 180 + iAngle; 
    } else if ((x >= 0) && (y < 0)) {
      iAngle = 360 + iAngle; 
    }

    repaint();

    if ((theTA != null) && (theTA.getThermometer() != null)) {
      theTA.getThermometer().setRotateAngle(iAngle); 
    }
  }


  public static void main(String args[]) {
    RotateWidget rw = new RotateWidget(null);
    JFrame theFrame = new JFrame();
    theFrame.getContentPane().add(rw);
    theFrame.pack();
    theFrame.show();

  }
}

/**
 * ThermometerApplication.java - interactive thermometer developed using 
 *                               siloette objects   
 *
 * @author Levi D. Smith (lsmith55@utk.edu, lsmith@cs.utk.edu)
 * @date October 30, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;
import silhouette.interactors.*;
import silhouette.constraints.*;

public class ThermometerApplication {

  /*** CONSTANTS ***/
  public static final int TEXT_OFFSET = 5;

  /*** INSTANCE VARIABLES ***/
  int iMinValue, iMaxValue, iValue;

  JFrame theFrame;

  CanvasShape theCanvas;
  EditableTextShape txtTemperature;
  TextShape txtMaxValue;
  TextShape txtMinValue;
  LineShape lsBottomLine;
  
  RectangleShape rsTube;
  RectangleShape rsMercury;
  CircleShape cirBulb;

  /**
   * ThermometerApplication - constructor
   */
  public ThermometerApplication(int i1, int i2, int i3) {

    iMinValue = i1;
    iMaxValue = i2;
    iValue = i3;


    setupWindow();
    makeThermometerObjects();
    makeConstraints();
    makeInteractors();

    theCanvas.repaint();  //make sure it repaints with all of the
                          //constraints set... else it will look
                          //like one garbled mess in the upper left 
                          //corner of the window
  }


  /**
   * setupWindow - creates the window for the application
   */
  private void setupWindow() {
    WindowListener wl;

    theFrame = new JFrame();
    
    wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.dispose();
        System.exit(0);
      }
    };

    theFrame.addWindowListener(wl);

    theCanvas =  new CanvasShape();
    theFrame.getContentPane().add(theCanvas.getSilPanel());

    theFrame.setTitle("Thermometer Application");
    theFrame.pack();
    theFrame.show();
    
//    theFrame.setLocation(0, 32);

  }

  /**
   *  makeThermometerObjects - creates all of the siloette objects
   */
  private void makeThermometerObjects() {
   
    //make the tube 
    rsTube = new RectangleShape(0, 0, 10, 10);
    rsTube.setFilled(false); 
    rsTube.setLineColor(Color.black); 
    theCanvas.add(rsTube);

    //make the mercury 
    rsMercury = new RectangleShape();
    rsMercury.setFilled(true); 
    rsMercury.setFillColor(Color.red); 
    rsMercury.setLineColor(Color.red); 
    theCanvas.add(rsMercury);

    //make the text degree display
    txtTemperature = new EditableTextShape(0, 0, "" + iValue) {
      public void stopAction(SilInteractor si) {
        try {
          int iTemp;
          iTemp = (new Integer(txtTemperature.getText())).intValue();
          if ((iTemp >= iMinValue) && (iTemp <= iMaxValue)) {
            iValue = iTemp;
            setTemperature(iValue);
          } else {
            txtTemperature.setText("" + iValue); 
          }

        } catch (NumberFormatException e) { 
          txtTemperature.setText("" + iValue); 
        }
      }
    };
    txtTemperature.setFillColor(Color.white);
    txtTemperature.setFilled(false);
    txtTemperature.setLineColor(Color.black);
    theCanvas.add(txtTemperature);

    //make the bulb 
    cirBulb = new CircleShape();
    cirBulb.setFilled(true); 
    cirBulb.setFillColor(Color.red); 
    cirBulb.setLineColor(Color.red); 
    theCanvas.add(cirBulb);

    //draw the labels
    txtMinValue = new TextShape(0, 0, "" + iMinValue);
    txtMinValue.setFilled(false);
    txtMinValue.setLineColor(Color.black);
    theCanvas.add(txtMinValue);

    txtMaxValue = new TextShape(0, 0, "" + iMaxValue);
    txtMaxValue.setFilled(false);
    txtMaxValue.setLineColor(Color.black);
    theCanvas.add(txtMaxValue);
   
    //the bottom line
    lsBottomLine = new LineShape(0, 0, 0, 0);
    txtMinValue.setFilled(false);
    txtMinValue.setLineColor(Color.black);
    theCanvas.add(lsBottomLine);


  }

  /**
   * makeConstraints - set the constraints for all of the siloette
   *                   objects
   */
  private void makeConstraints() {
    rsTube.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          RectangleShape self = (RectangleShape) owner;
          self.setHeight(theCanvas.getHeight() * 3 / 4);
        }
      }
    );

    rsTube.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          RectangleShape self = (RectangleShape) owner;
          self.setWidth(self.getHeight() / 15);
        }
      }
    );

    rsMercury.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          RectangleShape self = (RectangleShape) owner;
          self.setWidth(rsTube.getWidth());
          self.setHeight(cirBulb.getWidth() +
                          ((rsTube.getHeight() - cirBulb.getWidth()) 
                           * (iValue - iMinValue) / (iMaxValue - iMinValue))
                        );
          self.setLeft(rsTube.getLeft());
          self.setBottom(rsTube.getBottom());
        }
      }
    );

    rsTube.addConstraint(
        new silhouette.constraints.alignCenterX(theCanvas)
    );

    rsTube.addConstraint(
        new silhouette.constraints.alignCenterY(theCanvas)
    );

    txtTemperature.addConstraint(
        new silhouette.constraints.alignAfter(rsTube, TEXT_OFFSET)
    );

    txtMinValue.addConstraint(
        new silhouette.constraints.alignBefore(rsTube, TEXT_OFFSET)
    );

    txtMinValue.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          TextShape self = (TextShape) owner;
          self.setCenterY(lsBottomLine.getY1());
        }
      }
    );


    txtMaxValue.addConstraint(
        new silhouette.constraints.alignBefore(rsTube, TEXT_OFFSET)
    );

    txtMaxValue.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          TextShape self = (TextShape) owner;
          self.setCenterY(rsTube.getTop());
        }
      }
    );


    txtTemperature.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          EditableTextShape self = (EditableTextShape) owner;
          self.setCenterY(rsMercury.getTop());
        }
      }
    );

    cirBulb.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          CircleShape self = (CircleShape) owner;
          self.setWidth(rsTube.getWidth() * 3);
          self.setHeight(rsTube.getWidth() * 3);
          self.setCenterX(rsTube.getCenterX());
          self.setCenterY(rsTube.getBottom());
        }
      }
    );

    lsBottomLine.addConstraint(
      new constraint() {
        public void formula(constrainedObject owner) {
          LineShape self = (LineShape) owner;
          self.setX1(rsTube.getLeft());
          self.setY1(rsTube.getBottom() - cirBulb.getWidth());

          self.setX2(rsTube.getRight());
          self.setY2(rsTube.getBottom() - cirBulb.getWidth());
        }
      }
    );


  }

  /**
   * makeInteractors - creates the interactor for the mouse drags and clicks
   *                   (and holds any additional interactors)
   */
  private void makeInteractors() {

    SilInteractor moveMercury = new SilInteractor(
                                new SilMouseEvent(
                                  MouseEvent.MOUSE_PRESSED),
                                new SilMouseEvent(
                                  MouseEvent.MOUSE_RELEASED)
                                                ) {
      public void startAction(InputEvent e) {
          MouseEvent event = (MouseEvent) e;
          iValue = getValueAtY(event.getY());
          txtTemperature.setText(iValue + "");
          rsMercury.setHeight(cirBulb.getHeight() +
                          ((rsTube.getHeight() - cirBulb.getHeight()) 
                           * iValue / (iMaxValue - iMinValue))
                        );
 //         rsMercury.setBottom(rsTube.getBottom());
      }

      public void stopAction(InputEvent e) {
          MouseEvent event = (MouseEvent) e;
          iValue = getValueAtY(event.getY());
          txtTemperature.setText(iValue + "");
          rsMercury.setHeight(cirBulb.getHeight() +
                          ((rsTube.getHeight() - cirBulb.getHeight()) 
                           * iValue / (iMaxValue - iMinValue))
                        );
//          rsMercury.setBottom(rsTube.getBottom());
      }
  
      public void runningAction(InputEvent e) {
        if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
          MouseEvent event = (MouseEvent) e;
          iValue = getValueAtY(event.getY());
          txtTemperature.setText(iValue + "");
          rsMercury.setHeight(cirBulb.getHeight() +
                          ((rsTube.getHeight() - cirBulb.getHeight()) 
                           * iValue / (iMaxValue - iMinValue))
                        );
//          rsMercury.setBottom(rsTube.getBottom());
        }
      }
    };

    rsMercury.addInteractor(moveMercury);

  }

  public int getValueAtY(int y) {
    int iReturn;
  
    int iWinRange;
    int iValueRange;
    int iMouseRangeValue;

    iWinRange = (int) (rsTube.getTop() - lsBottomLine.getY1());
    iValueRange = iMaxValue - iMinValue;
    iMouseRangeValue = (int) (y - lsBottomLine.getY1());

    iReturn = (int) ((iMouseRangeValue * iValueRange) / iWinRange);
    iReturn += iMinValue;

//    System.out.println("Value is: " + iReturn);

    if (iReturn > iMaxValue) {
      iReturn = iMaxValue;
    } else if (iReturn < iMinValue) {
      iReturn = iMinValue;
    }

    return iReturn;
  }

  /**
   * setTemperature - common method that can be used to set the numerical
   *                  value of the temperature and set the mercury height
   */
  public void setTemperature(int i) {
    iValue = i;

          rsMercury.setHeight(cirBulb.getHeight() +
                          ((rsTube.getHeight() - cirBulb.getHeight()) 
                           * iValue / (iMaxValue - iMinValue))
                        );
  }


  /**
   * main - entry point for the application
   */
  public static void main(String args[]) {
    int iParam1, iParam2, iParam3;
                                                                                
    System.out.println("CS594--Lab6\nThermometer Application\n\nSubmitted by Levi D. Smith\nlsmith55@utk.edu\nlsmith@cs.utk.edu");
/*
    System.out.println("*** Note: It seems like it's random wheter or not the constraints are used when the application is started.  If the thermometer is not displayed correctly on startup, then please resize the window and it will display correctly");
*/                                                                                
    if (args.length == 3) {
      try {
        iParam1 = (new Integer(args[0])).intValue();
        iParam2 = (new Integer(args[1])).intValue();
        iParam3 = (new Integer(args[2])).intValue();
                                                                                
        if (iParam2 <= iParam1) {
          System.out.println("\n\n*** minValue must be less than maxValue");
        } else if (iParam3 < iParam1) {
          System.out.println("\n\n*** value must be greater than or equal to minValue");
        } else if (iParam3 > iParam2) {
          System.out.println("\n\n*** value must be less than or equal to maxValue");
        } else {
          new ThermometerApplication(iParam1, iParam2, iParam3);
        }
      } catch (NumberFormatException e) {
        System.out.println("\n\n*** Parameters must be integer numbers");
      }
    } else {
      System.out.println("\n\n*** Usage:\njava ThermometerApplication minValue maxValue value");
 
    }
 
 
  }


}

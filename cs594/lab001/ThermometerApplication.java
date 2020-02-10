/**
 * ThermometerApplication.java - the entry point object for the thermometer
 *                               application 
 *
 * @author Levi D. Smith (lsmith@utk.edu, lsmith@cs.utk.edu)
 * @date August 31, 2003
 * @course CS594 Graphical User Interfaces
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ThermometerApplication {

  private Thermometer theThermometer;

  private JFrame theFrame;

  /**
   * ThermometerApplication - a default contstructor
   */
  public ThermometerApplication() {
    theThermometer = new Thermometer(32, 212, 76);
    setupWindow();

  }

  /**
   * ThermometerApplication - constructor
   *
   * @param i1 - the minimum value for the thermometer
   * @param i2 - the maximum value for the thermometer
   * @param i3 - the value of the thermometer
   */
  public ThermometerApplication(int i1, int i2, int i3) {
    theThermometer = new Thermometer(i1, i2, i3);
    setupWindow();

  }

  /**
   * setupWindow - sets up the window for the application
   */
  private void setupWindow() {
    WindowListener wl;
    theFrame = new JFrame();

    theFrame.setLocation(64, 64);

    wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.hide();
        theFrame.dispose();
        System.exit(0);
      }
    };
    
    theFrame.addWindowListener(wl);
    theFrame.getContentPane().add(theThermometer);

    theFrame.setTitle("Thermometer Application");
    theFrame.pack();
    theFrame.show();
    

  }


  /**
   * main - the entry point for the application
   */
  public static void main(String args[]) {
    int iParam1, iParam2, iParam3;

    System.out.println("CS594--Lab1\nThermometer Application\n\nSubmitted by Levi D. Smith\nlsmith55@utk.edu\nlsmith@cs.utk.edu");

    if (args.length == 3) {
      try {
        iParam1 = (new Integer(args[0])).intValue();
        iParam2 = (new Integer(args[1])).intValue();
        iParam3 = (new Integer(args[2])).intValue();

        if (iParam2 <= iParam1) {
          System.out.println("\n\n*** minValue must be less than maxValue");
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

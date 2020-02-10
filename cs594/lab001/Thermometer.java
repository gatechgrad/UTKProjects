/**
 * Thermometer.java - contains thermometer properties and methods for drawing
 *                    the thermometer
 *
 * @author Levi D. Smith (lsmith@utk.edu, lsmith@cs.utk.edu)
 * @date August 31, 2003
 * @course CS594 Graphical User Interfaces
 */

import java.awt.*;
import javax.swing.*;


class Thermometer extends JComponent implements ThermometerInterface {

  public static final Font THERMOMETER_FONT = new Font("Helvetica", Font.BOLD, 12);
  public static final Color MERCURY_COLOR = Color.red;
  public static final int TEXT_OFFSET = 5;

  private int iMinValue;
  private int iMaxValue;
  private int iValue;

  private int iViewWidth = 612;
  private int iViewHeight = 430;

  /**
   * Thermometer - constructor
   *
   * @param i1 - the min value
   * @param i2 - the max value
   * @param i3 - the mercury value
   */
  public Thermometer(int i1, int i2, int i3) {
    iMinValue = i1;
    iMaxValue = i2;
    iValue = i3;

    setPreferredSize(new Dimension(iViewWidth, iViewHeight));
  }

  /**
   * drawThermometer - draws the thermometer to the graphics object
   *
   * @param g - the graphics object to draw the thermometer
   */
  private void drawThermometer(Graphics g) {
    int iThermometerHeight;
    int iThermometerWidth;
    int iBulbSize;
    int iMercuryHeight;

    int iThermometerX;
    int iThermometerY;
    int iBottomLocation;
    
    FontMetrics fm;

    iViewWidth = getWidth(); 
    iViewHeight = getHeight(); 
 
    iThermometerHeight = iViewHeight * 3 / 4;
    iThermometerWidth = iViewHeight * 1 / 15;
    iBulbSize = iThermometerWidth * 3;
    
    iThermometerX = (iViewWidth - iThermometerWidth) / 2;
    iThermometerY = (iViewHeight - iThermometerHeight) / 2;

    iBottomLocation = iThermometerY + iThermometerHeight - iBulbSize;

    g.setFont(THERMOMETER_FONT);
    fm = g.getFontMetrics();
 
    //draw the outline of the thermometer
    g.setColor(Color.black);

    g.drawRect(iThermometerX, iThermometerY, iThermometerWidth, iThermometerHeight);

    //draw the mercury
    g.setColor(MERCURY_COLOR);
    iMercuryHeight = getMercuryHeight(iThermometerY, iThermometerY + 
                     iThermometerHeight, iBottomLocation, iMinValue, iMaxValue, 
                     iValue);
    g.fillRect(iThermometerX, iThermometerHeight + iThermometerY 
               - iMercuryHeight, iThermometerWidth, iMercuryHeight);
    g.drawRect(iThermometerX, iThermometerHeight + iThermometerY 
               - iMercuryHeight, iThermometerWidth, iMercuryHeight);

    //draw the bulb
    g.setColor(MERCURY_COLOR);
    g.fillOval((iViewWidth - iBulbSize) / 2, iThermometerY + iThermometerHeight - (iBulbSize / 2), iBulbSize, iBulbSize);

    //draw the text labels
    g.setColor(Color.black);
    g.drawLine(iThermometerX, iBottomLocation, iThermometerX + 
               iThermometerWidth, iBottomLocation); 
    //I'm using getAscent() instead of getHeight() to make the numbers
    //vertically centered, since no numbers extend below the baseline
    g.drawString(iValue + "", iThermometerX + iThermometerWidth + TEXT_OFFSET, 
                 iThermometerY + iThermometerHeight - iMercuryHeight + (fm.getAscent() / 2)); 


    g.drawString(iMaxValue + "", iThermometerX - fm.stringWidth(iMaxValue + "") - TEXT_OFFSET, iThermometerY + (fm.getAscent() / 2)); 


    g.drawString(iMinValue + "", iThermometerX - fm.stringWidth(iMinValue + "") - TEXT_OFFSET, iBottomLocation + (fm.getAscent() / 2)); 

  }


  /**
   * getMercuryHeight - returns the number of pixels high that the mercury
   *                    should be drawn
   *
   * @param iThermometerTop - the location of the top of the thermometer
   * @param iThermometerBottom - the location of the bottom of the thermometer
   * @param iMinLocation - the location of the min value on the thermometer
   * @param iMinValue - the minimum temperature
   * @param iMaxValue - the maximum temperature
   * @param iValue - the temperature value
   * @return the number of pixels high that the mercury should be drawn
   */
  private int getMercuryHeight(int iThermometerTop, int iThermometerBottom,
                               int iMinLocation, int iMinValue, int iMaxValue,
                               int iValue) {
    int iRange;
    int iReturn;


    iRange = iMinLocation - iThermometerTop;
    iReturn = ((iValue - iMinValue) * iRange) / (iMaxValue - iMinValue); 
    iReturn += (iThermometerBottom - iMinLocation);


    if (iReturn < 0) {
      iReturn = 0;
    } else if (iReturn > (iThermometerBottom - iThermometerTop)) {
      iReturn = iThermometerBottom - iThermometerTop;
    }

    return iReturn;
  }

  /**
   * paint - calls the thermometer drawing method
   *
   * @param  g - the graphics object
   */
  public void paint(Graphics g) {
    drawThermometer(g);
  }


  /**
   * paintComponent - calls the thermometer drawing method
   *
   * @param g - the graphics object
   */
  public void paintComponent(Graphics g) {
    drawThermometer(g);
  }

  /**
   * setValue - sets the degree value of the thermometer
   */
  public void setValue(int i) {
    iValue = i;
  }
}

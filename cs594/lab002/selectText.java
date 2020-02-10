import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class selectText extends JComponent {
  public static final int LINE_SPACING = 10;
  public static final int BORDER_WIDTH = 10;
 
  private int iCursorX, iCursorY;
  private Font theFont;
  private String strInputText[];
  private int iSelStr, iSelChar;

  private JFrame theFrame;

  public selectText(String str[], Font f, int i1, int i2) {
    iCursorX = i1;
    iCursorY = i2;
    theFont = f;
    
    strInputText = str;
    setupWindow();

  }

  private void setupWindow() {
    Dimension dScreen;
    WindowListener wl;

    theFrame = new JFrame();
    theFrame.getContentPane().add(this);
    theFrame.pack();
    theFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 
                          theFrame.getWidth()) / 2,
                         (Toolkit.getDefaultToolkit().getScreenSize().height - 
                          theFrame.getHeight()) / 2);
    wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        theFrame.hide();
        theFrame.dispose();
        System.exit(0);
      }
    };
    theFrame.addWindowListener(wl); 
    theFrame.setTitle("Select Text Application");
    theFrame.show();
  
  }

  public void drawText(Graphics g) {
    int i;
    int x, y;
    int iFontSpacing;
    FontMetrics fm;
    int iDisplayWidth;
    int iCharPos;
    int iCharX;
    int iCursorLineX;
  
    g.setFont(theFont);
    fm = g.getFontMetrics();    
 
    iFontSpacing = fm.getHeight();

    y = 0;
    x = BORDER_WIDTH;

    //if the mouse was clicked, then reset the selected characters
    if ((iCursorX >= 0) && (iCursorY >= 0)) {
      iSelStr = -1;
      iSelChar = -1;
    }

    iDisplayWidth = 0;
    for (i = 0; i < strInputText.length; i++) {

      y += LINE_SPACING;

      y += iFontSpacing;

      g.setColor(Color.black);
      iCursorLineX = -1;
      if ((iCursorX >= 0) && (iCursorY >= 0)) {
        if ((iCursorY < y) && (iCursorY > y - iFontSpacing)) {  
          if ((iCursorX > BORDER_WIDTH) && (iCursorX < fm.stringWidth(strInputText[i]) + BORDER_WIDTH + 5)) {
            for (iCharPos = 0; iCharPos < (strInputText[i]).length() + 1; iCharPos++) { 
              iCharX = BORDER_WIDTH + fm.stringWidth(strInputText[i].substring(0, iCharPos));    
              if ((iCursorX > iCharX)) {
                iSelStr = i;
                iSelChar = iCharPos; 
              }
            }

          }
        }
      }


      if ((i == iSelStr) && (iSelChar >= 0)) {
        iCursorLineX = BORDER_WIDTH + fm.stringWidth(strInputText[i].substring(0, iSelChar));
        g.drawLine(iCursorLineX, y - fm.getMaxDescent(), iCursorLineX, y - fm.getHeight()); 
      }

      g.drawString(strInputText[i], x, y - fm.getMaxDescent() + 1);
  
      if (fm.stringWidth(strInputText[i]) > iDisplayWidth) {
        iDisplayWidth = fm.stringWidth(strInputText[i]);
      }



      
    }

    iCursorX = -1;
    iCursorY = -1;

    iDisplayWidth += (2 * BORDER_WIDTH);
    y += LINE_SPACING;
    setPreferredSize(new Dimension(iDisplayWidth, y));
    theFrame.pack();
  }

  public void paint(Graphics g) {
    drawText(g);
  }

  public void paintComponent(Graphics g) {
    drawText(g);
  }

  public static void main(String args[]) {
    int i;

    boolean paramsOkay;
    int iPoint;
    int x, y;
   
    Font fntTemp;
    String strTemp[];

    x = 0;
    y = 0;
    iPoint = 0; 

    paramsOkay = true;

    if (args.length < 5) {
      paramsOkay = false;
    }
 
    if (paramsOkay) {
      try {
        iPoint = (new Integer(args[1])).intValue();
        x = (new Integer(args[2])).intValue();
        y = (new Integer(args[3])).intValue();

      } catch (NumberFormatException e) {
        System.out.println("\npointSize must be an integer");
        System.out.println("\nx must be an integer");
        System.out.println("\ny must be an integer");
        paramsOkay = false;
      }
    }

    if (paramsOkay) {
      strTemp = new String[args.length - 4];
      for (i = 4; i < args.length; i++) {
        strTemp[i - 4] = args[i];
      }

      fntTemp = new Font(args[0], Font.PLAIN, iPoint);
      new selectText(strTemp, fntTemp, x, y);
    } else {
      System.out.println("\n\n*** Usage:\njava selectText fontName pointSize x y string1 string2 ... stringN");

    }
  }



}

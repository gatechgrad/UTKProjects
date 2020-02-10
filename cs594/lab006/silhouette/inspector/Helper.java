package silhouette.inspector;


import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import silhouette.shapes.*;


public class Helper extends JFrame {


  /* frame and canvas */
  static Helper     	    helperFrame;;

  static Color		    defColor = Color.black;
  static Font		    defFont  = new Font (
                                   "Lucida Sans Typewriter", Font.PLAIN, 24);
  static final String helperText = "<html>\n" +
  	"<font size=-1>" +
	"<br>\n"+
  	"<b>      Inspector Window</b><br>\n" +
	"<ul><li>Click on the value to edit property's value.  For a <b>numerical</b><br>\n" +
	"    or <b>string</b> value, clicking on it brings up cursor. Press <br>\n" +
	"    ENTER to finish editing. For a <b>font</b> value, clicking the font <br>\n" +
	"    name brings up font selector. For a <b>color</b> value, click<br>\n" +
	"    the color chip to show color selector. If value is edited<br>\n" +
	"    during the debugging process, it would return to the old <br>\n" +
	"    value,  because value should not be changed before one <br> \n" +
	"    debugging cycle ends. <br> \n" +
	"<li>Double click the name of the parent object(which is shown<br>\n" +
	"    in blue) changes the inspector to show the properties of that <br>\n" +
	"    object. Clicking Back button will bring you back to the <br>\n" +
	"    previous object. <br>\n" +
	"<li>Right click on property name, a pop up menu will be brought<br>\n"+ 
    "    up. If there's constraint associated with that property, <br>\n"+
	"    the property's name is colored purple and the menu item <br>\n"+
	"    is enabled. Then click the menu item can launch up <br>\n"+
	"    the constraint debug window.<br></ul>\n"+
	"<p>\n"+
	"<b>Debug Window</b> <br>\n" +
	"<li>The tiny circle stands for property and the tiny rectangle<br>\n"+
	"    stands for constraint<br>\n"+
	"<li>Black color of circle or rectangle stands for outdated<br>\n" +
	"    property or constraint, green color of circle or retangle<br>\n"+
	"    means that the constraint evaluation process stops there.<br>\n"+
	"<li>Normal edges are colored black, newly added edges are <br>\n"+
	"    colored blue, newly deleted edges are colored red.<br>\n" +
	"<li>Right click on the rectangle brings up a popup menu,<br>\n"+
	"    by which you can add a breakpoint or delete a breakpoint<br>\n"+
	"    on that constraint.<br>\n"+
	"<li>Clicking Step button means the evaluation process continues<br>\n"+
    "    then suspends at next property or constraint. The same as <br>\n"+
	"    pressing 'Alt+S'. <br>\n"+
	"<li>Clicking Next button means the evaluation process continues<br>\n"+
    "    then suspends until it has traversed the next edge in the <br>\n"+
	"    constraint graph and all of its successor edges. The same <br>\n"+
	"    as pressing 'Alt+N'. <br>\n"+
	"<li>Click Continue button means evaluation process continues<br>\n"+
	"    until next breakpoint is reached. The same as pressing <br>\n"+
	"    'Alt+C'. <br>\n"+
	"<li>When one cycle of evaluation process finishes, Debug window<br>\n"+        "    will show the original constraint graph, and all existed <br>\n"+
	"    Inspector windows will be updated.<br>\n"+
	"<li>Double click the circle will bring up an inspector window <br>\n"+
	"    which shows the owner object of that property. <br>\n"+
	"</font>\n"+
        "</html>\n";

  static JLabel 	    theLabel = new JLabel(helperText);
  
  /**
   *Initialization routine,  build up the heavy part
   */
  protected void INITWindow() {
    JPanel sPanel = new JPanel();
    sPanel.setPreferredSize(new Dimension(440, 900));


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize  = helperFrame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height   = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width    = screenSize.width;
    }
    //helperFrame.setBounds( 0, 0, 400, 400 );
    // decides the position of the frame
    helperFrame.setLocation(
      (screenSize.width  - frameSize.width )/2 - 100,
      (screenSize.height - frameSize.height)/2 + 50);
    helperFrame.setTitle("Helper Window");


    JScrollPane sp = new JScrollPane(sPanel);
    sp.setPreferredSize(new Dimension(600, 400));

    helperFrame.getContentPane().add(sp, BorderLayout.CENTER);

    sPanel.add(theLabel);

    // override the "close window" event
    helperFrame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
    helperFrame.addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        helperFrame.setVisible(false);  // hide it.
        helperFrame.hide();
      }  
    });

  }

  // class initialization
//  static {
//    INITWindow();
//  }

  /**
   *bring it up the frame:
   */
  protected void ShowNewFrame () {
    helperFrame.pack();
    helperFrame.setVisible(true);
    helperFrame.setState(helperFrame.NORMAL);

  }

  /**
   * constructor
   */
  public Helper () {
	super();
  }

  public static void showHelper() {
	if(Helper.helperFrame == null) {
		Helper.helperFrame = new Helper();
		Helper.helperFrame.INITWindow();
		Helper.helperFrame.ShowNewFrame();
	}
	else {
		Helper.helperFrame.ShowNewFrame();
	}
	
  }
}

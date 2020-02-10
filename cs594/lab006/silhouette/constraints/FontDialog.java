package silhouette.constraints;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;


/*
 * FontDialog.java
 * Writen by Weizhong Ji at Nov, 2000 for his PILOT project
 * Instructor: Brad Vander Zanden
 *
 * purpose: Font Chooser,  preview provided
 */

public class FontDialog extends JDialog {

  // some local default values
  static String			DefaultFontName  = "Lucida Sans Typewriter";
  static int			DefaultFontStyle = Font.PLAIN;
  static int			DefaultFontSize  = 12;

  private static FontDialog  	dialog;
  private static Font 	newFont;
  private JList 		list, list1, list2;
  private JCheckBox		BoldFont, ItalicFont;
  private JLabel 		labelText;

  static String[]		FontNames;
  static String[]		FontSizes = {
    "8", "12", "14", "16", "18", "24", "48", "64" };

  class CheckListener implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      DefaultFontStyle = Font.PLAIN;
      if (BoldFont.isSelected())   { DefaultFontStyle += Font.BOLD;   }
      if (ItalicFont.isSelected()) { DefaultFontStyle += Font.ITALIC; }
      labelText.setFont(new Font( DefaultFontName, DefaultFontStyle,
          DefaultFontSize) );
    }
  }

  private CheckListener     	  checklistener = new CheckListener();

  static {
    newFont     = new Font ( DefaultFontName, DefaultFontStyle,
      DefaultFontSize);
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    FontNames   = env.getAvailableFontFamilyNames();
    Frame frame = JOptionPane.getFrameForComponent(null); //JDialog thing
    dialog      = new FontDialog(frame, "Select Font");
  }

  // public method for user to get font ()
  public static Font showDialog(Component comp, Font InitFont) {
    dialog.setValue(InitFont);
    dialog.setLocationRelativeTo(comp);       	// JDialog Thing
    dialog.setVisible(true);
    return newFont;
  }

  // set the start up values
  private void setValue(Font InitFont)
  {
    if (InitFont != null) {
      DefaultFontName  = InitFont.getFontName();
      DefaultFontSize  = InitFont.getSize();
      DefaultFontStyle = InitFont.getStyle();
    }

    list1.setSelectedValue(DefaultFontName, true);
    list2.setSelectedValue(new Integer(DefaultFontSize).toString(), true);

    // font style
    if (DefaultFontStyle == Font.BOLD) {
      BoldFont.setSelected(true);
    } else if (DefaultFontStyle == Font.ITALIC) {
      ItalicFont.setSelected(true);
    } else if (DefaultFontStyle == Font.ITALIC + Font.BOLD) {
      BoldFont.setSelected(true);
      ItalicFont.setSelected(true);
    }
  }

  // private constructor
  private FontDialog(
    Frame frame, String title)
  {
    super(frame, title, true);  //JDialog thing

    //buttons
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FontDialog.dialog.setVisible(false);
      }
    });

    final JButton okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FontDialog.newFont =
        new Font( DefaultFontName, DefaultFontStyle, DefaultFontSize);
        FontDialog.dialog.setVisible(false);
      }
    });
    getRootPane().setDefaultButton(okButton);

    // font names
    list1 = new JList(FontNames);
    list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list1.addMouseListener( new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JList theList = (JList) e.getSource();
        ListModel model = theList.getModel();

        int index = theList.locationToIndex(e.getPoint());
        String itemString = (String)model.getElementAt(index);
        DefaultFontName = itemString;
        labelText.setFont(new Font( DefaultFontName, DefaultFontStyle,
            DefaultFontSize) );
      }
    });
    JScrollPane list1Scroller = new JScrollPane(list1);
    list1Scroller.setPreferredSize(new Dimension(250, 200));
    list1Scroller.setMinimumSize(new Dimension(250, 80));
    list1Scroller.setAlignmentX(LEFT_ALIGNMENT);

    JPanel list1Pane = new JPanel();
    list1Pane.setLayout(new BoxLayout(list1Pane, BoxLayout.Y_AXIS));

    JLabel labelName = new JLabel("Font Name: ");
    labelName.setLabelFor(list1);
    list1Pane.add(labelName);
    list1Pane.add(Box.createRigidArea(new Dimension(0,5)));
    list1Pane.add(list1Scroller);
    list1Pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    // Font Size
    list2 = new JList(FontSizes);
    list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list2.addMouseListener( new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
      JList theList = (JList) e.getSource();
      ListModel model = theList.getModel();

      int index = theList.locationToIndex(e.getPoint());
      String itemString = (String)model.getElementAt(index);
      DefaultFontSize = new Integer(itemString).intValue();
      labelText.setFont(new Font( DefaultFontName, DefaultFontStyle,
          DefaultFontSize) );
      }
    });

    JScrollPane list2Scroller = new JScrollPane(list2);
    list2Scroller.setPreferredSize(new Dimension(60, 200));
    list2Scroller.setMinimumSize(new Dimension(60, 80));
    list2Scroller.setAlignmentX(LEFT_ALIGNMENT);

    JPanel list2Pane = new JPanel();
    list2Pane.setLayout(new BoxLayout(list2Pane, BoxLayout.Y_AXIS));
    JLabel labelSize = new JLabel("Font Size: ");
    labelSize.setLabelFor(list2);
    list2Pane.add(labelSize);
    list2Pane.add(Box.createRigidArea(new Dimension(0,5)));
    list2Pane.add(list2Scroller);
    list2Pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    // Font Style
    BoldFont   = new JCheckBox("Bold");
    BoldFont.addItemListener( checklistener );
    ItalicFont = new JCheckBox("Italic");
    ItalicFont.addItemListener( checklistener );

    JPanel checkPane = new JPanel();
    checkPane.setLayout(new BoxLayout(checkPane, BoxLayout.Y_AXIS));
    checkPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    JLabel labelType = new JLabel("Font Type: ");

    checkPane.add(labelType);
    checkPane.add(Box.createRigidArea(new Dimension(0,5)));
    checkPane.add(BoldFont);
    checkPane.add(Box.createRigidArea(new Dimension(0,10)));
    checkPane.add(ItalicFont);

    /* Button Panel */

    //Lay out the buttons from left to right.
    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
    buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    buttonPane.add(Box.createHorizontalGlue());
    buttonPane.add(cancelButton);
    buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
    buttonPane.add(okButton);

    /* Preview Pane */
    JPanel previewPane = new JPanel();
    labelText = new JLabel("ABCDEFGHIJK abcdefghijk");
    labelText.setFont(new Font( DefaultFontName, DefaultFontStyle,
        DefaultFontSize) );
    previewPane.add(labelText);

    //Put everything together, using the content pane's BorderLayout.
    Container contentPane = getContentPane();
    //contentPane.setPreferredSize(new Dimension(500, 400));
    contentPane.add(previewPane, BorderLayout.NORTH);
    contentPane.add(list1Pane, BorderLayout.WEST);
    contentPane.add(checkPane, BorderLayout.CENTER);
    contentPane.add(list2Pane, BorderLayout.EAST);
    contentPane.add(buttonPane, BorderLayout.SOUTH);

    pack();
  }
}

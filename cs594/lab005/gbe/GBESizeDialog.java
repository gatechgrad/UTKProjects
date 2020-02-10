import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GBESizeDialog extends JDialog implements ActionListener {

  public static final String BUT_OK_LABEL = "OK";
  public static final String BUT_CANCEL_LABEL = "Cancel";

  private GameBoardEditor theGBE;
  private JSlider sldrRows;
  private JSlider sldrCols;
  private int iRows;
  private int iCols;

  public GBESizeDialog(JFrame f, GameBoardEditor gbe) {
    super(f, "Create New Game Board", true);
    theGBE = gbe;
    iRows = -1;
    iCols = -1;

    makeDialog();
    this.pack();
  }

  public void makeDialog() {
    JPanel pnl;
    JPanel pnlButtons;
    JButton butOK, butCancel;

    pnl = new JPanel();
    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

    pnl.add(Box.createVerticalStrut(32));

    pnl.add(new JLabel("Rows"));

    sldrRows = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
    sldrRows.setPaintTicks(true);
    sldrRows.setPaintLabels(true);
    sldrRows.setMajorTickSpacing(5);
    sldrRows.setMinorTickSpacing(1);
    pnl.add(sldrRows);

    pnl.add(Box.createVerticalStrut(32));

    pnl.add(new JLabel("Columns"));

    sldrCols = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
    sldrCols.setPaintTicks(true);
    sldrCols.setPaintLabels(true);
    sldrCols.setMajorTickSpacing(5);
    sldrCols.setMinorTickSpacing(1);
    pnl.add(sldrCols);

    pnl.add(Box.createVerticalStrut(32));
 
    pnlButtons = new JPanel();

    butOK = new JButton(BUT_OK_LABEL);
    butOK.addActionListener(this);
    pnlButtons.add(butOK);

    butCancel = new JButton(BUT_CANCEL_LABEL);
    butCancel.addActionListener(this);
    pnlButtons.add(butCancel);

    pnl.add(pnlButtons);

    this.getContentPane().add(pnl);

  }

  public int getRows() {
    return iRows;
  }


  public int getCols() {
    return iCols;
  }

  public void actionPerformed(ActionEvent e) {
    String strCommand;
    
    strCommand = e.getActionCommand();

    if (strCommand.equals(BUT_OK_LABEL)) {
      if ((sldrRows.getValue() > 0) &&
          (sldrCols.getValue() > 0)
         ) {
    //    theGBE.createNew(sldrRows.getValue(), sldrCols.getValue());
        iRows = sldrRows.getValue();
        iCols = sldrCols.getValue();
        this.hide();
      } else {
        JOptionPane.showMessageDialog (
                  this, "Rows and Columns must be greater than zero", 
                  "ERROR", 
                   JOptionPane.ERROR_MESSAGE);
      }
    } else if (strCommand.equals(BUT_CANCEL_LABEL)) {
      this.hide();
      
      theGBE.quit();
    }
  }
}

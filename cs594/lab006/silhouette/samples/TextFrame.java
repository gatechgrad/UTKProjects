package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;

/**
 * This class shows some sample text strings drawn in different colors
 * and different fonts.
 */
public class TextFrame extends JFrame {

  private CanvasShape canvas;

  public TextFrame() {
    canvas = new CanvasShape();

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("TextFrame");

    // create a blue colored text string that is set in a white box
    TextShape textbox = new TextShape(20, 20, "Brad");
    textbox.setFillColor(Color.white);
    textbox.setFilled(true);
    textbox.setLineColor(Color.blue);
    canvas.add(textbox);

    // create a normal colored text string with a 48 point font
    Font newFont = textbox.getFont().deriveFont(48.0f);
    TextShape nextbox = new TextShape(20, 80, "Brad", newFont);
    canvas.add(nextbox);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
	dispose();
	System.exit(0);
      }
    });
  }

  public static void main(String argv[]) {

    TextFrame mywindow = new TextFrame();

    mywindow.pack();
    mywindow.show();
    return;
  }

}

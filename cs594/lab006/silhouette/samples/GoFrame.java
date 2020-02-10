package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.samples.ColorPaletteCanvas;
import silhouette.shapes.*;

public class GoFrame extends JFrame {

  private ColorPaletteCanvas canvas;

  public GoFrame() {
    canvas = new ColorPaletteCanvas();

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("GoFrame");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
	dispose();
	System.exit(0);
      }
    });
  }

  public static void main(String argv[]) {

    GoFrame mywindow = new GoFrame();

    mywindow.pack();
    mywindow.show();
  }

}

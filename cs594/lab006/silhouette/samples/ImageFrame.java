package silhouette.samples;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import silhouette.shapes.*;

/**
 * This application displays a gif image and resizes the canvas to be
 * the same size as the gif image
 */
public class ImageFrame extends JFrame {

  private CanvasShape canvas;

  public ImageFrame() {
    canvas = new CanvasShape();

    getContentPane().add (canvas.getSilPanel());
    this.setTitle("ImageFrame");
    ImageShape Imagebox = new ImageShape("/sunshine/homes/bvz/www-home/bvz.gif");
    canvas.add(Imagebox);
    canvas.setPreferredSize(new Dimension((int)Imagebox.getWidth(),
					  (int)Imagebox.getHeight()));

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
	dispose();
	System.exit(0);
      }
    });
  }

  public static void main(String argv[]) {

    ImageFrame mywindow = new ImageFrame();

    mywindow.pack();
    mywindow.show();
  }

}

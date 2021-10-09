import Shapes.MainApplet;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @author Maheswaran Sathiamoorthy
 *
 */
public class StoreImage extends MainApplet{

  int circleRadius = 10;
  private final int MAX_X = 600;
  private final int MAX_Y = 350;
  private BufferedImage bufferedImage;
  private final GraphicsConfiguration gConfig = GraphicsEnvironment
      .getLocalGraphicsEnvironment().getDefaultScreenDevice()
      .getDefaultConfiguration();


  // If you plan to show on the screen on an applet, apart from saving as an image
  @Override
  public void start() {
    setSize(MAX_X, MAX_Y);
    bufferedImage = create(MAX_X, MAX_Y, true);
  }


  // If you plan to show on the screen on an applet, apart from saving as an image
  @Override
  public void paint(Graphics g) {
    storeImage();
  }


  public void storeImage() {
    BufferedImage image = create(MAX_X, MAX_Y, true);
    Graphics2D g = image.createGraphics();
    // you can disable this if you don't want smooth graphics
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.dispose();
    try {
      ImageIO.write(image, "png", new File("/home/shimaa/Downloads/file$$$.png"));
    } catch (IOException e) {
   }
  }

  private BufferedImage create(final int width, final int height,
      final boolean alpha) {
    BufferedImage buffer = gConfig.createCompatibleImage(width, height,
              alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    return buffer;
  }
} 
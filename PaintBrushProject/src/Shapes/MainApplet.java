/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author shimaa
 */
public class MainApplet extends Applet {

    private static final long serialVersionUID = 1L;
    int x, x1, x2, y, y1, y2;
    int width = 0;
    int height = 0;
    int newWidth = 10;
    int newHeight = 10;
    boolean keepX2 = false;
    boolean keepY2 = false;
    Vector<GeoShape> shapesVector = new Vector<GeoShape>();
    int j = 0;
    boolean clearPressed = false;
    boolean dottedPressed = false;
    boolean filledPressed = false;
    boolean undoPressed = false;
    //POSSIBLE MODIFICATIONS: ARRAY OF BOOLEANS
    Button bttnLine, bttnRec, bttnOval, freeDrawing, eraser, clear, undo, save,
            btnMagenta, bttnPink, btnBlack, btnOrange, bttnDarkGrey, btnLightGrey;
    Button btnRed, btnGreen, bttnYellow, btnBlue, bttnCyan, btnGray;
    Checkbox btnDotted, btnFill;
    static Color currentColor = Color.black; //default color
    char currentShape;
    int counter;
    BufferedImage image;

    private final int MAX_X = 1200;
    private final int MAX_Y = 800;
    private BufferedImage bufferedImage;
    private final GraphicsConfiguration gConfig = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDefaultConfiguration();

    @Override
    public void start() {
        super.start();

    }

    public void init() {
        //initialize GUI
        this.setSize(1200, 800);
        this.setBackground(Color.WHITE);

        btnRed = new Button("  ");
        bttnYellow = new Button("  ");
        btnGreen = new Button("  ");
        btnBlue = new Button("  ");
        bttnCyan = new Button("  ");
        btnGray = new Button("  ");

        btnMagenta = new Button("  ");
        bttnPink = new Button("  ");
        btnOrange = new Button("  ");
        btnBlack = new Button("  ");
        bttnDarkGrey = new Button("  ");
        btnLightGrey = new Button("  ");

        bttnLine = new Button("Line");
        bttnRec = new Button("Rectangle");
        bttnOval = new Button("Oval");
        freeDrawing = new Button("Free Hand");
        // Icon icon = new ImageIcon("/home/shimaa/Downloads/icon-eraser.png");
        eraser = new Button("Eraser");
        clear = new Button("Clear");
        undo = new Button("Undo");
        save = new Button("Save");
        btnDotted = new Checkbox("Dotted");
        btnFill = new Checkbox("Fill");

        btnMagenta.setBackground(Color.MAGENTA);
        bttnPink.setBackground(Color.PINK);
        btnOrange.setBackground(Color.ORANGE);
        btnBlack.setBackground(Color.BLACK);
        bttnDarkGrey.setBackground(Color.DARK_GRAY);
        btnLightGrey.setBackground(Color.LIGHT_GRAY);

        btnRed.setBackground(Color.RED);
        bttnYellow.setBackground(Color.YELLOW);
        btnGreen.setBackground(Color.GREEN);
        btnBlue.setBackground(Color.BLUE);
        bttnCyan.setBackground(Color.CYAN);
        btnGray.setBackground(Color.GRAY);
        btnDotted.setVisible(true);
        
        btnFill.setVisible(true);

        //Colors
        add(btnRed);
        add(bttnYellow);
        add(btnGreen);
        add(btnBlue);
        add(bttnCyan);
        add(btnGray);
        add(btnMagenta);
        add(bttnPink);
        add(btnOrange);
        add(bttnDarkGrey);
        add(btnLightGrey);
        add(btnBlack);
        

        //Shapes
        add(bttnLine);
        add(bttnRec);
        add(bttnOval);
        add(freeDrawing);
        add(eraser);
        add(clear);
        add(undo);
        add(save);
        add(btnDotted);
        add(btnFill);

        inintializeActionButtons();
        MouseListener_ m = new MouseListener_();
        MotionListener ml = new MotionListener();
        this.addMouseListener(m);
        this.addMouseMotionListener(ml);

    }

    public void inintializeActionButtons() {
        bttnLine.addActionListener((ActionEvent ev) -> {
            currentShape = 'l';
        });

        bttnRec.addActionListener((ActionEvent e) -> {
            currentShape = 'r';
        });

        bttnOval.addActionListener((ActionEvent ev) -> {
            currentShape = 'c';
        });

        freeDrawing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape = 's';
                repaint();
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearPressed = true;
                repaint();
            }

        });

        eraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape = 'e';
            }
        });

        btnRed.addActionListener((ActionEvent ev) -> {
            currentColor = Color.red;
            repaint();
        });

        bttnYellow.addActionListener((ActionEvent ev) -> {
            currentColor = Color.yellow;
            repaint();
        });

        btnGreen.addActionListener((ActionEvent ev) -> {
            currentColor = Color.green;
            repaint();
        });

        btnBlue.addActionListener((ActionEvent ev) -> {
            currentColor = Color.blue;
            repaint();
        });

        bttnCyan.addActionListener((ActionEvent ev) -> {
            currentColor = Color.cyan;
            repaint();
        });

        btnGray.addActionListener((ActionEvent ev) -> {
            currentColor = Color.gray;
            repaint();
        });

        btnDotted.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    dottedPressed = true;
                } else {
                    dottedPressed = false;
                }

            }
        });

        btnFill.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    filledPressed = true;
                } else {
                    filledPressed = false;
                }

            }
        });

        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoPressed = true;
                if (shapesVector.size() > 0) {
                    shapesVector.removeElementAt(shapesVector.size() - 1);
                } else {
                    System.out.println("There is nothing to Undo");
                }
                repaint();
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                currentShape = 'n';
                repaint();

            }
        });
    }

//    public void storeImage() {
//
//        try {
//            ImageIO.write(image, "png", new File("/Users/Yourname/Documents/file.png"));
//        } catch (IOException e) {
//        }
//    }
    @Override
    public void paint(Graphics g) {
        float[] dash = {10, 10, 10};
        Graphics2D g2d = (Graphics2D) g.create();
        image = create(MAX_X, MAX_Y, true);
        Graphics2D gImage = image.createGraphics();
        gImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(currentColor);

        if (clearPressed) {
            shapesVector = new Vector<GeoShape>();
            clearPressed = false;
        }

        //the old draw
        System.out.println(shapesVector.size());
        for (j = 0; j < shapesVector.size(); j++) {
            g2d.setColor(shapesVector.get(j).getColor());
            if (shapesVector.get(j).getDottedState()) //cap_butt The decoration applied to the ends of unclosed subpaths and dash segments
            // join_level The decoration applied at the intersection of two path segments and at the intersection of the endpoints of a subpath that is closed 
            {
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10, dash, 10));
            } else {
                g2d.setStroke(new BasicStroke(0));
            }

            switch (shapesVector.get(j).getShapeName()) {
                case 'l':
                    g2d.drawLine(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),
                            shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                    break;
                case 'r':

                    // x2 is width and y2 is height
                    g2d.drawRect(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),
                            shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                    if (shapesVector.get(j).isFilled()) {
                        g2d.fillRect(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),
                                shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                    }
                    break;
                case 'c':
                    // x2 is width and y2 is height
                    g2d.drawOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),
                            shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                    if (shapesVector.get(j).isFilled()) {
                        g2d.fillOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),
                                shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                    }
                    break;
                case 'e':
                    g2d.fillRect(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(), newWidth, newHeight);

            }

        }
        if (dottedPressed) {
            g2d.setStroke(new BasicStroke(0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10, dash, 10));
        }

        switch (currentShape) {
            case 'e':
                g.setColor(Color.black);
                g.drawRect(x2, y2, newWidth, newHeight);
                g.setColor(getBackground());
                g.fillRect(x2, y2, newWidth, newHeight);
                break;

            case 'r':
                x = (x1 - x2) < 0 ? x1 : x2;
                y = (y1 - y2) < 0 ? y1 : y2;
                g2d.drawRect(x, y, width, height);
                if (filledPressed) {
                    g.setColor(currentColor);
                    g.fillRect(x, y, width, height);
                }
                width = height = 0;
                break;
            case 'c':
                x = (x1 - x2) < 0 ? x1 : x2;
                y = (y1 - y2) < 0 ? y1 : y2;
                g2d.drawOval(x, y, width, height);
                if (filledPressed) {
                    g.setColor(currentColor);
                    g.fillOval(x, y, width, height);
                }
                width = height = 0;
                break;
            case 's':
                g.drawLine(x1, y1, x2, y2);
                break;
            case 'n':
                System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyy");
                setSize(MAX_X, MAX_Y);
                bufferedImage = create(MAX_X, MAX_Y, true);
                try {
                    ImageIO.write(image, "png", new File("/home/shimaa/Downloads/iconwwwew.png"));

                } catch (IOException ex) {

                }

        }

        gImage.dispose();
    }
    //Possible modification: Adapter class

    class MouseListener_ implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {

            x1 = x2 = e.getX();
            y1 = y2 = e.getY();
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();

            width = Math.abs(x2 - x1);
            height = Math.abs(y2 - y1);
            switch (currentShape) {
                case 'l':
                    shapesVector.add(new Line(x1, y1, x2, y2, 'l', dottedPressed));
                    break;
                case 'r':
                    x = (x1 - x2) < 0 ? x1 : x2;
                    y = (y1 - y2) < 0 ? y1 : y2;
                    shapesVector.add(new Rect(x, y, width, height, 'r', dottedPressed, filledPressed));
                    break;
                case 'c':
                    x = (x1 - x2) < 0 ? x1 : x2;
                    y = (y1 - y2) < 0 ? y1 : y2;
                    shapesVector.add(new Oval(x, y, width, height, 'c', dottedPressed, filledPressed));
                    break;

            }

            repaint();
        }
    }

    class MotionListener implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();

            width = Math.abs(x2 - x1);
            height = Math.abs(y2 - y1);

            switch (currentShape) {

                case 'e':
                    shapesVector.add(new Rect(x2, y2, newWidth, newHeight, 'e', dottedPressed, filledPressed));
                    break;
                case 's':
                    shapesVector.add(new Line(x1, y1, x2, y2, 'l', dottedPressed));
                    counter += 1;
                    if (counter % 2 == 0) {
                        x1 = x2;
                        y1 = y2;
                    }

                    break;

            }
            repaint();
        }
    }

    public void storeImage(Graphics2D g) {
        BufferedImage image = create(1200, 800, true);
        g = image.createGraphics();
        // you can disable this if you don't want smooth graphics
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("/home/shimaa/Downloads/file$.png"));
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

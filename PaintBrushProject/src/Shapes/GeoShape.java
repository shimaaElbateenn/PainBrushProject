/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;
import javax.swing.text.StyleConstants;

/**
 *
 * @author shimaa
 */
public class GeoShape {

    protected int dim1;
    protected int x1; //starting point
    protected int y1;
    protected int x2;
    protected int y2;
    private char shape;
    protected boolean dottedPressed;
    protected boolean filledPressed;
    protected Color color;
    MainApplet applet = new MainApplet();
    Color currentColor = MainApplet.currentColor;

    public GeoShape() {

    }

    public GeoShape(int x1, int y1, int x2, int y2, char shape, boolean dottedPressed, boolean filledPressed) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shape = shape;       // shape 
        this.dottedPressed = dottedPressed;
        this.filledPressed = filledPressed;

        if (shape == 'e') {
            this.color = Color.WHITE;
        } else {
            this.color = currentColor;
        }
    }

    public GeoShape(int x1, int y1, int x2, int y2, char shape, boolean dottedPressed) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shape = shape;
        this.dottedPressed = dottedPressed;
        this.color = currentColor;
    }

    boolean isFilled() {
        return filledPressed;
    }

    void setShape(char s) {
        shape = s;
    }

    boolean getDottedState() {
        return this.dottedPressed;
    }

    char getShape() {
        return shape;
    }

    void setX1(int x1) {

        x1 = x1;
    }

    void setY1(int y1) {
        y1 = y1;
    }

    public void setX2(int x2) {
        x2 = x2;
    }

    public void setY2(int Y2) {
        y2 = y2;
    }

    public void setDim(int d) {
        if (d < 1) {
            System.out.print("SORRY NO NEGATIVE NUMBERS!");
        } else {
            dim1 = d;
        }

    }

    public int getDim() {
        return this.dim1;
    }

    int getX1() {
        return x1;
    }

    int getY1() {
        return y1;
    }

    int getX2() {
        return x2;
    }

    int getY2() {
        return y2;
    }

    public Color getColor() {
        return color;
    }

    public char getShapeName() {
        return shape;
    }
}

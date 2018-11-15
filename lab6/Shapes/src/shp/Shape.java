package shp;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public abstract class Shape {
    //Shape(){}
    protected String name;
    protected double x, y, w, h;
    public abstract boolean contains(Point p);
    public void setLocation(double _x, double _y) { this.x = _x; this.y = _y; }
    public void setLocation(Point p) { this.x = p.getX(); this.y = p.getY(); }
    public abstract void draw(Graphics g);
    public double getX(){ return x; }
    public double getY(){ return y; }
}

class Kolo extends Shape {

    //private double radius = w/2;
    //private Point center = new Point((int)((w+x)/2), (int)((h+y)/2));

    Kolo() { super(); name = "kolo"; }

    Kolo(double _x, double _y, double _w, double _h) {
        name = "kolo";
        this.x = x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public boolean contains(Point p){
        return  Math.pow((int)(p.getX()- (x+w/2)),2) + Math.pow((int)(p.getY()-(y+h/2)),2) <= (w/2)*(w/2);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x, y, w, h);
        Paint paint = Color.blue;
        g2d.setPaint(paint);
        g2d.fill(circle);
        g2d.draw(circle);
    }
}


class Kwadrat extends Shape {

    Kwadrat() { super(); name = "kwadrat"; }

    Kwadrat(double _x, double _y, double _w, double _h) {
        name = "kwadrat";
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public boolean contains(Point p) {
        return  p.getX() >= x && p.getX() <= x+w && p.getY() >= y && p.getY() <= y+h;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        Paint paint = Color.red;
        g2d.setPaint(paint);
        g2d.fill(rectangle);
        g2d.draw(rectangle);
    }
}
package shp;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Shape {
    protected String name;
    protected int x, y, w, h;
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getW(){ return w; }
    public int getH(){ return h; }
    public void setX(int _x){ x = _x; }
    public void setY(int _y){ y = _y; }
    public abstract boolean contains(Point p);
    public void setLocation(int _x, int _y) { this.x = _x; this.y = _y; }
    public void setLocation(Point p) { this.x = (int) p.getX(); this.y = (int) p.getY(); }
    public abstract void draw(Graphics g);

}

class Kolo extends Shape {

    //private double radius = w/2;
    //private Point center = new Point((int)((w+x)/2), (int)((h+y)/2));

    //Kolo() { super(); name = "kolo"; }

    Kolo(int _x, int _y, int _w, int _h) {
        name = "kolo";
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public boolean contains(Point p){
        //return  Math.pow((p.getX()- (x+w/2)),2) + Math.pow((p.getY()-(y+h/2)),2) <= (w/2)*(w/2);
        Ellipse2D circle = new Ellipse2D.Double(x, y, w, h);
        return circle.contains(p);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x, y, w, h);
        Paint paint = Color.blue;
        g2d.setPaint(paint);
        g2d.fill(circle);
        //g2d.draw(circle);
    }
}


class Kwadrat extends Shape {

    Kwadrat() { super(); name = "kwadrat"; }

    Kwadrat(int _x, int _y, int _w, int _h) {
        name = "kwadrat";
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _w;
    }

    public boolean contains(Point p) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        return rectangle.contains(p);
        //return  p.getX() >= x && p.getX() <= x+w && p.getY() >= y && p.getY() <= y+h;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g2d.setPaint(Color.cyan);
        g2d.fill(rectangle);
        //g2d.draw(rectangle);
    }
}


class Prostokat extends Shape {

    Prostokat(int _x, int _y, int _w, int _h) {
        name = "prostokat";
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public boolean contains(Point p) {
        //return p.getX() >= x && p.getX() <= x + w && p.getY() >= y && p.getY() <= y + h;
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        return rectangle.contains(p);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g2d.setPaint(Color.pink);
        g2d.fill(rectangle);
        //g2d.draw(rectangle);
    }
}

class Trojkat extends Shape {

    Trojkat(int _x, int _y, int _w, int _h) {
        name = "trojkat";
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public boolean contains(Point p) {
        Polygon polygon = new Polygon(new int[]{ x, x+w/2, x+w}, new int[]{ (y + h), y, (y + h) }, 3);
        return polygon.contains(p);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon polygon = new Polygon(new int[]{ x, x+w/2, x+w}, new int[]{ (y + h), y, (y + h) }, 3);
        Paint paint = Color.magenta;
        g2d.setPaint(paint);
        //g2d.draw(polygon);
        g2d.fillPolygon(polygon);
    }
}
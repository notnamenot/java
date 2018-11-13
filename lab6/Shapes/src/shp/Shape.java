package shp;

import java.awt.*;              //graphics
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public abstract class Shape {
    public String name;
    public abstract void draw(Graphics g);
}

class Kolo extends Shape {
    Kolo() { super(); name = "kolo"; }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(10, 10, 380, 380);
        Paint paint = Color.blue;
        g2d.setPaint(paint);
        //g2d.fill(circle);
        g2d.draw(circle);
    }
}


class Kwadrat extends Shape {
    Kwadrat() { name = "kwadrat";}
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(10, 10, 380, 380);
        Paint paint = Color.red;
        g2d.setPaint(paint);
        //g2d.fill(rectangle);
        g2d.draw(rectangle);

    }
    /*
    public static void main(String[] args) {
        Kwadrat kw = new Kwadrat();
        Kolo kol = new Kolo();
        //kw.draw();
        //kol.draw();
        LinkedList<Shape> lista = new LinkedList<>();
        lista.add(kw);
        lista.add(kol);
        for (int i = 0; i < lista.size(); ++i) System.out.println(lista.get(i).name);
    }
    */
}
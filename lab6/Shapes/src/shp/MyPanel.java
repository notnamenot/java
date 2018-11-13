package shp;

import javax.swing.*;   //JPanel,JFrame
import java.awt.*;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyPanel extends JPanel implements MouseListener,MouseMotionListener {
   /* public Drawer() {
        super("Hello World");                       //konstruktor nadklasy
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie
        setVisible(true);                               //wyświetlenie
        setSize(600,500);
        setLocation(200,200);
    }*/

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    private int x, y;

    // punkt, który będzie pamiętał współrzędne przesuwanego kwadracika
    Point movingPoint;

    public MyPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        //setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();       //współrzędne w których kliknięto
        y = e.getY();
        System.out.println("tu jesten: "+x+","+y);
        //points.add(new Point(x, y));
        //repaint();
        //int x1 = e.getX(); //współrzędne w których kliknięto
        //int y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*if (movingPoint != null) {
            movingPoint.x = e.getX();
            movingPoint.y = e.getY();
            repaint();
        }*/
        e.getComponent().setLocation(e.getX()+e.getComponent().getX(),e.getY()+e.getComponent().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }


    @Override
    public void paint(Graphics arg0) {
        LinkedList<Shape> lista = new LinkedList<>();
        lista.add(new Kolo());
        lista.add(new Kwadrat());

        for (int i = 0; i < lista.size(); ++i) {
            lista.get(i).draw(arg0);
        }
    }
}


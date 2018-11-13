package shp;

import javax.swing.*;   //JPanel,JFrame
import java.awt.*;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyPanel extends JPanel implements MouseListener {
   /* public Drawer() {
        super("Hello World");                       //konstruktor nadklasy
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie
        setVisible(true);                               //wyświetlenie
        setSize(600,500);
        setLocation(200,200);
    }*/

    private int x, y;

    public MyPanel() {
        addMouseListener(this);
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
        x = e.getX();
        y = e.getY();
        //points.add(new Point(x, y));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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


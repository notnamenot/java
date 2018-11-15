package shp;

import javax.swing.*;   //JPanel,JFrame
import java.awt.*;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyPanel extends JPanel implements MouseListener,MouseMotionListener {

    private ArrayList<Shape> mojaLista = new ArrayList<>();

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;


    public MyPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mojaLista.add(new Kwadrat(0, 0, 150, 150));
        mojaLista.add(new Kolo(0, 0, 150, 150));
    }

    Shape s;
    private double x, y;
    private boolean pressOut = false;   //pusczczone

    public void updateLocation(MouseEvent e) {
        s.setLocation(x + e.getX(), y + e.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (Shape shp : mojaLista)
            if (shp.contains(e.getPoint()))
                s = shp;

        x = s.getX() - e.getX();
        y = s.getY() - e.getY();

        //System.out.println("movedshape: " + s.getX() + "," + s.getY());
        //System.out.println("tu jest mycha: " + e.getX() + "," + e.getY());
        //System.out.println("tu jestem po kliknięciu: " + x + "," + y);

        if (s.contains(e.getPoint())) {
            System.out.println("mousePressedIf");
            updateLocation(e);

        } else pressOut = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (!pressOut) {
            //System.out.println("a");
            updateLocation(e);
            //repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
        if (s.contains(e.getPoint())) {
            updateLocation(e);
            //System.out.println("V"+s.getX()+" "+s.getY());

        } else pressOut = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}



    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE); //reset tla
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        for (Shape s : mojaLista)
            s.draw(g);
    }
}


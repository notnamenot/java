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
        //setLayout(new BorderLayout());
        mojaLista.add(new Prostokat(0, 0, 200, 150));
        mojaLista.add(new Kwadrat(0, 0, 150, 150));
        mojaLista.add(new Kolo(0, 0, 150, 150));
        mojaLista.add(new Trojkat(0, 0, 150, 150));
    }

    private Shape s;
    private int x, y;
    private boolean pressOut = false;   //pusczczone
    private boolean first = true;

    public void updateLocation(MouseEvent e) {
        s.setLocation(x + e.getX(),y + e.getY());
        check();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (Shape shp : mojaLista)
            if (shp.contains(e.getPoint())) {
                s = shp;
                first = false;
            }

        if (first) return;  //jeśli pierwsze kliknięcia nie będą na kształcie

        x = s.getX() - e.getX();
        y = s.getY() - e.getY();

        //System.out.println("movedshape: " + s.getX() + "," + s.getY());
        //System.out.println("tu jest mycha: " + e.getX() + "," + e.getY());
        //System.out.println("tu jestem po kliknięciu: " + x + "," + y);

        if (s.contains(e.getPoint())) {
            updateLocation(e);
        }
        else pressOut = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (first) return;

        if (!pressOut) {
            updateLocation(e);
            //repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (first) return;

        if (s.contains(e.getPoint())) {
            updateLocation(e);
        }
        else pressOut = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    private void check() {
        if (s.getX() + s.getW() > WIDTH)
            s.setX(WIDTH - s.getW());

        if (s.getX() < 0)
            s.setX(0);

        if (s.getY() + s.getH() + 30 > HEIGHT)
            s.setY(HEIGHT - s.getH()-30);

        if (s.getY() < 0)
            s.setY(0);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE); //reset tla
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        for (Shape s : mojaLista)
            s.draw(g);
    }
}


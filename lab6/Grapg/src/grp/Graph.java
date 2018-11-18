package grp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static grp.TextAndButtonPanel.pressed;
import static grp.TextAndButtonPanel.coefs;

public class Graph extends JPanel implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    private ArrayList<Integer> coefs;// = (ArrayList<Integer>) TextAndButtonPanel.getCoefs();
    private int from, to, fs;

    Graph() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());
        TextAndButtonPanel.button_draw.addActionListener(this);
        //System.out.println(coefs);
    }


    int poly(float x) {

        float y = 0;
        for (int i = coefs.size(); i > 0; --i) {
                y += Math.pow(x, i)*coefs.get(i-1);
        }
        return (int) y;
    }



    public void paint(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.setBackground(Color.white);

        from = TextAndButtonPanel.getFrom();
        to = TextAndButtonPanel.getTo();
        fs = TextAndButtonPanel.getFs();

        if (fs == 0) return;

        int part = ((to - from) / fs);
        System.out.print("a");
        int x1 = from, y1 = poly(from), x2 = from + part, y2 = poly(from+part);

        //if(pressed == true)
        for(int i = 0; i < fs; ++i) {
            g2d.drawLine(x1,y1,x2,y2);
            x1 += part; //x1=x2;
            y1 = poly(x1); //y1 =y2;
            x2 += part;
            y2 = poly(x2);
            System.out.print("Z");
            //repaint();

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    //@Override
    //public void paintComponent(Graphics g) {
    //    super.paintComponent(g);
    //}
}

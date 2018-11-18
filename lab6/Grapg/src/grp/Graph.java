package grp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static grp.TextAndButtonPanel.coefs;

public class Graph extends JPanel implements ActionListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    int x,y;
    float unit,scale;

    //private ArrayList<Integer> coefs;// = (ArrayList<Integer>) TextAndButtonPanel.getCoefs();
    private float from, to, fs;

    Graph() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());
        TextAndButtonPanel.button_draw.addActionListener(this);
        //System.out.println(coefs);
    }


    float poly(float x) {
        float y = 0;
        //System.out.println("poly");

        for (int i = coefs.size(), j= 0; i > 0; --i, ++j) {
            //System.out.println("W");
                y += Math.pow(x, i-1)*coefs.get(j);
            //System.out.println("y = "+y);
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

        //g.drawString("Origin(0,0)", 200, 240);
        g2d.drawLine(30, 470, 470, 470);
        g2d.drawLine(30, 30, 30, 470);

        if (fs == 0) return;



        float interval = (to - from) / fs;     //szerokosc przedziałów
        unit = 500/interval;                            //szerokość jednostki w poikselach
                //width of numberline w
        //System.out.println("liczba przedziałów: " + interval);


        /*int start = from;
        for (int i = 0; i < fs; ++i) {
            g.drawString(Integer.toString(start), 40+((int)(i*unit)), 260);
            start+=interval;
            //System.out.print("i="+i);
            repaint();
        }*/

        float min_poly = poly(from), max_poly =poly(from);
        //System.out.println("A min_poly = "+ min_poly +" max_poly = " +max_poly);
        for (int i = 0; i < fs+1; ++i) {              //znajdowanie minimum i maximum
            //System.out.println("from = " + from +" from+i*interval = "+ (from+i*interval));
            if (poly(from+i*interval) < min_poly)
                min_poly = poly(from+i*interval);
            else if (poly(from+i*interval) > max_poly)
                max_poly = poly(from+i*interval);
            //System.out.println("min_poly = "+ min_poly +" max_poly = " +max_poly);
        }
        System.out.println("min_poly = "+ min_poly +" max_poly = " +max_poly);


        System.out.println("from = " + from + " interval =" + interval+ " fs = "+fs);

        //Pixel Location = (Graph Location – Graph Minimum) / (Graph Width / Pixel Width)

        float graph_width = to-from;
        float graph_height = max_poly - min_poly;
        float number_line_width = graph_width/WIDTH;
        float number_line_height = graph_height/HEIGHT;
        float graph_width_min = from;
        float graph_height_min = min_poly;

        float graph_loc_x1 = from;
        float graph_loc_y1 = poly(graph_loc_x1);
        float graph_loc_x2 = (from + interval);
        float graph_loc_y2 = poly(graph_loc_x2);

        float pix_loc_x1;
        float pix_loc_y1;
        float pix_loc_x2;
        float pix_loc_y2;

        for(int i = 0; i < fs+1; ++i) {

            pix_loc_x1 = (graph_loc_x1-graph_width_min)/(number_line_width);
            pix_loc_y1 = (graph_loc_y1-graph_height_min)/(number_line_height);
            pix_loc_x2 = (graph_loc_x2-graph_width_min)/(number_line_width);
            pix_loc_y2 = (graph_loc_y2-graph_height_min)/(number_line_height);

            g2d.drawLine((int) pix_loc_x1, (int) pix_loc_y1, (int) pix_loc_x2, (int) pix_loc_y2);
             //System.out.println(graph_loc_x1 + " " + graph_loc_y1 + " " + graph_loc_x2 + " " + graph_loc_y2);
            //System.out.println(pix_loc_x1 + " " + pix_loc_y1 + " " + pix_loc_x2 + " " + pix_loc_y2);

            graph_loc_x1 += interval; //x1=x2;
            graph_loc_y1 = poly(graph_loc_x1); //y1 =y2;
            graph_loc_x2 += interval;
            graph_loc_y2 = poly(graph_loc_x2);

            //System.out.print("Z");
            //repaint();
        }
        //float x1 = 40, y1 = poly(x1), x2 = (from + interval), y2 = poly(x2);
        /*for(int i = 0; i < fs; ++i) {
            g2d.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
            System.out.println(x1 + " " + y1 + " " + x2 + " " +y2);
            x1 += interval; //x1=x2;
            y1 = poly(x1); //y1 =y2;
            x2 += interval;
            y2 = poly(x2);
            //System.out.print("Z");
            //repaint();

        }*/


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

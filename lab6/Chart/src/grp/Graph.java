package grp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import mylib.MyLib;


import static grp.TextAndButtonPanel.coefs;



public class Graph extends JPanel implements ActionListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private float from, to, fs;


    Graph() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());
        TextAndButtonPanel.button_draw.addActionListener(this);
        setBorder(new EmptyBorder(30, 30, 30, 30));
    }


    /*float poly(float x) {
        float y = 0;

        if (coefs.size() == 1) {
            y = coefs.get(0);
            return y;
        }

        for (int i = coefs.size(), j= 0; i > 0; --i, ++j) {
                y += Math.pow(x, i-1)*coefs.get(j);
        }
        return y;
    }*/



    public void paint(Graphics g) {

        MyLib myLib = new MyLib();

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.setBackground(Color.white);

        from = TextAndButtonPanel.getFrom();
        to = TextAndButtonPanel.getTo();
        fs = TextAndButtonPanel.getFs();


        if (fs == 0) return;

        String poly = myLib.polynomial(coefs);
        g2d.drawString("W(x) = "+ poly, 20,20);

        float interval = (to - from) / fs;                      //szerokosc przedziałów
        //float unit = 500/interval;                            //szerokość jednostki w poikselach

        /*float min_poly = myLib.poly(from,coefs), max_poly = myLib.poly(from,coefs);

        for (int i = 0; i < fs+1; ++i) {                         //znajdowanie ymin i ymax

            min_poly = myLib.poly(from+i*interval,coefs) < min_poly ? myLib.poly(from+i*interval,coefs) : min_poly;
            max_poly = myLib.poly(from+i*interval,coefs) > max_poly ? myLib.poly(from+i*interval,coefs) : max_poly;
        }*/
        //System.out.println("min_poly = "+ min_poly +" max_poly = " +max_poly);


        //Graph Location = Pixel Location * (Graph Width / Pixel Width) + Graph Minimum
        //Pixel Location = (Graph Location – Graph Minimum) / (Graph Width / Pixel Width)

        float graph_width = to-from;
        float graph_height = myLib.poly_min_max(from, to, fs, coefs).get(1)-myLib.poly_min_max(from, to, fs, coefs).get(0);
        float number_line_width = graph_width/WIDTH;
        float number_line_height = graph_height/HEIGHT;
        float graph_width_min = from;
        float graph_height_min = myLib.poly_min_max(from, to, fs, coefs).get(0);

        float graph_loc_x1 = from;
        float graph_loc_y1 = myLib.poly(graph_loc_x1,coefs);
        float graph_loc_x2 = (from + interval);
        float graph_loc_y2 = myLib.poly(graph_loc_x2,coefs);

        float pix_loc_x1;
        float pix_loc_y1;
        float pix_loc_x2;
        float pix_loc_y2;


        float scale = graph_width/10, x;
        DecimalFormat df = new DecimalFormat("#.#");

        for (int i = 0; i <= 10; ++i) {                 //OX
            x = i*(WIDTH/10);
            g2d.drawString((df.format(from+i*scale)), (int)x -2, HEIGHT - 5);
            //g2d.drawLine((int)x,HEIGHT-7,(int)x, HEIGHT-3);
        }


        g2d.drawLine(0,HEIGHT-2,WIDTH, HEIGHT-2); //OX

        float scale2 = graph_height/10, y;
        for (int i = 0; i <= 10; ++i) {                 //OY
            y = i*(HEIGHT/10);
            g2d.drawString((df.format(myLib.poly_min_max(from,to,fs,coefs).get(0)+i*scale2)), 5, (int)y - 5);
            //g2d.drawLine((int)x,HEIGHT-7,(int)x, HEIGHT-3);
        }
        g2d.drawLine(2,0,2, HEIGHT-2); //OY


        for(int i = 0; i < fs+1; ++i) {

            pix_loc_x1 = i*(WIDTH/fs);
            pix_loc_y1 = HEIGHT-(graph_loc_y1-graph_height_min)/(number_line_height);
            pix_loc_x2 = (i+1)*(WIDTH/fs);
            pix_loc_y2 = HEIGHT-(graph_loc_y2-graph_height_min)/(number_line_height);
            g2d.drawLine((int) pix_loc_x1, (int) pix_loc_y1, (int) pix_loc_x2, (int) pix_loc_y2);
             //System.out.println(graph_loc_x1 + " " + graph_loc_y1 + " " + graph_loc_x2 + " " + graph_loc_y2);
            //System.out.println(pix_loc_x1 + " " + pix_loc_y1 + " " + pix_loc_x2 + " " + pix_loc_y2);

            graph_loc_x1 += interval; //x1=x2;
            graph_loc_y1 = myLib.poly(graph_loc_x1,coefs); //y1 =y2;
            graph_loc_x2 += interval;
            graph_loc_y2 = myLib.poly(graph_loc_x2,coefs);

            g2d.drawLine(0,HEIGHT-2, (int) pix_loc_x2, HEIGHT-2);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

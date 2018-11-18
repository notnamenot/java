package grp;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.PAGE_END;

public class MyFrame extends JFrame {

    private int WIDTH = 800;
    private int HEIGHT = 600;

    private TextAndButtonPanel tabp;
    private Graph graph;


    MyFrame() {
        super("Grph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());

        tabp = new TextAndButtonPanel();
        graph = new Graph();
        //setLocationRelativeTo(this);
        add(tabp,BorderLayout.PAGE_END);
        //add(tabp);
        add(graph,BorderLayout.PAGE_START);
        pack();
        setVisible(true);
    }
    public static void main(String[] args){
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}

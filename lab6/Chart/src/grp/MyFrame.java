package grp;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private int WIDTH = 500;
    private int HEIGHT = 600;

    private TextAndButtonPanel tabp;
    private Graph graph;


    MyFrame() {
        super("Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLayout(new BorderLayout());

        tabp = new TextAndButtonPanel();
        graph = new Graph();
        add(tabp,BorderLayout.PAGE_END);
        add(graph,BorderLayout.PAGE_START);
        pack();
        setVisible(true);
    }
    public static void main(String[] args){
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}

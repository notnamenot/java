package shp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawerProgram extends JFrame {
    public DrawerProgram() {
        super("Shapes");                       //konstruktor nadklasy
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie

        setSize(new Dimension(700, 500));
        setLocationRelativeTo(this);
        //setLocation(200,200);
        //setLayout(null);//new FlowLayout());

        add(new MyPanel());



        setVisible(true);                               //wyświetlenie
    }
    public static void main(String[] args) {

        //DrawerProgram  dp = new DrawerProgram();
        //DrawerProgram dp = new DrawerProgram();
        //MyPanel d = new MyPanel();
        //dp.setSize(450, 450);
        //dp.setVisible(true);
        //dp.add(d);
        new DrawerProgram();
    }
}

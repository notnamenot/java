package shp;

import javax.swing.*;
import java.awt.*;

public class DrawerProgram extends JFrame {

    protected static final int WIDTH = 700;
    protected static final int HEIGHT = 500;

    public DrawerProgram() {
        super("Shapes");                       //konstruktor nadklasy
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(this);
        //setLocation(200,200);

        add(new MyPanel());

        setVisible(true);                               //wyświetlenie
    }
        
    public static void main(String[] args) {
        new DrawerProgram();
    }
}

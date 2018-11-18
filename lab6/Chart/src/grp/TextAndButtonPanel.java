package grp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TextAndButtonPanel extends JPanel {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 70;

    static ArrayList<Integer> coefs;
    private static float from, to, fs;


    private JLabel label_wsp;
    private JLabel label_od;
    private JLabel label_do;
    private JLabel label_fs;
    private JTextField entry_wsp;
    private JTextField entry_od;
    private JTextField entry_do;
    private JTextField entry_fs;
    private JButton button_add;
    public static JButton button_draw;

    static ArrayList<Integer> getCoefs() { return coefs; }
    static float getFrom() { return from; }
    static float getTo() { return to; }
    static float getFs() { return fs; }


    TextAndButtonPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());

        coefs = new ArrayList<>();

        label_wsp = new JLabel("Współczynniki: ");
        label_od = new JLabel(" od x= ");
        label_do = new JLabel(" do x= ");
        label_fs = new JLabel("Częstotliwośc próbkowania");

        entry_wsp = new JTextField(5);
        entry_od = new JTextField(5);
        entry_do = new JTextField(5);
        entry_fs = new JTextField(5);

        entry_wsp.setMaximumSize(entry_wsp.getPreferredSize());
        entry_od.setMaximumSize(entry_od.getPreferredSize());
        entry_do.setMaximumSize(entry_do.getPreferredSize());
        entry_fs.setMaximumSize(entry_fs.getPreferredSize());

        button_add = new JButton("ADD");
        button_draw = new JButton("DRAW");

        add(label_wsp);
        add(entry_wsp);
        add(button_add);
        add(label_od);
        add(entry_od);
        add(label_do);
        add(entry_do);
        add(label_fs);
        add(entry_fs);
        add(button_draw);

        button_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("coefficent added");
                coefs.add(Integer.parseInt(entry_wsp.getText()));
                entry_wsp.setText(null);
            }
        });

        button_draw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("Draw");
                from = Float.parseFloat(entry_od.getText());
                to = Float.parseFloat(entry_do.getText());
                fs = Float.parseFloat(entry_fs.getText());

                //System.out.println("coefs: " + coefs +"from: "+from+" to: " + to +" with fs: "+fs);
            }
        });
    }
}

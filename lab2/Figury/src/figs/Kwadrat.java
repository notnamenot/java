package figs;
import java.util.Scanner;


public class Kwadrat {
    protected double a;
    public Kwadrat(double _a) { a = _a; }
    private Kwadrat(){}

    public double getA() { return a; }
    public void setA() {
        System.out.print("Podaj długość boku: ");
        Scanner reader = new Scanner(System.in);
        this.a = reader.nextDouble();
        //reader.close();               //zamykamy dopiero na koncu!
    }
    public double area() { return a*a; }
    public boolean isBigger(Kwadrat k) { return k.a > this.a; }


}

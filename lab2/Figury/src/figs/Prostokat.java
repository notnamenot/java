package figs;
import java.util.Scanner;

public class Prostokat extends Kwadrat {
    public double b;

    public Prostokat(double _a, double _b) {super(_a); b = _b; }

    public double getB() { return b; }

    public static Prostokat wczytajProstokat() {
        double x,y;
        System.out.print("Podaj długości boków: ");
        Scanner reader = new Scanner(System.in);
        x = reader.nextDouble();
        y = reader.nextDouble();

        Prostokat p = new Prostokat(x,y);
        return p;
    }

    public void setB() {
        System.out.print("Podaj długość boku: ");
        Scanner reader = new Scanner(System.in);
        this.b = reader.nextDouble();
        //reader.close();
    }
    public double area() { return a*b; }
    public boolean isBigger(Prostokat p) { return p.area() > this.area(); }

    /*public static void main(String[] args) {
        Kwadrat k = new Kwadrat(3);
        Kwadrat m = new Kwadrat(4);
        System.out.println("Powierzchnia k: " + k.area());
        System.out.println("Powierzchnia m: " + m.area());
        System.out.println("m jest większe od k?  " + k.isBigger(m));
        k.setA();
        System.out.println("Powierzchnia k po zmianie: " + k.area());
        System.out.println("Powierzchnia m: " + m.area());
        System.out.println("m jest większe od k?  " + k.isBigger(m));
        System.out.println();


        Prostokat p = new Prostokat(2,4);
        Prostokat r = new Prostokat(3,5);
        System.out.println("Wymiary prostokąta p: " + p.getA() + " " + p.getB());
        System.out.println("Wymiary prostokąta r: " + r.getA() + " " + r.getB());
        System.out.println("Powierzchnia prostokąta p: " + p.area());
        System.out.println("Powierzchnia prostokąta r: " + r.area());
        r.setB();
        System.out.println("Wymiary prostokąta r po zmianie boku b: " + r.getA() + " " + r.getB());
        System.out.println("Wymiary prostokąta p: " + p.getA() + " " + p.getB());

        System.out.println("Powierzchnia prostokąta p: " + p.area());
        System.out.println("Powierzchnia prostokąta r: " + r.area());
        System.out.println("r jest większe od p?  " + p.isBigger(r));
    }*/
}


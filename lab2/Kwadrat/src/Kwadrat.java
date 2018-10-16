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
        reader.close();
    }
    public double area() { return a*a; }
    public boolean isBigger(Kwadrat k) { return k.a > this.a; }

    public static void main(String[] args) {
        Kwadrat k = new Kwadrat(3);
        Kwadrat m = new Kwadrat(4);
        System.out.println("Powierzchnia k: " + k.area());
        System.out.println("Powierzchnia m: " + m.area());

        System.out.println("m jest większe od k?  " + k.isBigger(m));
        k.setA();
        System.out.println("m jest większe od k?  " + k.isBigger(m));
        System.out.println("Powierzchnia k: " + k.area());
        System.out.println("Powierzchnia m: " + m.area());
        Prostokat p = new Prostokat(2,4);
        System.out.println("Wymiary prostokąta: " + p.getA() + " " + p.getB());
        System.out.println("Powierzchnia prostokąta: " + p.area());
    }
}

class Prostokat extends Kwadrat{
    double b;
    public Prostokat(double _a, double _b) {super(_a); b = _b; }

    public double getB() { return b; }
    public void setB() {
        System.out.print("Podaj długość boku: ");
        Scanner reader = new Scanner(System.in);
        this.b = reader.nextDouble();
        reader.close();
    }
    public double area() { return a*b; }
    public boolean isBigger(Prostokat p) { return p.area() > this.area(); }
}






 /*
    @Override
    public double getA() {
        return super.getA();
    }

 */

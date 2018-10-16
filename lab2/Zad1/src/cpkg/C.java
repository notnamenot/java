package cpkg;
import abpkg.*;

public class C extends B {
    public C(int number, String name) { super(number,name); }
    void changeName() { name = "Basia"; }   //nie nadpisuje bo jest w innym pakiecie a w B nie ma protected ani public



    public static void main(String[] args) {
        A a = new A(1, "Ala");
        System.out.println(a.getNumber() + " " + a.getName());
        a.callDecrement();
        System.out.println(a.getNumber() + " " + a.getName());
        a.callChangeName();
        System.out.println(a.getNumber() + " " + a.getName());
        a.callIncrement();
        System.out.println(a.getNumber() + " " + a.getName());

        B b = new B(2,"Mela");
        System.out.println("\n" + b.getNumber() + " " + b.getName());
        b.callDecrement();
        System.out.println(b.getNumber() + " " + b.getName());
        b.callChangeName();
        System.out.println(b.getNumber() + " " + b.getName());
        b.callIncrement();
        System.out.println(b.getNumber() + " " + b.getName());

        C c = new C(3,"Kasia");
        System.out.println("\n" + c.getNumber() + " " + c.getName());
        c.callDecrement();  //z B
        System.out.println(c.getNumber() + " " + c.getName());
        c.callChangeName();
        System.out.println(c.getNumber() + " " + c.getName());
        c.callIncrement(); //z A
        System.out.println(c.getNumber() + " " + c.getName());


    }

}





/* public static void main(String[] args) throws Exception {
    System.out.println("something important");
}
*/
//package = katalog
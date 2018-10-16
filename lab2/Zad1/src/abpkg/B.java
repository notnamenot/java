package abpkg;

public class B extends A {
    public B(int number, String name) { super(number,name); }

    protected void decrement() { number = number - 2; }
    void changeName() { name = "Ela"; } //bez specyfikatora funkcje mogą być nadpisywane w obrębie pakietu, nie można nadpisać
    private void increment() { number += 2; }   //nie można nadpisać więc powiększa o 1
}
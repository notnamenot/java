package abpkg;

public class A {
    protected int number;
    protected String name;  //protected żeby był dostępny poza pakietem

    public int getNumber() { return number; }
    public String getName() { return name; }

    public A(int number, String name) { this.number = number; this.name = name; }

    private void increment () { ++number; } //prywatnych metod nie można nadpisywać!!
    protected void decrement() { --number; }
    void changeName() { name = "Ula"; }     //w obrębie pakietu można nadpisywać

    public void callDecrement() { decrement(); }
    public void callChangeName() { changeName(); }
    public void callIncrement() { increment(); }
}




//package to katalog
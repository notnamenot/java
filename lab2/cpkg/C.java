package cpkg;
import abpkg.*;

public class C extends B {
	void changeName() { name += "X"; }
	//public C() { super(); }
	public C(int number, String name) { super(number,name); }
	
	public static void main(String[] args) {
		A a = new A(1, "ala");
		System.out.println(a.getNumber() + " " + a.getName());
		a.callDecrement();
	}		
	
}





/* public static void main(String[] args) throws Exception {
    System.out.println("something important");
}
*/
//package = katalog

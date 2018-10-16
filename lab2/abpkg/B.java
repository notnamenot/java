package abpkg;

public class B extends A {
	protected void decrement() { number = number - 2; }
	void changeName() { name += "Y"; }	
	private void increment() { number = number + 2; }
	public B(int number, String name) { super(number,name); }
}

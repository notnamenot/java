package abpkg;

public class A {
	protected int number;
	protected String name;
	
	public int getNumber() { return number; }
	public String getName() { return name; }

	public A(int number, String name) { this.number = number; this.name = name; }
	
	private void increment () { ++number; }
	protected void decrement() { --number; }
	void changeName() { name += "A"; } 

	public void callDecrement() { decrement(); }
	public void callChangeName() { changeName(); }	
	public void callIncrement() { increment(); }
} 


 

//package to katalog

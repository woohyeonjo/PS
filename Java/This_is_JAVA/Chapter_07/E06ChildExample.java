package Chapter_07;

public class E06ChildExample {
	public static void main(String args[]){
		E06Child child = new E06Child();
		
		E06Parent parent = child;
		parent.method1();
		parent.method2();
		//parent.method3();
	}
}

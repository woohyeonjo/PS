package Chapter_08;

public class E15DefaultMethodExample {
	public static void main(String args[]){
		E15MyInterface mi1 = new E15MyClassA();
		mi1.method1();
		mi1.method2();
		
		E15MyInterface mi2 = new E15MyClassB();
		mi2.method1();
		mi2.method2();
	}
}

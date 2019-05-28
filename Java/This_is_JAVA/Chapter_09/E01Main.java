package Chapter_09;

public class E01Main {
	public static void main(String args[]){
		E01A a = new E01A();
		
		E01A.B b = a.new B();
		b.field1 = 3;
		b.method1();
		
		E01A.C c = new E01A.C();
		c.field1 = 3;
		c.method1();
		E01A.C.field2 = 3;
		E01A.C.method2();
		
		a.method();
	}
}

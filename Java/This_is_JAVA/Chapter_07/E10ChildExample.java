package Chapter_07;

public class E10ChildExample {
	public static void main(String args[]){
		E10Parent parent = new E10Child();
		parent.field1 = "data1";
		parent.method1();
		parent.method2();
		/*
		parent.field2 = "data2";
		parent.method3;
		*/
		
		E10Child child = (E10Child) parent;
		child.field2 = "yyy";
		child.method3();
	}
}

package Chapter_09;

public class E05Outter {
	String field = "Outter-field";
	void method(){
		System.out.println("Outter-method");
	}
	
	class Nested{
		String field = "Nested-field";
		void method(){
			System.out.println("Nested-method");
		}
		
		void print(){
			System.out.println(this.field);
			this.method();
			System.out.println(E05Outter.this.field);
			E05Outter.this.method();
		}
	}
}

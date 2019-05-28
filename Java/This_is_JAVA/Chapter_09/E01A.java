package Chapter_09;

public class E01A {
	E01A(){
		System.out.println("A 객체가 생성됨");
	}
	
	class B{
		B(){
			System.out.println("B 객체가 생성됨");
		}
		
		int field1;
		//static int field2;
		void method1(){}
		//static void method2(){}
	}
	
	static class C{
		C(){
			System.out.println("C 객체가 생성됨");
		}
		int field1;
		static int field2;
		void method1(){}
		static void method2(){}
	}
	
	void method(){
		class D{
			D(){
				System.out.println("D 객체가 생성됨 ");
			}
			int field1;
			//static field2;
			void method1(){}
			//static method2(){}
		}
		D d = new D();
		d.field1 = 3;
		d.method1();
	}
}

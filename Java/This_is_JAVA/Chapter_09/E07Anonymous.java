package Chapter_09;

public class E07Anonymous {
	E07Person field = new E07Person(){
		void work(){
			System.out.println("출근합니다.");
		}
		
		@Override
		void wake(){
			System.out.println("6시에 일어납니다.");
			work();
		}
	};
	
	void method1(){
		E07Person localVar = new E07Person(){
			void walk(){
				System.out.println("산책합니다.");
			}
			@Override
			void wake(){
				System.out.println("7시에 일어납니다.");
				walk();
			}
		};
		
		localVar.wake();
	}
	
	void method2(E07Person person){
		person.wake();
	}
}

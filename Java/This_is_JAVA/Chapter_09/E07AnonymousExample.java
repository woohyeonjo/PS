package Chapter_09;

public class E07AnonymousExample {
	public static void main(String args[]){
		E07Anonymous anony = new E07Anonymous();
		
		anony.field.wake();
		anony.method1();
		anony.method2(
				new E07Person(){
					void study(){
						System.out.println("공부합시다.");
					}
					@Override
					void wake(){
						System.out.println("8시에 일어납니다.");
						study();
					}
				});
		
	}
}

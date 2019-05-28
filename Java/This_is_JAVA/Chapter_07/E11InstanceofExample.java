package Chapter_07;

public class E11InstanceofExample {
	public static void method1(E11Parent parent){
		if(parent instanceof E11Child){
			E11Child child = (E11Child)parent;
			System.out.println("method1 - Child로 변환 성공");
		} else {
			System.out.println("method1 - Child로 변환되지 않음");
		}
	}
	
	public static void method2(E11Parent parent){
		E11Child child = (E11Child) parent;
		System.out.println("method2 - Child로 변환 성공");
	}
	
	public static void main(String args[]){
		E11Parent parentA = new E11Child();
		method1(parentA);
		method2(parentA);
		
		E11Parent parentB = new E11Parent();
		method1(parentB);
		method2(parentB); //예외발생
	}
}

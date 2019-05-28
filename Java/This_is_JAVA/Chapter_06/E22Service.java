package Chapter_06;

public class E22Service {
	@E22PrintAnnotation
	public void method1(){
		System.out.println("실행 내용 1");
	}
	
	@E22PrintAnnotation("*")
	public void method2(){
		System.out.println("실행 내용 2");
	}
	
	@E22PrintAnnotation(value="#", number = 20)
	public void method3(){
		System.out.println("실행 내용 3");
	}
}

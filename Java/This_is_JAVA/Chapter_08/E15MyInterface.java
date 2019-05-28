package Chapter_08;

public interface E15MyInterface {
	public void method1();
	
	public default void method2(){
		System.out.println("MyInterface-method2  실행");
	}
}

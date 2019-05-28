package Chapter_06;

public class E18Singleton {
	private static E18Singleton singleton = new E18Singleton();
	
	private E18Singleton(){}
	
	static E18Singleton getInstatnce(){
		return singleton;
	}
}

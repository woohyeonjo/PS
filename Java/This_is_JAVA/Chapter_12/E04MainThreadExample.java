package Chapter_12;

public class E04MainThreadExample {
	public static void main(String[] args){
		E04Calculator calculator = new E04Calculator();
		
		E04User1 user1 = new E04User1();
		user1.setCalculator(calculator);
		user1.start();
		
		E04User2 user2 = new E04User2();
		user2.setCalculator(calculator);
		user2.start();
	}
}

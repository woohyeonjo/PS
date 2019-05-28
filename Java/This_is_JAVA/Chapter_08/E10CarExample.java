package Chapter_08;

public class E10CarExample {
	public static void main(String args[]){
		E10Car myCar = new E10Car();
		
		myCar.run();
		
		myCar.tires[0] = new E09KumhoTire();
		myCar.tires[1] = new E09KumhoTire();
		
		myCar.run();
	}
}

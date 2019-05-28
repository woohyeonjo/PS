package Chapter_08;

public class E09CarExample {
	public static void main(String args[]){
		E09Car myCar = new E09Car();
		
		myCar.run();
		
		myCar.frontLeftTire = new E09KumhoTire();
		myCar.frontRightTire = new E09KumhoTire();
		
		myCar.run();
	}
}

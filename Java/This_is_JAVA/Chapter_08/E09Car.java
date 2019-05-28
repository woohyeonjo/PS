package Chapter_08;

public class E09Car {
	E09Tire frontLeftTire = new E09HankookTire();
	E09Tire frontRightTire = new E09HankookTire();
	E09Tire backLeftTire = new E09HankookTire();
	E09Tire backRightTire = new E09HankookTire();
	
	void run(){
		frontLeftTire.roll();
		frontRightTire.roll();
		backLeftTire.roll();
		backRightTire.roll();
	}
}

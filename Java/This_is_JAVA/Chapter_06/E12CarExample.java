package Chapter_06;

public class E12CarExample {
	public static void main(String[] args){
		E12Car myCar = new E12Car();
		myCar.keyTurnOn();
		myCar.run();
		int speed = myCar.getSpeed();
		System.out.println("현재 속도 : " + speed + "km/h");
	}
}

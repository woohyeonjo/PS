package Chapter_07;

public class E07Car {

	E07Tire frontLeftTire = new E07Tire("앞 왼쪽", 6);
	E07Tire frontRightTire = new E07Tire("앞 오른쪽", 2);
	E07Tire backLeftTire = new E07Tire("뒷 왼쪽", 3);
	E07Tire backRightTire = new E07Tire("뒷 오른쪽", 4);
	
	int run(){
		System.out.println("[자동차가 달립니다.]");
		if(frontLeftTire.roll()==false) {stop(); return 1;};
		if(frontRightTire.roll()==false) {stop(); return 2;};
		if(backLeftTire.roll()==false) {stop(); return 3;};
		if(backRightTire.roll()==false) {stop(); return 4;};
		return 0;
	}
	
	void stop(){
		System.out.println("[자동차가 멈춥니다.]");
	}
}

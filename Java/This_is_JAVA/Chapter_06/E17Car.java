package Chapter_06;

public class E17Car {
	int speed;
	
	void run(){
		System.out.println(speed + " 으로 달립니다.");
	}
	
	public static void main(String args[]){
		E17Car myCar = new E17Car();
		myCar.speed = 60;
		myCar.run();
	}
}

package Chapter_07;

public class E03Computer extends E03Calculator {
	@Override
	double areaCircle(double r){
		System.out.println("Computer 객체의 areaCircle() 실행");
		return 3.14159 * r * r;
	}
}

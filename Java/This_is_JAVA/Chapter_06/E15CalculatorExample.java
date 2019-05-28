package Chapter_06;

public class E15CalculatorExample {
	public static void main(String args[]){
		double result1 = 10 * 10 * E15Calculator.pi;
		int result2 = E15Calculator.plus(10, 5);
		int result3 = E15Calculator.minus(10, 5);
		
		System.out.println("result1 : " + result1);
		System.out.println("result2 : " + result2);
		System.out.println("result3 : " + result3);
	}
}

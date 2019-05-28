package Chapter_03;

public class E09AccuracyExample1 {
	public static void main(String args[]){
		int apple = 1;
		double pieceUnit = 0.1;
		int number = 7;
		
		double result = apple - number * pieceUnit;
		
		System.out.print("사과 한개에서 ");
		System.out.print("0.7 조각을 빼면, ");
		System.out.print(result + " 조각이 남는다.");
	}
}

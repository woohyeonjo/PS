package Chapter_03;

public class E10AccuracyExample2 {
	public static void main(String args[]){
		int apple = 1;
		
		int totalPieces = apple * 10;
		int number = 7;
		int temp = totalPieces - number;
		
		double result = temp/10.0;
		
		System.out.print("사과 한개에서 ");
		System.out.print("0.7 조각을 빼면, ");
		System.out.print(result + " 조각이 남는다.");
	}
}

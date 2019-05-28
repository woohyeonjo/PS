package Chapter_07;

public class E03ComputerExample {
	public static void main(String args[]){
		int r = 10;
		
		E03Calculator calculator = new E03Calculator();
		System.out.println("원면적 : " + calculator.areaCircle(r));
		System.out.println();
		
		E03Computer computer = new E03Computer();
		System.out.println("원면적 : " + computer.areaCircle(r));
		
	}
}

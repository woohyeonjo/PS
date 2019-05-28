package Chapter_12;

public class E04User2 extends Thread {
	private E04Calculator calculator;
	
	public void setCalculator(E04Calculator calculator){
		this.setName("CalculatorUser2");
		this.calculator = calculator;
	}
	
	public void run(){
		calculator.setMemory(50);
	}
}

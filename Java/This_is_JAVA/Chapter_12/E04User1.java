package Chapter_12;

public class E04User1 extends Thread {
	private E04Calculator calculator;
	
	public void setCalculator(E04Calculator calculator){
		this.setName("CalculatorUser1");
		this.calculator = calculator;
	}
	
	public void run(){
		calculator.setMemory(100);
	}
}

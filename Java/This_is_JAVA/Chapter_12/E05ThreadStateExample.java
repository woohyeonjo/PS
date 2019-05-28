package Chapter_12;

public class E05ThreadStateExample {
	public static void main(String[] args){
		E05StatePrintThread statePrintThread =
					new E05StatePrintThread(new E05TargetThread());
		statePrintThread.start();
	}
}

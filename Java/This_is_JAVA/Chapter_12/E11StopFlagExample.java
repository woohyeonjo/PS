package Chapter_12;

public class E11StopFlagExample {
	public static void main(String[] args){
		E11PrintThread1 printThread = new E11PrintThread1();
		printThread.start();
		
		try { Thread.sleep(1000); } catch (InterruptedException e) {}
		
		printThread.setStop(true);
	}
}

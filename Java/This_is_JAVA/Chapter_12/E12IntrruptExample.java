package Chapter_12;

public class E12IntrruptExample {
	public static void main(String[] args){
		Thread thread = new E12PrintThread2();
		thread.start();
		
		try{ Thread.sleep(1000); } catch(InterruptedException e){}
		thread.interrupt();
	}
}

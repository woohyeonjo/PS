package Chapter_12;

public class E07YieldExample {
	public static void main(String[] args){
		E07ThreadA threadA = new E07ThreadA();
		E07ThreadB threadB = new E07ThreadB();
		threadA.start();
		threadB.start();
		
		try { Thread.sleep(3000); } catch (InterruptedException e) {}
		threadA.work = false;
		
		try { Thread.sleep(3000); } catch (InterruptedException e) {}
		threadA.work = true;
		
		try { Thread.sleep(3000); } catch (InterruptedException e) {}
		threadA.stop = true;
		threadB.stop = true;
	}
}

package Chapter_12;

public class E15WorkThread extends Thread {
	public E15WorkThread(ThreadGroup threadGroup, String threadName){
		super(threadGroup, threadName);
	}
	
	@Override
	public void run(){
		while(true){
			try{
				Thread.sleep(1000);
			} catch (InterruptedException e){
				System.out.println(getName() + " interrupted");
				break;
			}
		}
		System.out.println(getName() + "종료됨");
	}
}

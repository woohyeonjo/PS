package Chapter_12;

public class E05StatePrintThread extends Thread {
	private Thread targetThread;
	
	public E05StatePrintThread(Thread targetThread){
		this.targetThread = targetThread;
	}
	
	public void run(){
		while(true){
			Thread.State state = targetThread.getState();
			System.out.println("타겟 스레드 상태: " + state);
			
			if(state == Thread.State.NEW){
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED){
				break;
			}
			try{
				Thread.sleep(500);
			} catch(Exception e){}
		}
	}
}

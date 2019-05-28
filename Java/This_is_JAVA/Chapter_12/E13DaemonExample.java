package Chapter_12;

public class E13DaemonExample {
	public static void main(String[] args){
		E13AutoSaveThread autoSaveThread = new E13AutoSaveThread();
		autoSaveThread.setDaemon(true);
		autoSaveThread.start();
		
		try{
			Thread.sleep(3000);
		} catch(InterruptedException e){}
		System.out.println("메인 스레드 종료");
	}
}

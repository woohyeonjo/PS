package Chapter_12;

public class E07ThreadA extends Thread {
	public boolean stop = false;
	public boolean work = true;
	
	public void run(){
		while(!stop){
			if(work) {
				System.out.println("ThreadA 작업 내용");
			} else {
				Thread.yield();
			}
		}
		System.out.println("ThreadA 종료");
	}
}
